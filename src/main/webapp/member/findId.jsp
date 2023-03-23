<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>로그인</title>    
<link rel="stylesheet" href="./css/login.css">
<script src="../lib/jquery-3.6.0.min.js"></script>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>
</head>

<body class="home-template">
<div class="site-wrapper">
	<jsp:include page="../inc/top.jsp"/>

 <section class="login-form">
	 <div class="panel border bg-white">
	<h1>아이디 찾기</h1>
	<form action="./findIdAction.me" method="post" name="fId">
		<div class="int-area">
			<input type="text" name="Mem_name" id="Mem_name" autocomplete="off" required>
			<label for="mem_name"> 이름 </label>
		</div>
		
		<div class="int-area">
			<input type="text" name="Mem_email" id="Mem_email" autocomplete="off" required>
			<label for="mem_email">이메일</label>
		</div>

		<div class="btn-area">
			<button id="btn" type="submit">아이디 찾기</button>
		</div>
	</form>
    </div>
 </section>  
 
<footer class="site-footer clearfix">
	<div class="text-center">
		<a>안영화세요</a><br>Copyright &copy;<script>document.write(new Date().getFullYear());</script>
		All rights reserved 
	</div>
</footer>


</div>
 

<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>