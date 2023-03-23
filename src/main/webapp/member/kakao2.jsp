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

<!-- īī�� �α��� ��ư ���� -->
<a href="javascript:kakaoLogin();">
<img src="img/kakaoLogo.png" alt="īī������ �α���" style="width: 185px;"/></a>

<!-- īī�� �α��� �� ���� -->
<form action="./KakaoLoginAction.me" method="post" id="kl">
	<input type="hidden" id="Mem_email" name="Mem_email" value="">
	<input type="hidden" id="Mem_id" name="Mem_id" value="">
	<input type="hidden" id="Mem_name" name="Mem_name" value="">
</form>
<!-- īī�� �α��� �� �� -->

	
<!-- īī�� �α��� ��ũ��Ʈ ���� -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>


<script>
Kakao.init('7b8ba339d869370b95d680c83b575837'); //�߱޹��� Ű �� javascriptŰ�� ������ش�.
	console.log(Kakao.isInitialized()); // sdk�ʱ�ȭ�����Ǵ�
	
	//īī���α���
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
	        	  // ���ɴ�, ���� ��������
	        	  
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
	
	//īī���α׾ƿ�  
	function kakaoLogout() {
	    if (Kakao.Auth.getAccessToken()) {
	      Kakao.API.request({
	        url: '/v1/user/unlink',
	        success: function (response) {
	        	console.log(response)
	        	alert('īī�� �α׾ƿ� ����')
	        },
	        fail: function (error) {
	          console.log(error)
	        },
	      })
	      Kakao.Auth.setAccessToken(undefined)
	    }
	  }  
	</script>
<!-- īī�� �α��� ��ũ��Ʈ �� -->
	

</body>
</html>