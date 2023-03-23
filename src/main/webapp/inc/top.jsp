<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<!-- Jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$('#menu > li').mouseenter(function(){
				console.log("mouseenter@@@@@@@@");
				
			var menu_i = $(this).index();
			console.log("@@@@@"+menu_i);
			
			if(menu_i == 2){
				//alert(menu_i);
				$('.menu_pan').css({
					display:'none'
				}).eq($(this).index()-3).css({
					display:'block'
				});
// 				console.log("this"+$(this).index());
// 				console.log(" menu_pan :" + $('.menu_pan').index()-3);
			}
			
			 else {
				$('#menu > li').mouseleave(function(){
					$('.menu_pan').eq($(this).index()-3).css({
						display:'none'
					});
				})
			}  
			
			
		
		});
		
		/*  $('#menu > li').mouseleave(function(){
			$('.menu_pan').eq($(this).index()-3).css({
				display:'none'
			});
		});  */
		
		   /* $('#menu > li').mouseleave(function(){
			
			var menu_i = $(this).index();
					console.log("mouseleave@@@@@@@@");
			
			if(!(menu_i == 2)){
// 			if(menu_i <4){
				console.log("mouseleave@@@@@@@@ : "+ menu_i );
// 				console.log("mouseleave@@@@@@@@ : "+ $(this).index() );
			
				$('.menu_pan').eq($(this).index()-3).css({
					display:'none'
				});
			}
		});    */
		
		  
		$('.menu_pan').mouseenter(function(){
			$('.menu_pan').eq($(this).index()-3).css({
				display:'block'
			});
		});
		$('.menu_pan').mouseleave(function(){
			$('.menu_pan').eq($(this).index()-3).css({
				display:'none'
			});
		});
		
	});

</script>
<!-- Jquery  -->
<nav class="main-nav overlay clearfix">
	<a class="blog-logo" href="./Main.mv"><img src="" alt="Hello Movie"/></a>
	<ul id="menu">
		<li class="nav-home nav-current" role="presentation"><a href="./MovieList.mv">영화</a></li>
		<li class="nav-article-example" role="presentation"><a href="./Booking.bk">예매</a></li>
		<li class="nav-about-us" role="presentation"><a href="#">극장</a>
		
		</li>
		<li class="nav-author-page" role="presentation"><a href="Bonus.mv">혜택</a></li>
		<span class="socialheader">
		<a href="Login.mv"><li class='nav-home nav-current'>로그인</li></a>
		
	<%
	  String id = (String)session.getAttribute("id");
  	  if(id != null){
  	  if(id.equals("admin")){
  	%>
		<a href="MyPageMain.mv"><li class='nav-home nav-current'>마이페이지</li></a>
		<a href="*.mv"><li class='nav-home nav-current' >예매 목록</li></a>
		<a href="*.mv"><li class='nav-home nav-current' >로그아웃</li></a>
		<%
  	  	}
  	  }
  	%>
		
	<%
  	  if(id != null){
  	  if(id.equals("admin")){
  	%>
		<h2> 관리자 메뉴 </h2>
	  	<h3><a href="./MyPageMain.mv">회원목록(List)</a></h3>
  	<%
  	  	}
  	  }
  	%>
		</span>
	</ul>
	<div class="menu_pan">
			<div class="sub_menu">
				<div class="screen_zone"> <a>서울</a> 
					<div> <a href="./Screen.sc?Sc_num=02_1" title="강남">강남점</a> </div>
					<div> <a href="./Screen.sc?Sc_num=02_2" title="명동">명동점</a> </div>
				</div>
			</div>
			
			<div class="sub_menu">
				<div class="screen_zone"> <a>대구</a> 
					<div> <a href="./Screen.sc?Sc_num=053_1" title="대구수성">수성점</a> </div>
					<div> <a href="./Screen.sc?Sc_num=053_2" title="대구현대">현대점</a> </div>
				</div>
			</div>
			
			<div class="sub_menu">
				<div class="screen_zone"> <a>대전</a> 
					<div> <a href="./Screen.sc?Sc_num=042_1" title="대전탄방">탄방점</a> </div>
					<div> <a href="./Screen.sc?Sc_num=042_2" title="대전가오">가오점</a> </div>
				</div>
			</div>
			<div class="sub_menu">
				<div class="screen_zone"> <a>부산</a> 
					<div> <a href="./Screen.sc?Sc_num=051_1" title="부산서면">서면점</a> </div>
					<div> <a href="./Screen.sc?Sc_num=051_2" title="부산해운대">해운대점</a> </div>
				</div>
			</div>
			
			<div class="sub_menu">
				<div class="screen_zone"> <a>광주</a> 
					<div> <a href="./Screen.sc?Sc_num=062_1" title="대구수성">광주점</a> </div>
					<div> <a href="./Screen.sc?Sc_num=062_2" title="대구현대">용봉점</a> </div>
				</div>
			</div>
			
			</div>
	</nav>