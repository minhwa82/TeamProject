<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" 
integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

<!-- 카카오 로그인 버튼 시작 -->
<a href="javascript:kakaoLogin();">
<img src="img/kakaoLogo.png" alt="카카오계정 로그인" style="width: 185px;"/></a>

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
	

</body>
</html>