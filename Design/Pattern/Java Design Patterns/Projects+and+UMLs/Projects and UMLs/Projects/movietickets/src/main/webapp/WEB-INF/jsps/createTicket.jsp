<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Ticket</title>
</head>
<body>
<form action="createTicket" method="post">
Movie Name:<input type="text" name="movieName"/>
Screen:<input type="text" name="screenNo"/>
Seat No:<input type="text" name="seatNo"/>
<input type="submit" value="purchase"/>
</form>
${msg}
</body>
</html>