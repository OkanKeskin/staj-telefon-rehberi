<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Dao.NumberDao" %>
<%@page import="User.Numbers" %>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        body{
            background-color: grey;
            display: flex;
            flex-direction: column;
            height: 100vh;
        }
        form{
        	display: flex;
            flex-direction: column;
            margin:auto;
            background-color: white;
            border-radius:10px;
            padding: 20px;
            width: 450px;
            height: 200px;
        }
        form h1{
            text-align: center;
            margin-bottom: 15px;
        }
        form div{
            padding: 10px;
        }
        form div label{
            font-size: 20px;
        }
        form div input{
            padding: 10px 20px;
            border-radius: 10px;
            border: 1px solid black;
        }
        form div input:not(last-child){
            margin-left: 10px;
        }
        form input[type="submit"]{
            margin-top: 10px;
            height: 40px;
            border-radius: 10px;
            border: none;
            background-color: aqua;
            font-size: 20px;
        }
        form input[type="submit"]:hover{
            background-color: rgb(169, 234, 234);
        }
    </style>
</head>
<body>
<div class="container mt-4">
	<div class="row">
		<div class="col-md-6">
			<table class="table table-striped">
				<tr>
					<td><h3>Numaralar</h3></td>
					<td>Sil</td>
				</tr>
				<c:forEach var="number" items="${listNumber}">
					<tr>
						<td><c:out value="${number.number}">NOo</c:out></td>
						<td><button type="button" class="btn btn-dark" onclick="location.href='?delete=<c:out value="${number.numberId}"/>'">Sil</button></td>	
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-6">
			<form action="NumberServlet" method="post">
		        <div class="form-group row">
		            <label for="name">Numara: </label>
		            <input type="text" name="number">
		        </div>
		        <input type="submit" value="Ekle" name="gonder">
		    </form>
		</div>
	</div>
</div>
	
	
	
	
	<!-- JavaScript Bundle with Popper -->
</body>
</html>