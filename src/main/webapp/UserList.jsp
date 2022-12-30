<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Dao.UserDao" %>
<%@page import="User.User" %>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	List<User> listUser = UserDao.getInstance().listUser();
	request.setAttribute("listUser", listUser);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<style>
	a{
		text-decoration: none;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
	<table class="table table-striped">
		<tr>
			<td scrope="col">ID</td>
			<td scrope="col">Ad</td>
			<td scrope="col">Soyad</td>
			<td scrope="col">TCKN</td>
			<td scrope="col">Sil</td>
			<td scrope="col">Güncelle</td>
			<td scrope="col">Numaralar</td>
		</tr>
		<c:forEach var="user" items="${listUser}">
			<tr>
				<td scrope="row"><c:out value="${user.id}">NOo</c:out></td>
				<td><c:out value="${user.name}"/></td>
				<td><c:out value="${user.surname}"/></td>
				<td><c:out value="${user.tckn}"/></td>
				<td><button type="button" class="btn btn-dark" onclick="location.href='?sil=<c:out value="${user.id}"/>'">Sil</button></td>
				<td><button type="button" class="btn btn-dark" onclick="location.href='?edit=<c:out value="${user.id}"/>'">Güncelle</button></td>
				<td><button type="button" class="btn btn-dark" onclick="location.href='./NumberServlet?id=<c:out value="${user.id}" />'">Numaraları Görüntüle</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>