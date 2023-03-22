<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<nav class="main-nav overlay clearfix">
	<a class="blog-logo" href="./Main.mv"><img src="assets/img/logo.png" alt="Fashion Critiques"/></a>
	<ul id="menu">
		<li class="nav-home nav-current" role="presentation"><a href="./MovieList.mv">영화</a></li>
		<li class="nav-article-example" role="presentation"><a href="">예매</a></li>
		<li class="nav-about-us" role="presentation"><a href="./CinemaPlace.mv">극장</a></li>

		<li class="nav-author-page" role="presentation"><a href="./Bonus.mv">혜택</a></li>
		<span class="socialheader">
	<%
		  String id = (String)session.getAttribute("id");
		if(id == null){
			
			 %>
		<a href="Login.mv"><li class='nav-home nav-current'>로그인</li></a>
	<%
		}
	%>		
		
	<%
  	  if(id != null){
  	  if(id.equals("admin")){
  	%>
		<a href="./AdminPage.mv"><li class='nav-home nav-current'>관리자페이지</li></a>
		<a href="*.mv"><li class='nav-home nav-current' >예매 목록</li></a>
		<a href="*.mv"><li class='nav-home nav-current' >로그아웃</li></a>
		<%
  	  	}
  	  }
  	%>
		
		</span>
	</ul>
	</nav>