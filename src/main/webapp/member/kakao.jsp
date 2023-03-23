<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

      <!-- 카카오 로그인 버튼 -->
      <a id="kakao-login-btn" href="javascript:kakaoLogin()"> 
      <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
         width="222" alt="카카오 로그인 버튼" /></a>

      <!-- 카카오 스크립트 -->
      <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
      <!-- 카카오 스크립트 end -->

      <script>
         Kakao.init('7b8ba339d869370b95d680c83b575837'); // SDK를 초기화함 / 발급 받은 키 중 javascript키를 사용해준다.
         console.log(Kakao.isInitialized()); // sdk초기화여부판단
         /* 카카오 로그인 */
         function kakaoLogin() { // 카카오 로그인 버튼 클릭시 실행되는 함수  
            Kakao.Auth
                  .login({
                     success : function(response) { // 로그인에 성공하면 받아오는 데이터
                        console.log(Kakao.Auth.getAccessToken())
                        console.log(response)
                        Kakao.API
                              .request({
                                 url : '/v2/user/me', // --> 로그인시 url
                                 success : function(response) { // 로그인 성공시 받아올 데이터
                                   
                                    var name = response.kakao_account.profile.nickname;
                                    var id =   response.id;                         
                                
                                    location.href="KakaoLoginAction.me?memberName=" + name + "&userId=" + id + "&kakao=kakao";
                                  
                                    console.log(response);
                                    console.log(response.id); // 로그인 성공한 유저 고유 id
                                   
                                 },
                                 fail : function(error) { // 로그인 실패시
                                    console.log(error)
                                 },
                              })
                     },
                     fail : function(error) { // 로그인 실패시
                        console.log(error)
                     },
                  })
         }
         /*  로그인 end */
         /*    로그아웃 */
         function kakaoLogout() {
            console.log("너 뭐니?" + Kakao.Auth.getAccessToken())
            if (!Kakao.Auth.getAccessToken()) { // 로그인하지 않은 상태에서 로그아웃 시도
               console.log("너 뭐니?" + Kakao.Auth.getAccessToken())
               alert("로그인을 해주세요.");
               return;
            }
            console.log("로그아웃 성공 직전" + Kakao.Auth.getAccessToken())
            Kakao.Auth.logout(function() { //  로그인한 상태에서 로그아웃 시도
            /*           alert("로그아웃 성공 -> " + Kakao.Auth.getAccessToken()); */
               alert(name + "님 로그아웃되었습니다.");
            });
         }
         /* 로그아웃 end */
         /*회원탈퇴*/
         function kakaoDelete() { //  탈퇴 버튼 클릭시 실행될 함수
            if (Kakao.Auth.getAccessToken()) {
               console.log(Kakao.Auth.getAccessToken())
               Kakao.API.request({
                  url : '/v1/user/unlink', // --> 탈퇴시 url
                  success : function(response) {
                     console.log(response)
                     alert("탈퇴완료.");
                  },
                  fail : function(error) {
                     console.log(error)
                  },
               })
               Kakao.Auth.setAccessToken(undefined)
            }
         }
         /*  회원탈퇴 end */
      </script>
      <%--------------------------카카오 로그인 End------------------------------%>
  <!--     
카카오 스크립트
 <a href="javascript:kakaoLogin();">
 <img src="img/kakaoLogo.png" alt="카카오계정 로그인" style="width: 230px;"/></a>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('7b8ba339d869370b95d680c83b575837'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
        function kakaoLogin() {
            window.Kakao.Auth.login({
//                 scope: 'account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function(response) {
                      Kakao.API.request({
                           url : '/v2/user/me',
                           success : function(response) {
                        	   
                               var email=response.kakao_account.email;
                               var name=response.kakao_account.profile.nickname;
                               console.log(response)
                               console.log('이메일:'+response.kakao_account.email);
                               console.log('닉네임:'+response.kakao_account.profile.nickname);                   
//                                location.href="login.do?amid="+email;

                               if(email!=null){
                                   var form=document.forms['login'];
                                   $('#id').val(email);
                                   $('#pw').val(name);
                                   form.action="KakaoLoginAction.me";
                                   form.submit();
                                }
                               
                           },
                           fail : function(error) {
                               console.log(error)
                           },
                       })
                   },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
  
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
           console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }  
</script>
 -->
</body>
</html>