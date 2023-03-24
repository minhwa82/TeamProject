<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="./css/login.css">
<script src="../lib/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" 
href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<!-- SNS로그인 관련 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" 
integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

</head>

<body class="post-template page-template page">
<jsp:include page="../inc/top.jsp"/>

<section class="login-form">
   <div class="panel border bg-white">
  <!--s  <h1>안영화세요</h1>	-->
    <img src="assets/img/logo2.png" width="150px" > 
        <form action="./LoginAction.me?" method="post">
        <input type="hidden" name="Mem_num" id="Mem_num">
        <input type="hidden" name="name" >
            <div class="int-area">         
                <input type="text" name="id" id="id" required>
                <label for="id">아이디</label>
            </div>
            <div class="int-area">
                <input type="password" name="pw" id="pw" autocomplete="off" required>
                <label for="pw">비밀번호</label>
            </div>
            <div class="btn-area">
                <button id="btn" type="submit">LOGIN</button>
            </div>
        </form>
        <div class="caption">
            <a href="./findPw.me" id="forgotPw">비밀번호 찾기</a>
            <a href="./findId.me" id="forgotId">아이디 찾기</a><br><br>
            <a href="Join.me" id="">회원가입</a>
        </div>
        
        <div class="mx-3 my-2 py-2 bordert">
			<div class="text-center py-3">						
			<!-- 네이버 로그인 관련 -->
			 <div id="naver_id_login"></div>
			 <!-- 카카오 로그인 버튼 시작 -->
			<a href="javascript:kakaoLogin();">
			<img src="img/kakaoLogo.png" alt="카카오계정 로그인" style="width: 185px;"/></a>
			</div>
		</div>
	</div>
</section>
    
<!-- 네이버 로그인 관련 -->
<script type="text/javascript">
	  	var naver_id_login =
	  		new naver_id_login("Rv8AbbtIDIKKhJ_g5wd4", "http://localhost:8080/TeamProject/NaverLogin.me")
	  	var state = naver_id_login.getUniqState();
	  	naver_id_login.setDomain("http://localhost8080/");
	  	naver_id_login.setButton("green",3,40);
	  	naver_id_login.setState(state);
	  	naver_id_login.init_naver_id_login();
</script>


<!-- 카카오 로그인 폼 시작 -->
<form action="./KakaoLoginAction.me" method="post" id="kl">
	<input type="hidden" id="Mem_email" name="Mem_email" value="">
	<input type="hidden" id="Mem_id" name="Mem_id" value="">
	<input type="hidden" id="Mem_name" name="Mem_name" value="">
</form>
<!-- 카카오 로그인 폼 끝 -->
	
<!-- 카카오 로그인 스크립트 시작 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<script>
Kakao.init('7b8ba339d869370b95d680c83b575837'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	
	//카카오로그인
	function kakaoLogin() {
	    Kakao.Auth.loginForm({
	      success: function (response) {
	        Kakao.API.request({
	          url: '/v2/user/me',
	          success: function (response) {
	        	  console.log(response)
	        	  
	        	  var id = response.id;
	        	  var email = response.kakao_account.email;
	        	  var k_nickname = response.properties.nickname;
	        	  var k_gender = response.kakao_account.gender;
	        	  var birth = response.kakao_account.birthday;
	        	  // 연령대, 생일 가져오기
	        	  
	        	  $('#Mem_email').val(email)
	        	  $('#Mem_id').val(id)
	        	  $('#Mem_name').val(k_nickname)
	        	  $('#kl').submit();
	        	  
 	        	  alert(id)
 	        	  alert(email)
 	        	  alert(birth)
 	        	  alert(k_nickname)

	          },
	          fail: function (error) {
	            console.log(error)
	          },
	        })
	      },
	      fail: function (error) {
	        console.log(error)
	      },
	    })
	  }
	
	//카카오로그아웃  
	function kakaoLogout() {
	    if (Kakao.Auth.getAccessToken()) {
	      Kakao.API.request({
	        url: '/v1/user/unlink',
	        success: function (response) {
	        	console.log(response)
	        	alert('카카오 로그아웃 성공')
	        },
	        fail: function (error) {
	          console.log(error)
	        },
	      })
	      Kakao.Auth.setAccessToken(undefined)
	    }
	  }  
	</script>
<!-- 카카오 로그인 스크립트 끝 -->
 
<footer class="site-footer clearfix">
	<div class="text-center">
		<a>안영화세요</a><br>Copyright &copy;<script>document.write(new Date().getFullYear());</script>
		All rights reserved 
	</div>
</footer>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>