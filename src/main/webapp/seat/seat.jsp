<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Nectaria - Free HTML Template by WowThemes.net</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>
	
<script type="text/javascript">
	
		
		
		
		 $(function (){
			
			 alert('${SList[0].s_num}');
			 console.log('${SList}');
			 console.log('${SList[0]}');
			 
			 var obj = [ 
				   {"s_num":100,"name":"test"},
				   {"s_num":101,"name":"test"},
				   {"s_num":102,"name":"test"},
				   {"s_num":103,"name":"test"}				 
			 ];
			/*  console.log(obj.s_num); */
			
			var list = '${SList}';
			
			for(var i=0; i<obj.length; i++){
 				alert(obj[i].s_num);
				
			}
			
			// JsonList로 변경
			
			
			
			for(var i=0; i<60; i++){
			
				$('.'+list[i]).on("click",function(){
			        $('.'+list[i]).attr("disabled",true);
			 	}); 
			}
			
			/* $('.a1').on("click",function(){
		        $('.a1').attr("disabled",true);
		    }); */
		    
			/* for(var i=0; i<size; i++){
				console.log(list[i]);
				
				$('.'+list[i]).on("click",function(){
			        $('.'+list[i]).attr("disabled",true);
			    });
			} 
				  */	
		    
		});
		


</script>



</head>
<body class="post-template page-template page">
<div class="site-wrapper">
	<nav class="main-nav overlay clearfix">
	<a class="blog-logo" href="index.html"><img src="assets/img/logo.png" alt="Fashion Critiques"/></a>
	<ul id="menu">
		<li class="nav-home nav-current" role="presentation"><a href="index.html">Home</a></li>
		<li class="nav-article-example" role="presentation"><a href="article.html">Post Example</a></li>
		<li class="nav-about-us" role="presentation"><a href="about.html">Page Example</a></li>
		<li class="nav-author-page" role="presentation"><a href="author.html">Author Page</a></li>
		<span class="socialheader">
		<a href="#"><span class='symbol'>circletwitterbird</span></a>
		<a href="#"><span class='symbol'>circlefacebook</span></a>
		<a href="#"><span class='symbol'>circlegoogleplus</span></a>
		<a href="mailto:wowthemesnet@gmail.com"><span class='symbol'>circleemail</span></a>
		</span>
	</ul>
	</nav>
	<header class="main-header post-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/10/10174958/fas-compressor.jpg)">
	<div class="vertical">
		<div class="main-header-content inner">
			<h1 class="post-title">${param.M_name }</h1>
			<h1>인원/좌석</h1>
		</div>
			
		<form action="./seatBook.st?seat" method="get" name="book">
			<c:forEach items="${SList }" var="SList" varStatus="status">
				
				<c:if test="${SList.s_choice eq 1 }">
					<input type="button" value="${SList.s_num }" style="width:36px" class="${SList.s_num }" disabled="disabled">
				</c:if>
				<c:if test="${SList.s_choice eq 0 }">
					<input type="button" value="${SList.s_num }" style="width:36px" class="${SList.s_num }" name="seat" onclick="select();">
				</c:if>
					
				<c:if test="${status.index==11 || status.index==23 || status.index==35 || status.index==47 || status.index==59 }">
					<br>
				</c:if>
			</c:forEach>
		
		성인   : 
		<c:forEach begin="1" end="8" step="1" var="i">
			<input type="button" value="${i }">
		</c:forEach>
		<br>
		청소년 : 
		<c:forEach begin="1" end="8" step="1" var="i">
			<input type="button" value="${i }">
		</c:forEach>
		
		<hr>
		
		<input type="submit" value="예매하기">
		
		</form>	
		
		<a href="./insertSeat.st">시트등록</a> 
		
		
		
	</div>
	</header>
	
	<footer class="site-footer clearfix">
	<a href="#top" id="back-to-top" class="back-top"></a>
	<div class="text-center">
		Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a>

	</div>
	</footer>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>