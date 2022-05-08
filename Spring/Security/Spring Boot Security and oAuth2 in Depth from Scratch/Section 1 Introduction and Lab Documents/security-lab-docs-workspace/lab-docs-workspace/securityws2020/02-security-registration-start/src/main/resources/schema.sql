
create table users(
	username varchar_ignorecase(50) not null primary key,
	first_name varchar_ignorecase(50),
	last_name varchar_ignorecase(50),
	password varchar_ignorecase(200) not null,
	email varchar(100) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
