<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% String kadi = (String)session.getAttribute("kadi"); %>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
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
            border-radius:10px;
            background-color: white;
            padding: 20px;
            width: 400px;
            height: 360px;
        }
        .header{
        	display: flex;
        	flex-direction:column;
        	justify-content: center;
        	align-items: center;
        	height:100px;	
        }
        
        .header a{
        	font-size: 22px;
        	color: aqua;
        	margin-top: 10px;
        	text-decoration: none;
        	padding : 10px;
        	border: 1px solid aqua;
        }
        .header h1{
        	color:white;
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

	<div class="header">
		<h1><% out.print("Hoşgeldin "+kadi); %></h1>
		<a href="UserServlet?ok=1">Kişileri Listele</a>
	</div>
    <form class="form" action="UserServlet" method="post">
        <h1>Kişi Ekle</h1>
        <div class="form-group row">
            <label for="name">İsim: </label>
            <input type="text" name="name" placeholder="İsim giriniz.">
        </div>
        <div class="form-group row">
            <label for="surname">Soyisim: </label>
            <input type="text" name="surname" placeholder="Soyisim giriniz.">
        </div>
        <div class="form-group row">
            <label for="tckn">TCKN: </label>
            <input type="text" name="tckn" placeholder="TC Kimlik numarannızı giriniz.">
        </div>
        <div class="form-group row">
            <label for="tckn">Numara: </label>
            <input type="text" name="number" placeholder="Telefon numaranızı giriniz.">
        </div>
        <input type="submit" value="Gönder" name="gonder">
    </form>
</body>
</html>