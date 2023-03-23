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
        <h1>아이디 찾기 완료</h1>
		<p>회원님의 아이디는 다음과 같습니다.</p>

		<form action="./findIdAction.me" method="post">
			<h1>${dto.mem_id}</h1>
			<div class="btn-area">
				<button id="btn" onclick="location.href='./Login.me'">로그인 바로가기</button>
			</div>
		</form>
     </div>
 </section>
</div>

</body>
</html>