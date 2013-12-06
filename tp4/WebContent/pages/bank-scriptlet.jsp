<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ema.tpjee.banking.model.Bank"%>
<jsp:useBean id="currentbank" scope="session"
	class="ema.tpjee.banking.model.Bank" />
<jsp:useBean id="banks" scope="session" class="java.util.ArrayList" />
<jsp:useBean id="error" scope="request" class="java.lang.String" />
<!-- jsp:setProperty doesn't work with mvc pattern, setting bean properties is done by a servlet
jsp:setProperty name="currentbank" property="*" 
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Application - scriptlet</title>
</head>
<body>
<a href="index.jsp">retour</a>
<h1>Application bancaire</h1>
<div><%=error%>
<form action="bank.do">
<input name="page" type="hidden" value="<%=request.getParameter("page") %>" />
<input name="id" type="hidden"
	value="<%=currentbank.getId()%>" /> id:<%=currentbank.getId()%><br />
nom:<input name="name" type="text"
	value="<%=currentbank.getName()%>" /><br />
adresse:<input name="address" type="text"
	value="<%=currentbank.getAddress()%>" /><br />
t�l�phone:<input name="phone" type="text"
	value="<%=currentbank.getPhone()%>" /><br />
code postal:<input name="zipCode" type="text"
	value="<%=currentbank.getZipCode()%>" /><br />

<input name="cmd" type="submit" value="modifier" />
<input name="cmd" type="submit" value="ajouter" /> 
<input name="cmd" type="submit" value="suivant" /> 
<input name="cmd" type="submit" value="precedent" />
<input name="cmd" type="submit" value="dupliquer" /> 
<input name="cmd" type="submit" value="foobar" /> 
<input name="cmd" type="submit" value="annuler" /> 
<input name="cmd" type="submit" value="clients" /> <br />
</form>
</div>
<hr />

<div>liste des banques
<table border="1">
	<tr>
		<td>id</td>
		<td>&nbsp;</td>
		<td>name</td>
		<td>address</td>
		<td>phone</td>
		<td>zipcode</td>
	</tr>
	<%
		for (int i = 0; i < banks.size(); i++) {
			Bank b = (Bank) banks.get(i);
	%>
	<tr>
		<%
			if (b.getId() == currentbank.getId()) {
		%>
		<td bgcolor="red">
		<%
			} else {
		%>
		
		<td>
		<%
			}
		%> <a href="bank.do?cmd=editer&id=<%=b.getId()%>&page=<%=request.getParameter("page")%>"><%=b.getId()%></a></td>
		<td><a href="bank.do?cmd=supprimer&id=<%=b.getId()%>&page=<%=request.getParameter("page")%>">supprimer</a></td>
		<td><%=b.getName()%></td>
		<td><%=b.getAddress()%></td>
		<td><%=b.getPhone()%></td>
		<td><%=b.getZipCode()%></td>
	</tr>
	<%
		}
	%>

</table>
</div>
</body>
</html>