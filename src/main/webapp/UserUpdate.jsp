<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Dao.UserDao" %>
<%@page import="User.User" %>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
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
            width: 460px;
            height: 450px;
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
    <form class="form" action="UserServlet?guncelle=<c:out value='${user.id}'/>" method="post">
        <h1>Kişi Güncelle</h1>
        <div class="form-group row">
            <label for="name">İsim: </label>
            <input type="text" name="name" placeholder="İsim giriniz." value="<c:out value='${user.name}'/>">
        </div>
        <div class="form-group row">
            <label for="surname">Soyisim: </label>
            <input type="text" name="surname" placeholder="Soyisim giriniz." value="<c:out value='${user.surname}'/>">
        </div>
        <div class="form-group row">
            <label for="tckn">TCKN: </label>
            <input type="text" name="tckn" placeholder="TC Kimlik numaranızı giriniz." value="<c:out value='${user.tckn}'/>"> 
        </div>
        <input type="submit" value="Gonder">
    </form>
</body>
</html>