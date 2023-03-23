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

      <!-- īī�� �α��� ��ư -->
      <a id="kakao-login-btn" href="javascript:kakaoLogin()"> 
      <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
         width="222" alt="īī�� �α��� ��ư" /></a>

      <!-- īī�� ��ũ��Ʈ -->
      <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
      <!-- īī�� ��ũ��Ʈ end -->

      <script>
         Kakao.init('7b8ba339d869370b95d680c83b575837'); // SDK�� �ʱ�ȭ�� / �߱� ���� Ű �� javascriptŰ�� ������ش�.
         console.log(Kakao.isInitialized()); // sdk�ʱ�ȭ�����Ǵ�
         /* īī�� �α��� */
         function kakaoLogin() { // īī�� �α��� ��ư Ŭ���� ����Ǵ� �Լ�  
            Kakao.Auth
                  .login({
                     success : function(response) { // �α��ο� �����ϸ� �޾ƿ��� ������
                        console.log(Kakao.Auth.getAccessToken())
                        console.log(response)
                        Kakao.API
                              .request({
                                 url : '/v2/user/me', // --> �α��ν� url
                                 success : function(response) { // �α��� ������ �޾ƿ� ������
                                   
                                    var name = response.kakao_account.profile.nickname;
                                    var id =   response.id;                         
                                
                                    location.href="KakaoLoginAction.me?memberName=" + name + "&userId=" + id + "&kakao=kakao";
                                  
                                    console.log(response);
                                    console.log(response.id); // �α��� ������ ���� ���� id
                                   
                                 },
                                 fail : function(error) { // �α��� ���н�
                                    console.log(error)
                                 },
                              })
                     },
                     fail : function(error) { // �α��� ���н�
                        console.log(error)
                     },
                  })
         }
         /*  �α��� end */
         /*    �α׾ƿ� */
         function kakaoLogout() {
            console.log("�� ����?" + Kakao.Auth.getAccessToken())
            if (!Kakao.Auth.getAccessToken()) { // �α������� ���� ���¿��� �α׾ƿ� �õ�
               console.log("�� ����?" + Kakao.Auth.getAccessToken())
               alert("�α����� ���ּ���.");
               return;
            }
            console.log("�α׾ƿ� ���� ����" + Kakao.Auth.getAccessToken())
            Kakao.Auth.logout(function() { //  �α����� ���¿��� �α׾ƿ� �õ�
            /*           alert("�α׾ƿ� ���� -> " + Kakao.Auth.getAccessToken()); */
               alert(name + "�� �α׾ƿ��Ǿ����ϴ�.");
            });
         }
         /* �α׾ƿ� end */
         /*ȸ��Ż��*/
         function kakaoDelete() { //  Ż�� ��ư Ŭ���� ����� �Լ�
            if (Kakao.Auth.getAccessToken()) {
               console.log(Kakao.Auth.getAccessToken())
               Kakao.API.request({
                  url : '/v1/user/unlink', // --> Ż��� url
                  success : function(response) {
                     console.log(response)
                     alert("Ż��Ϸ�.");
                  },
                  fail : function(error) {
                     console.log(error)
                  },
               })
               Kakao.Auth.setAccessToken(undefined)
            }
         }
         /*  ȸ��Ż�� end */
      </script>
      <%--------------------------īī�� �α��� End------------------------------%>
  <!--     
īī�� ��ũ��Ʈ
 <a href="javascript:kakaoLogin();">
 <img src="img/kakaoLogo.png" alt="īī������ �α���" style="width: 230px;"/></a>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('7b8ba339d869370b95d680c83b575837'); //�߱޹��� Ű �� javascriptŰ�� ������ش�.
console.log(Kakao.isInitialized()); // sdk�ʱ�ȭ�����Ǵ�
//īī���α���
        function kakaoLogin() {
            window.Kakao.Auth.login({
//                 scope: 'account_email', //�����׸� �������� �ִ� �������� ��ȣ ���̺��� Ȱ��ȭ�� ID���� �ֽ��ϴ�.
                success: function(response) {
                      Kakao.API.request({
                           url : '/v2/user/me',
                           success : function(response) {
                        	   
                               var email=response.kakao_account.email;
                               var name=response.kakao_account.profile.nickname;
                               console.log(response)
                               console.log('�̸���:'+response.kakao_account.email);
                               console.log('�г���:'+response.kakao_account.profile.nickname);                   
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
  
//īī���α׾ƿ�  
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