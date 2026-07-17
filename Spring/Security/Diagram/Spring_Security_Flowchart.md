# Spring Security — Complete Flowchart

A step-by-step map of how a request flows through Spring Security: from the
Servlet container, through the filter chain, into authentication, then
authorization, and finally to your controller. All diagrams use
[Mermaid](https://mermaid.js.org/) and render directly on GitHub.

---

## 1. Big Picture — Where Spring Security Sits

Spring Security is a **chain of Servlet Filters** inserted in front of your
application by a single `DelegatingFilterProxy` registered in the container.

```mermaid
flowchart LR
    Client([Client / Browser]) --> Container[Servlet Container]
    Container --> DFP[DelegatingFilterProxy<br/>springSecurityFilterChain]
    DFP --> FCP[FilterChainProxy]
    FCP --> SFC[SecurityFilterChain<br/>ordered list of filters]
    SFC --> DS[DispatcherServlet]
    DS --> C[Your @Controller]
    C --> Resp([Response])
```

- **DelegatingFilterProxy** — a standard Servlet filter that delegates to a
  Spring-managed bean (`FilterChainProxy`), bridging the container and the
  Spring context.
- **FilterChainProxy** — picks the matching `SecurityFilterChain` (you can have
  several, matched by URL) and runs its filters in order.
- If all security filters pass, the request reaches the `DispatcherServlet`
  and your controller.

---

## 2. The Security Filter Chain (Order Matters)

Each filter has one job. The request passes through them in a fixed order.

```mermaid
flowchart TD
    A[Incoming Request] --> B[SecurityContextPersistenceFilter<br/>load SecurityContext from session]
    B --> C[HeaderWriterFilter<br/>security headers]
    C --> D[CorsFilter]
    D --> E[CsrfFilter<br/>validate CSRF token]
    E --> F[LogoutFilter]
    F --> G[UsernamePasswordAuthenticationFilter<br/>form login POST /login]
    G --> H[BasicAuthenticationFilter<br/>HTTP Basic header]
    H --> I[Custom filters e.g. JwtAuthenticationFilter]
    I --> J[RequestCacheAwareFilter]
    J --> K[SecurityContextHolderAwareRequestFilter]
    K --> L[AnonymousAuthenticationFilter<br/>assign anonymous if none]
    L --> M[SessionManagementFilter]
    M --> N[ExceptionTranslationFilter<br/>catch auth/authz exceptions]
    N --> O[AuthorizationFilter<br/>FilterSecurityInterceptor]
    O --> P{Authorized?}
    P -->|Yes| Q[Proceed to Controller]
    P -->|No| R[403 / redirect to login]
```

> **Tip:** run with `logging.level.org.springframework.security=DEBUG` to see
> the exact filter list for your app at startup.

---

## 3. Authentication Flow (The Core)

This is the heart of it. When a request carries credentials, an
**authentication filter** builds an `Authentication` token and hands it to the
`AuthenticationManager`.

```mermaid
flowchart TD
    Start([Request with credentials]) --> Filter[Authentication Filter<br/>UsernamePasswordAuthenticationFilter]
    Filter --> Token[Create unauthenticated<br/>UsernamePasswordAuthenticationToken]
    Token --> AM[AuthenticationManager<br/>ProviderManager]

    AM --> Loop{Iterate over<br/>AuthenticationProviders}
    Loop --> P1[AuthenticationProvider<br/>supports this token?]
    P1 -->|No| Loop
    P1 -->|Yes| DaoP[DaoAuthenticationProvider.authenticate]

    DaoP --> UDS[UserDetailsService<br/>loadUserByUsername]
    UDS --> DB[(User store<br/>DB / LDAP / in-memory)]
    DB --> UD[UserDetails<br/>username, hashed pwd, authorities]
    UD --> PE[PasswordEncoder.matches<br/>raw vs stored hash]

    PE --> Check{Credentials valid?}
    Check -->|No| Fail[Throw BadCredentialsException]
    Check -->|Yes| Auth[Return authenticated<br/>Authentication + authorities]

    Fail --> FH[AuthenticationFailureHandler<br/>401 / redirect ?error]
    Auth --> Ctx[Store in SecurityContextHolder<br/>SecurityContext]
    Ctx --> SH[AuthenticationSuccessHandler<br/>redirect / 200]
    SH --> Done([Authenticated — continue chain])
```

### Key contracts in this flow

| Component | Responsibility |
|---|---|
| `Authentication` | Holds the principal, credentials, and authorities. Starts *unauthenticated*, ends *authenticated*. |
| `AuthenticationManager` | Entry point. Usually `ProviderManager`. |
| `ProviderManager` | Delegates to a list of `AuthenticationProvider`s until one succeeds. |
| `AuthenticationProvider` | Does the actual check. `DaoAuthenticationProvider` is the common one. |
| `UserDetailsService` | Loads user data by username (`loadUserByUsername`). |
| `UserDetails` | The loaded user: username, encoded password, authorities, account flags. |
| `PasswordEncoder` | Verifies raw password against the stored hash (e.g. BCrypt). |
| `SecurityContextHolder` | Thread-local holder of the current `SecurityContext` (the authenticated principal). |

---

## 4. Authorization Flow

Once authenticated, Spring Security decides **what the user is allowed to do**.

```mermaid
flowchart TD
    Req([Authenticated request]) --> AF[AuthorizationFilter]
    AF --> Get[Get Authentication from<br/>SecurityContextHolder]
    Get --> AM2[AuthorizationManager.check]
    AM2 --> Rules{Match against rules<br/>hasRole / hasAuthority / permitAll}
    Rules -->|Granted| Allow[AuthorizationDecision: granted]
    Rules -->|Denied| Deny[AccessDeniedException]
    Allow --> Ctrl([Reach Controller])
    Deny --> ET[ExceptionTranslationFilter]
    ET --> D2{Anonymous user?}
    D2 -->|Yes| Login[AuthenticationEntryPoint<br/>redirect to login / 401]
    D2 -->|No| F403[AccessDeniedHandler<br/>403 Forbidden]
```

- **Authentication** = *who are you?* → **Authorization** = *what can you do?*
- Rules come from your `SecurityFilterChain` config (`requestMatchers(...).hasRole(...)`)
  and/or method security (`@PreAuthorize`, `@Secured`).

---

## 5. Exception Handling Distinction

A frequent point of confusion — the `ExceptionTranslationFilter` routes two
different failures to two different handlers:

```mermaid
flowchart TD
    E[ExceptionTranslationFilter catches] --> T{Exception type}
    T -->|AuthenticationException<br/>not logged in| EP[AuthenticationEntryPoint<br/>start authentication - 401 / login page]
    T -->|AccessDeniedException<br/>logged in, lacks rights| ADH[AccessDeniedHandler<br/>403 Forbidden]
```

---

## 6. Form Login vs Stateless JWT

The two most common setups differ in **how state is carried between requests**.

### 6a. Session-based Form Login (stateful)

```mermaid
flowchart LR
    L1([POST /login user+pass]) --> L2[UsernamePasswordAuthenticationFilter]
    L2 --> L3[Authenticate via ProviderManager]
    L3 --> L4[Store SecurityContext in HttpSession]
    L4 --> L5[Return JSESSIONID cookie]
    L5 --> L6([Subsequent requests send cookie])
    L6 --> L7[SecurityContextPersistenceFilter<br/>restores context from session]
```

### 6b. Stateless JWT (token-based)

```mermaid
flowchart TD
    J1([POST /login user+pass]) --> J2[Authenticate credentials]
    J2 --> J3[Generate signed JWT<br/>header.payload.signature]
    J3 --> J4[Return JWT to client]
    J4 --> J5([Client stores token])
    J5 --> J6([Later request:<br/>Authorization: Bearer token])
    J6 --> J7[JwtAuthenticationFilter<br/>custom OncePerRequestFilter]
    J7 --> J8{Validate signature<br/>and expiry}
    J8 -->|Invalid| J9[401 Unauthorized]
    J8 -->|Valid| J10[Build Authentication<br/>from token claims]
    J10 --> J11[Set SecurityContextHolder]
    J11 --> J12([Continue — no server session])
```

| Aspect | Form Login (session) | JWT (stateless) |
|---|---|---|
| Server state | `HttpSession` stores context | None — token is self-contained |
| Carried by | `JSESSIONID` cookie | `Authorization: Bearer` header |
| Scaling | Needs sticky sessions / shared store | Scales freely |
| Revocation | Invalidate session | Harder — needs blocklist / short expiry |
| CSRF | Relevant (cookie-based) | Usually disabled (header-based) |

---

## 7. End-to-End Summary (One Diagram)

```mermaid
flowchart TD
    A([Request]) --> B[Filter Chain]
    B --> C{Credentials present?}
    C -->|No| D[AnonymousAuthenticationFilter<br/>anonymous principal]
    C -->|Yes| E[AuthenticationManager]
    E --> F[AuthenticationProvider<br/>+ UserDetailsService + PasswordEncoder]
    F --> G{Valid?}
    G -->|No| H[401 — AuthenticationEntryPoint]
    G -->|Yes| I[Set SecurityContextHolder]
    D --> J[AuthorizationFilter]
    I --> J
    J --> K{Has required authority?}
    K -->|No| L[403 — AccessDeniedHandler]
    K -->|Yes| M([Controller runs])
    M --> N([Response])
```

---

## 8. Minimal Reference Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());   // or .httpBasic(...)
        return http.build();
    }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.withUsername("user")
            .password("{bcrypt}$2a$10$...")   // encoded
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

---

## Glossary (Quick Recall)

- **Principal** — the authenticated entity (usually the user).
- **Authority / Role** — a permission granted to the principal (`ROLE_ADMIN`, `READ`).
- **GrantedAuthority** — Spring's representation of an authority.
- **SecurityContext** — holds the current `Authentication`.
- **SecurityContextHolder** — thread-local access to the `SecurityContext`.
- **AuthenticationEntryPoint** — kicks off authentication when an anonymous user hits a protected resource.
- **OncePerRequestFilter** — base class for custom filters (e.g. JWT) guaranteed to run once per request.
