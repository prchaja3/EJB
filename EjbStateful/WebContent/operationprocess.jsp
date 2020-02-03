<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ejb.BankRemote" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="operation.jsp"></jsp:include>
	<%
	BankRemote remote = (BankRemote)session.getAttribute("remote");
	String operation = request.getParameter("operation");
	String amount = request.getParameter("amount");
	
	if (operation != null){
		if (operation.equals("deposit")){
			remote.deposit(Integer.parseInt(amount));
			out.print("Částka úspěšně vložena");
		}
		else if (operation.equals("withdraw")){
			boolean status = remote.withdraw(Integer.parseInt(amount));
			if (status){
				out.print("Částka úspěšně vybrána");
			}
			else {
				out.println("Zadejte nižší částku");
			}	
		}
		else {
			out.print("Současný zůstatek: " + remote.getBalance());
		}
	}
	%>
	
</body>
</html>