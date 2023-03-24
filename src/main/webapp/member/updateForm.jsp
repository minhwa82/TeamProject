<%@page import="member.db.EmailDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="./css/login.css">
<script src="../lib/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" 
href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<link rel="stylesheet" href="./css/css.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>
<script type="text/javascript">
/*global $, document, window, setTimeout, navigator, console, location*/
$(document).ready(function () {


	
	var mType =
			["${dto.mem_mType.split(',')[0] }",
			"${dto.mem_mType.split(',')[1] }",
			"${dto.mem_mType.split(',')[2] }"];	
	
 
	for(var i=0; i<mType.length; i++){
		  $('input:checkbox[name=type]').each(function(){
		    if(this.value == mType[i]){
		    this.checked = true;
		    }
		  });
		}
	
	


    'use strict';
	
     var usernameError = false, 
        emailError    = true,
        emailCodeError= true,
        passwordError = true,
        passConfirm   = true,
    	idError 	  = false,
   		bir_yyError   = true,
    	bir_ddError   = true,
	    phoneError    = false,
	    typeError     = false; 
/*     var usernameError,
        emailError,
        emailCodeError,
        passwordError,
        passConfirm,
    	idError,
   		bir_yyError,
    	bir_ddError,
	    phoneError,
	    typeError; */
	
    var randomCode 	  = "";
    
	var error;
    
	
    

    
    
/*     $(function error() {
        for(var i=0; i<ErrorArray.length; i++) {
        	console.log(i+"번째 에러"+ErrorArray[i]);
        }
    	
    });
     */

    
    
    // Detect browser for css purpose
    if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
        $('.form form label').addClass('fontSwitch');
    }

    // Label effect
    $('input').focus(function () {

        $(this).siblings('label').addClass('active');
        
    });

    
    $('.sendEmail').click(function(){
    	var mem_email = $('.email').val();
    	console.log(mem_email);
    	
    	if(emailError==false) {
    		console.log('이메일 에러없음 - 전송가능한 상태');
    	 $.ajax({
				
				url:"./SendEmailAction.me",
				type:"post",
			 	data:{mem_email:mem_email},
				//dataType:"text",
				success:function(data){
					randomCode = data;
					//console.log(randomCode); // 이메일 인증코드 출력
					
					 $('.emailCode').siblings('span.error').text('인증번호가 전송되었습니다.').fadeIn().parent('.form-group').addClass('hasError');
		             
				},
				error:function(data){
					alert("실패");
					// 페이지 이동 후 실패했을 때 동작
				}
				
			});  
    	} else {
    		console.log('이메일 에러 있음 - 전송불가능');
    		$('.email').blur();
             
    	}
    	
    });
    
    
    // Form validation
    $('input').blur(function () {

        // User Name
        if ($(this).hasClass('name')) {
            if ($(this).val().length === 0) {
                $(this).siblings('span.error').text('이름을 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
                usernameError = true;
/*             } else if ($(this).val().length > 1 && $(this).val().length <= 6) {
                $(this).siblings('span.error').text('Please type at least 6 characters').fadeIn().parent('.form-group').addClass('hasError');
                usernameError = true; */
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                usernameError = false;
                
            }
        }
        
        
     

        
        
        
        // id
      /*   if ($(this).hasClass('id')) {
        	
        	   if ($(this).val().length === 0) {
                   $(this).siblings('span.error').text('아이디를 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
                   idError = true;
                   } else if($(this).val().length > 0) {
		        	var regId = /^[a-z]+[a-z0-9]{5,19}$/g;
		            var mem_id = $(this).val();
			        	 if(!(regId.test($(this).val()))){
			        		 console.log(mem_id);
			            $(this).siblings('span.error').text('6~20자의 영문 소문자, 숫자만 사용 가능합니다.').fadeIn().parent('.form-group').addClass('hasError');
			            idError = true;

			       		 } else {
				            $.ajax({
							//url:"./IdCheckAction",
							url:"./MemberJoinCheckAction.mj",
							type:"post",
						 	data:{mem_id:mem_id},
							//dataType:"text",
							success:function(data){
								//alert("성공");
								//console.log(data);
							
						 		 if(data==1){
						 			 //console.log("값이 1임")
						             $(".id").siblings('span.error').text('사용중인 아이디입니다.').fadeIn().parent('.form-group').addClass('hasError');
						             idError = true;
						
						 		 } else {
						 			$(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
						 			 idError = false;
						 		 }
						         
								},
								error:function(data){
									alert("실패");
									// 페이지 이동 후 실패했을 때 동작
								}
							
								});  
	
				          $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
				 		 idError = false;
			            //console.log(${checkId});
			           
			        	 }
			        }
			    }
 */



        
		
        // Email
        // https://jh91.tistory.com/entry/javescript-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%82%AC
        if ($(this).hasClass('email')) {
        	 
        	if ($(this).val().length === 0) {
        		   
                   $('.emailCode').siblings('span.error').text('이메일을 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
                   emailError = true;
                   
        	}else if ($(this).val().length > 0) {
            	 var regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
            	 var mem_email = $(this).val()
            	 if(regEmail.test($(this).val())){
            		
            		 
            		 
            		 $.ajax({
            				
            				url:"./MemberJoinCheckAction.me",
            				type:"post",
            			 	data:{mem_email:mem_email},
            				//dataType:"text",
            				success:function(data){
            					//alert("성공");
            					console.log(data);
            					
            		 		 if(data==1){
            		 			 //console.log("zz")
            		 			 if($('.email').val()=="${dto.mem_email}") { // 기존 이메일과 같을 경우 emailError false
			            		 console.log('기존과 같음');
			            		 $('.emailCode').siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
			                     emailError = false
			        	 		  } else {
            		             $('.emailCode').siblings('span.error').text('사용중인 이메일입니다.').fadeIn().parent('.form-group').addClass('hasError');
            		 			 emailError = true;
			        	 		  }
            		 		 } else {
            		 			$('.emailCode').siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
            		 			 emailError = false;
            		 		 }
            		         
            				},
            				error:function(data){
            					alert("실패");
            					// 페이지 이동 후 실패했을 때 동작
            				}
            				
            			});  
                $('.emailCode').siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                emailError = false;
   
            	
            	 
        	
            	 } else {
                $('.emailCode').siblings('span.error').text('이메일 주소를 다시 확인해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                emailError = true;
            	 }
        	
        	   
        	   }  
        }
        
			    
 		// email 본인인증
 		if($(this).hasClass('emailCode')) {

 			if(emailError==false)
 			console.log("인증번호: "+randomCode);
	 		if ($(this).val()==randomCode) {
	 			  $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
	 	           emailCodeError = false;
	 		}else {
	 			  
	               $(this).siblings('span.error').text('인증번호를 정확하게 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
	               emailCodeError = true;
	 		}
 		}
		   
 

        // Birth
        
      
        if ($(this).hasClass('bir_yy')) {
            var date = new Date();
            var yyyy = date.getFullYear();
	             
	               if ($(this).val().length <4 ) {
                       $(this).siblings('span.error').text('태어난 년도 4자리를 정확하게 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
                       bir_yyError = true;
                   }
                   else if ($(this).val()> yyyy) {
                       $(this).siblings('span.error').text('태어난 년도가 '+$(this).val()+'년이 맞나요?').fadeIn().parent('.form-group').addClass('hasError');
                       bir_yyError = true;
                   }                  
                   else if ($(this).val()< yyyy-100) {
                       $(this).siblings('span.error').text('태어난 년도가 '+$(this).val()+'년이 맞나요?').fadeIn().parent('.form-group').addClass('hasError');
                       bir_yyError = true;
                   } else {
                     $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                      bir_yyError = false;
                   }
               }
        
        

        
        if($(this).hasClass('bir_dd')){
/*             var input_yy = $('.bir_yy').val();
            var input_mm = $('.bir_mm').val(); */
            var last = new Date($('.bir_yy').val(),$('.bir_mm').val(),0); // 입력받은 년,월의 말일
            var dddd = last.getDate(); // dddd=말일
            //console.log(last);

              if($(this).val().length>0) {
            	if($(this).val()>0){
	              	if($(this).val()>dddd) { // 입력받은 년월의 말일보다 높은 값이 들어왔을 때
		               //$(this).siblings('span.error').text($('.bir_yy').val()+'년'+$('.bir_mm').val()+'월은'+dddd+'까지').fadeIn().parent('.form-group').addClass('hasError');
		               $(this).siblings('span.error').text('다시 입력해주세요.').fadeIn().parent('.form-group').addClass('hasError');
		               bir_ddError = true;
	                 } else { // 정상값일 경우 error 삭제
	                 	$(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
	                 	bir_ddError = false;
	                 }
            	}
              } else { // 0보다 이하인 값이나 숫자가 아닌 값일 들어왔을 때
            	  $(this).siblings('span.error').text('생년월일을 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
	               bir_ddError = true;
              }
        }

         
         


        // PassWord
        if ($(this).hasClass('pass')) {
        	 if ($(this).val().length === 0) {
                 $(this).siblings('span.error').text('비밀번호를 입력하세요.').fadeIn().parent('.form-group').addClass('hasError');
                 passwordError = true;
      	   } else if ($(this).val().length > 0) {
            	var regPass = /^(?=.*?[a-z])(?=.*?[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
            	//var regPass = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
            	 if(regPass.test($(this).val())){
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                passwordError = false;
            	 
	            } else {
	                $(this).siblings('span.error').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.').fadeIn().parent('.form-group').addClass('hasError');
	                passwordError = true;
	            }
            }
        }

        // PassWord confirmation
        
	        if ($('.pass').val() !== $('.passConfirm').val()) {
	            $('.passConfirm').siblings('.error').text('비밀번호가 일치하지 않습니다.').fadeIn().parent('.form-group').addClass('hasError');
	            passConfirm = true;
	        } else {
	            $('.passConfirm').siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
	            passConfirm = false;
	        }
        
        
        
        // 휴대폰 번호
            if ($(this).hasClass('phone1') || $(this).hasClass('phone2') || $(this).hasClass('phone3')) {
            	 var regPhone= /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
            	 var phone1 = $('.phone1').val();
            	 var phone2 = $('.phone2').val();
            	 var phone3 = $('.phone3').val();
            	 var phone = phone1+"-"+phone2+"-"+phone3+"";
              if ((phone1.length+phone2.length+phone3.length) > 0) {
            	 if(regPhone.test($('.phone1').val()+$('.phone2').val()+$('.phone3').val())){
            		 
            		 $.ajax({
         				
         				url:"./MemberJoinCheckAction.me",
         				type:"post",
         			 	data: {phone:phone},
         				dataType:"json",
         				success:function(data){
         					//alert("성공");
         					console.log(data);
         					
         		 		 if(data==1){
         		 			 
            		 		 if(phone=="${dto.mem_phone}") { // 기존 이메일과 같을 경우 emailError false
			            	 console.log('기존과 같음');
			            	 $('.phone3').siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
			                  phoneError = false
			        	 	  } else {         		 			 
	         		            $(".phone3").siblings('span.error').text('등록된 번호입니다.').fadeIn().parent('.form-group').addClass('hasError');
	         		            phoneError = true;
			        	 	  }
         		 		 } else {
         		 			$(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
         		 			 phoneError = false;
         		 		 }
         		         
         				},
         				error:function(data){
         					alert("실패");
         					// 페이지 이동 후 실패했을 때 동작
         				}
         				
         			});  
            		 
            		 
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                phoneError = false;
            	 
            	 } else {
           			$(this).siblings('span.error').text('휴대폰 번호를 다시 확인해주세요').fadeIn().parent('.form-group').addClass('hasError');
          			phoneError = true;
            	 }
              } else {
            	   $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                   phoneError = false;
              }
            }
        
        
        
	         // Type 좋아하는 장르
            $(":checkbox").click(function(){
               var cnt=$("input:checkbox[name=type]:checked").length;
               
           //console.log($(":checkbox(:checked)"));
           //console.log(cnt);

	    	if(cnt>3){
           console.log(cnt);
	    		//$(":checkbox:not(:checked)").attr("disabled", "disabled");
	    		//$(":checkbox:not(:checked)").siblings('.error').text('최대 3개 선택 가능합니다.').fadeIn().parent('.form-group').addClass('hasError');
	    		$(".typeTable").siblings('.error').text('최대 3개 선택 가능합니다.').fadeIn().parent('.form-group').addClass('hasError');
	    		typeError = true;
	    	}else {
	            //$("input[name=type]:checkbox").removeAttr("disabled");
		        $(".typeTable").siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
		        typeError = false;
	    	}
       });
	    	
	        

			
           
           

		     
		        
		        
		           
		     
	    	
        
        
        
        // label effect
        if ($(this).val().length > 0) {
            $(this).siblings('label').addClass('active');
        } else {
            $(this).siblings('label').removeClass('active');
        }
    });


    // form switch
    $('a.switch').click(function (e) {
        $(this).toggleClass('active');
        e.preventDefault();

        if ($('a.switch').hasClass('active')) {
            $(this).parents('.form-peice').addClass('switched').siblings('.form-peice').removeClass('switched');
        } else {
            $(this).parents('.form-peice').removeClass('switched').siblings('.form-peice').addClass('switched');
        }
    });


 // Form submit
     $('form.signup-form').submit(function (event) {
       
        
    	 $('.id, .email, .emailCode, .pass, .passConfirm, .phone3, .bir_yy, .bir_dd, .type').blur();
        
    var ErrorArray =
    	[idError,
        passwordError,
        passConfirm,
        usernameError,
        emailError,
        emailCodeError,
    	bir_yyError,
    	bir_ddError,
        phoneError,
        typeError];
    for(var i=0; i<ErrorArray.length; i++) {
    	
       console.log(i+"번째 에러"+ErrorArray[i]);
       if(ErrorArray[i]==true) {
    	   return false;
       }
    
    }
    
    
           
        //if (idError == true || usernameError == true || emailError == true || emailCodeError == true || passwordError == true || passConfirm == true) {
           if(error==true){
        	   event.preventDefault();
          console.log(error);
        	$('.id, .name, .email, .emailCode, .pass, .passConfirm, .phone3, .bir_yy, .bir_dd, .type').blur();
            //console.log("에러있음"+ErrorTrue);
/*         	  for(var i=0; i<ErrorArray.length; i++) {
              	console.log(i+"번째 에러"+ErrorArray[i]);
              }; */
            
         } 
        
          
          /* else {
            $('.signup, .login').addClass('switched');

            setTimeout(function () { $('.signup, .login').hide(); }, 700);
            setTimeout(function () { $('.brand').addClass('active'); }, 300);
            setTimeout(function () { $('.heading').addClass('active'); }, 600);
            setTimeout(function () { $('.success-msg p').addClass('active'); }, 900);
            setTimeout(function () { $('.success-msg a').addClass('active'); }, 1050);
            setTimeout(function () { $('.form').hide(); }, 700);
        } */
    });   
 
    // Reload page
    $('a.profile').on('click', function () {
        location.reload(true);
    });

    
});


</script>
</head>
<body>
<jsp:include page="../inc/top.jsp"/>
<!-- 
<div class="container">
   <section id="formHolder">

      <div class="row">

         Brand Box
         <div class="col-sm-6 brand">
            <a href="#" class="logo">MR <span>.</span></a>
             
            
 
            <div class="heading">
               <h2>Marina</h2>
               <p>Your Right Choice</p>
            </div>

            <div class="success-msg">
               <p>Great! You are one of our members now</p>
               <a href="#" class="profile">Your Profile</a>
            </div>
         </div>

 -->
         <!-- Form Box -->
         <div class="col-sm-6 form">

<!--             Login Form
            <div class="login form-peice switched">
               <form class="login-form" action="#" method="post">
                  <div class="form-group">
                     <label for="loginemail">Email Adderss</label>
                     <input type="email" name="loginemail" id="loginemail" required>
                  </div>

                  <div class="form-group">
                     <label for="loginPassword">Password</label>
                     <input type="password" name="loginPassword" id="loginPassword" required>
                  </div>

                  <div class="CTA">
                     <input type="submit" value="Login">
                     <a href="#" class="switch">I'm New</a>
                  </div>
               </form>
            </div>End Login Form

 -->
            <!-- Signup Form -->
            <div class="signup form-peice" >
               <form class="signup-form" action="./MemberUpdateProAction.me" method="post">
				
				<h3> ${id }님 회원정보수정 </h3>
				<!-- 회원번호 -->
	<!-- 			 <input type="hidden" name="num" value="0" >
				<input type="hidden" name="grade" value="normal" >
				<input type="hidden" name="joindate" value="" >
				<input type="hidden" name="rCount" value="0" > -->
				
                  <div class="form-group">
                     <label for="userId">아이디</label><br>
                     <input type="text" name="mem_id" id="id" class="id" maxlength="20" value="${id }" readonly><br>
                     <span class="error"></span>
                  </div>
                  
                  <div class="form-group">
                     <label for="password">비밀번호</label><br>
                     <input type="password" name="mem_pw" id="password" class="pass"><br>
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="passwordCon">비밀번호 재확인</label><br>
                     <input type="password" name="passwordCon" id="passwordCon" class="passConfirm"><br>
                     <span class="error"></span>
                   </div>
                   
    
 

                  <div class="form-group">
                     <label for="email">이메일</label><br>
                     <div class="group">
                     <input type="email" name="mem_email" id="email" class="email" placeholder="" value="${dto.mem_email }">
                     <input type="button" value="본인인증" id="sendEmail" class="sendEmail"><br>
                     <span class="error"></span>
                     </div>
                     <input type="text" class="emailCode"><br>
                     <span class="error" ></span>
                  </div>

                 <div class="form-group">
                     <label for="birth">생년월일</label><br>
                     <div class="group">
	      				 <input class="bir_yy" type="text" name="year" id="year" maxlength="4" placeholder="년(4자)" value="${dto.mem_birth.split('-')[0] }">
	                     <select class="bir_mm" name="month" id="month">
	                     <option value="0">월</option>
	                   	    <c:forEach var="mon" begin="1" end="12" step="1">
	                    	   <option value="${mon }"
	                    	  <c:if test="${mon==dto.mem_birth.split('-')[1] }">
	                    	    selected
	                    	    </c:if>
	                    	    >${mon }</option>

	                       </c:forEach>
	                       </select>
	                     <input class="bir_dd" type="text" name="day" id="day" maxlength="2" placeholder="일" value="${dto.mem_birth.split('-')[2] }"><br>
	                 	 <span class="error"></span>
              		</div>
                  </div>
                  
                  
                   
                  
                  
                  
                   <div class="form-group">
                     <label for="phone">휴대폰 번호</label><br>
                     <div class="group">
                     <input type="text" name=phone1 class="phone1" maxlength="3" value="${dto.mem_phone.split('-')[0] }"> -
                     <input type="text" name=phone2 class="phone2" maxlength="4" value="${dto.mem_phone.split('-')[1] }"> -
                     <input type="text" name=phone3 class="phone3" maxlength="4" value="${dto.mem_phone.split('-')[2] }">
                     <span class="error"></span>
                     </div>
                  </div>
                 	
                 
     
                  
				                   
                  <div class="form-group">
                     <label for="addr_num">주소</label>
                     <div class="group">
                     <input type="text" id="postcode" name="postcode" placeholder="우편번호" value="${dto.mem_postcode }"/>
                     <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
                     </div>
                 <!--     <label for="addr">주소</label> -->
                     <input type="text" id="address" name="mem_addr" placeholder="주소" value="${dto.mem_addr1 }"/><br>
                    <!--  <label for="addr_detail">상세주소</label> -->
                    <div class="group">
                     <input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" value="${dto.mem_addr2 }"/>
                     <input type="text" id="sample6_extraAddress" name="extraAddress" placeholder="참고항목" value="${dto.mem_addr3 }">
                     <span class="error"></span>
                    </div>
                  </div>
               
              
            
              
                  <div class="form-group">
                     <label for="type">좋아하는 장르  </label>  <small>(최대 3개 선택가능)</small> <br>
                   <table class="typeTable">
                 
       
                    <tr>
                    	<td><input type="checkbox" name="type" class="type" value="Thriller"> 공포</td>
	                    <td><input type="checkbox" name="type" class="type" value="Romance"> 로맨스</td>
	                    <td><input type="checkbox" name="type" class="type" value="Comedy"> 코미디</td>
	                 </tr>
	                 <tr>
	                    <td><input type="checkbox" name="type" class="type" value="RomCom"> 로맨틱코미디</td>
	                    <td><input type="checkbox" name="type" class="type" value="Mystery"> 미스터리</td>
	                    <td><input type="checkbox" name="type" class="type" value="Adventure"> 모험</td>
	                    </tr>
	                    <tr>
	                    <td><input type="checkbox" name="type" class="type" value="Animation"> 애니메이션</td>
	                    <td><input type="checkbox" name="type" class="type" value="heist"> 범죄</td>
	                    <td><input type="checkbox" name="type" class="type" value="Documentary"> 다큐</td>
                     </tr>
                     <tr>
	                    <td><input type="checkbox" name="type" class="type" value="Fantasy"> 판타지</td>
	                    <td><input type="checkbox" name="type" class="type" value="Action"> 액션</td>
	                    <td><input type="checkbox" name="type" class="type" value="Noir""> 누와르</td>
                     </tr>
                     <tr>
	                    <td><input type="checkbox" name="type" class="type" value="Drama"> 드라마</td>
                    </tr>
                     </table>
                     	
                  	<%-- </c:forEach> --%>
                     <span class="error"></span>
                  </div>
              



                       <div class="CTA">
                     <input type="submit" value="회원정보 수정" id="submit">
                  
                  </div>
                  
               </form>
               
               
               
            </div><!-- End Signup Form -->
         </div>
      </div>

   </section>


<!--    <footer>
      <p>
         Form made by: <a href="http://mohmdhasan.tk" target="_blank">Mohmdhasan.tk</a>
      </p>
   </footer> -->

</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                //변경불가능하게 만들기
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.querySelector("input[name=detailAddress]").focus(); 
            }
        }).open();
    }
</script>
</html>