<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zadání pohybu na účtu</title>
</head>
<body>
	<form action="operationprocess.jsp">
		Zadejte částku:<input type="text" name="amount"/><br>
		Vyberte operaci:
		Vklad <input type="radio" name="operation" value="deposit">
		Výběr <input type="radio" name="operation" value="withdraw">
		Zjistit zůstatek <input type="radio" name="operation" value="checkbalance">
		<br>
		<input type="submit" value="Provést operaci">
	
	</form>
</body>
</html>