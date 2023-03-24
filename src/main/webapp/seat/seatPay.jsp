<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Nectaria - Free HTML Template by WowThemes.net</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<!-- <link rel="shortcut icon" href="#"> -->
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>

<script type="text/javascript">

	$(document).ready(function(){
		
		var IMP = window.IMP;   // 생략 가능
		IMP.init("imp08534032"); // 예: imp00000000 
		
		$('input:button[name=pay]').on('click', function(){
			IMP.request_pay({
	            pg : "html5_inicis.INIpayTest",
	            pay_method : 'card',
	            merchant_uid: "${Book_num }", 
	            name : '${M_name}',
	            amount : 100,
	            buyer_email : 'Iamport@chai.finance',
	            buyer_name : '${M_name}',
	            buyer_tel : '010-1234-5678',
	            buyer_addr : '서울특별시 강남구 삼성동',
	            buyer_postcode : '123-456'
	        }, function (rsp) { // callback
	            if (rsp.success) {
	                console.log(rsp);
	                $('form').submit();
	            } else {
	                console.log(rsp);
	            }
	        });
			
		});
		
		$('input:button[name=test]').on('click', function(){
			$('form').submit();
		});
		
	});
	
</script>

<style>
	#t{
		text-align: right;
	}
</style>


</head>
<body class="post-template page-template page">
<div class="site-wrapper">
	<jsp:include page="../inc/top.jsp" />
	<header class="main-header post-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/10/10174958/fas-compressor.jpg); height:300px">
	<div class="vertical">
		<div class="main-header-content inner">
			<h1 class="post-title"> 결제 </h1>
		</div>
	</div>
	</header>
	<main class="content" role="main" style="height: auto;">
	<article class="post tag-fashion tag-art page">
	<section class="post-content">
	
	
	
	<h2>영화 정보</h2>
	 
	<form action="./bookAction.mv" method="get">
		<input type="hidden" value="${T_num }" name="T_num">
		<input type="hidden" value="${Book_num }" name="Book_num">
		<input type="hidden" value="${M_num }" name="M_num">
		<input type="hidden" value="${Sc_num }" name="Sc_num">
		<input type="hidden" value="${Mem_num }" name="Mem_num">
		<input type="hidden" value="${aNum }" name="aNum">
		<input type="hidden" value="${yNum }" name="yNum">
		<input type="hidden" value="${tPrice }" name="tPrice">
		
		<table>
			<tr>
				<td>영화명</td>
				<td>극장명</td>
				<td>회원명</td>
				<td>좌석번호</td>
				<td>인원</td>
			</tr>
			<tr>
				<td>${M_name }</td>
				<td>${Sc_name }</td>
				<td>${Mem_name }</td>
				<td>
					<c:forEach items="${S_num }" var="S_num">
						<input type="hidden" value="${S_num }" name="S_num">
						${S_num }
					</c:forEach>
				</td>
				<td>
					청소년 :${yNum }<br>
					성인 :${aNum }
				</td>
				
			</tr>
		</table>
		
		<hr color="black">
		
		<h2>할인 정보</h2>
		<c:set value="${price * yNum * 0.1 }" var="yDiscount"/>
		
		<c:choose>
			<c:when test="${Mem_grade eq 'Bronze' }">
				<c:set value="0" var="gDiscount"/>
			</c:when>
			<c:when test="${Mem_grade eq 'Silver' }">
				<c:set value="${price * 0.1 }" var="gDiscount"/>
			</c:when>
			<c:when test="${Mem_grade eq 'Gold' }">
				<c:set value="${price * 0.2 }" var="gDiscount"/>
			</c:when>
		</c:choose>
		<table>
			<tr>
				<td>할인 종류</td>
				<td style="text-align: right;">할인 금액</td>
			</tr>
			<tr>
				<td>청소년 할인</td>
				<td style="text-align: right;">${yDiscount }</td>
			</tr>
			<tr>
				<td>등급 할인(${Mem_grade })</td>
				<td style="text-align: right;">${gDiscount }</td>
			</tr>
		</table>
	
		<hr color="black">
		
		<h2>결제 정보</h2>
		
		<table>
			<tr>
				<td style="text-align: right;">결제할 금액</td>
				<td style="text-align: right;">할인 금액</td>
				<td style="text-align: right;">최종 결제 금액</td>
			</tr>
			<tr>
				<td style="text-align: right;">${tPrice }</td>
				<td style="text-align: right;">${yDiscount + gDiscount }</td>
				<td style="text-align: right;">${tPrice - (yDiscount + gDiscount) }</td>
			</tr>
		</table>
		
		<hr color="black">
		
		<input type="button" value="test" name="test" style="float:right">
		<input type="button" value="결제" name="pay" style="float:right">
	</form>
	
		
	</section>
	</article>
	</main>
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