<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			var aPeople = 0;
			var yPeople = 0;
			var sum = 0;
			var cnt = 0;
			var save = 0;

			$('input:button[name=adult]').on('click', function(){
				aPeople = $(this).attr('value');
				cnt = $('input:checkbox[id=c]:checked').length;
				sum = parseInt(aPeople) + parseInt(yPeople);
				
				if(cnt>sum){
					alert('좌석취소 후 인원 조정');
					aPeople = save;
					return false;
				}
				$('input:button[name=adult]').css('background', 'white');
				$(this).css('background', 'blue');
				save = $(this).attr('value');
				$('input:hidden[name=aNum]').val(save);
			}),
				
			$('input:button[name=youth]').on('click', function(){
				yPeople = $(this).attr('value');
				cnt = $('input:checkbox[id=c]:checked').length;
				sum = parseInt(aPeople) + parseInt(yPeople);
				
				if(cnt>sum){
					alert('좌석취소 후 인원 조정');
					yPeople = save;
					return false;
				}
				$('input:button[name=youth]').css('background', 'white');
				$(this).css('background', 'blue');
				save = $(this).attr('value');
				$('input:hidden[name=yNum]').val(save);
				
			}),
				
			$('input:checkbox[id=c]').click(function(){
				cnt = $('input:checkbox[id=c]:checked').length;
				
				sum = parseInt(aPeople) + parseInt(yPeople);
				if(sum == 0){
					alert('인원수 선택');
					$('input:checkbox[id=c]').checked = false;
					return false;
				}
					
				if(cnt>sum){
					alert("인원초과");
					
					return false;
				}
				let tmp = $(this);
				if(tmp.is(':checked') == true){
					
					$.ajax({
						url:'./seat/SeatDB.jsp',
						type:'get',
						data:{'S_num':$(this).attr('value'),
							  'T_num':${T_num},'Sc_num':$('input:hidden[name=Sc_num]').val(),
							  'M_num':$('input:hidden[name=M_num]').val()
							  },
						dataType:'json',
						success: function(data){
							if(data.alt == "예매중인 좌석"){
								alert('예매중인 좌석');
								tmp.attr("checked",false);
							}
						},
						error:function(data, status, opt){
							console.log("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+opt);
						}
					});
				}
				if($(this).is(':checked') == false){
					
					$.ajax({
						url:'./seat/SeatDB2.jsp',
						type:'get',
						data:{'S_num':$(this).attr('value'),
							  'T_num':${T_num},'Sc_num':$('input:hidden[name=Sc_num]').val(),
							  'M_num':$('input:hidden[name=M_num]').val()
							  },
						dataType:'json',
						success: function(data){
							if(data.alt == "예매중인 좌석"){
								alert('체크풀고 디비 변경');
							}
						},
						error:function(data, status, opt){
							console.log("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+opt);
						}	  
					});
				}
			});
			
			$('#form').submit(function(){
				//alert("cnt:"+cnt+"sum:"+sum);
				if(cnt != sum){
					alert('인원수와 선택좌석수가 다름');
					return false;
				}
				if(cnt == 0){
					alert('인원 / 좌석 선택 후 예매');
					return false;
				}
				
			});
		});
</script>

<style>
	#b{
		width:25px;
		height:25px;
		
	}
	#b1{
		width:25px;
		height:25px;
		background:blue;
		
	}

	#c{
		width:25px;
		height:30px;
		position: relative;
        top: 9px;
	}
	#s{
		color: white;
		
	}
</style>




</head>
<body class="post-template page-template page">
<div class="site-wrapper">
	<jsp:include page="../inc/top.jsp"/>
	<header class="main-header post-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/10/10174958/fas-compressor.jpg)">
	<div class="vertical">
		<div class="main-header-content inner">
			<h2 class="post-title"  style="color:white;">${M_name }</h2>
			<h2 style="color:white;">인원/좌석</h2>
			<h4 style="color:white;">${Scdto.sc_zone } ${Scdto.sc_name } / ${tdto.t_date } / ${tdto.t_startTime } ~ ${tdto.t_endTime }</h4>
		</div>
		
		
			
		<form action="./seatBook.st" method="get" id="form">
		<input type="hidden" value="${T_num }" name="T_num">
		<input type="hidden" value="${Sc_num }" name="Sc_num">
		<input type="hidden" value="${param.M_num }" name="M_num">
		<input type="hidden" value="${M_name }" name="M_name">
		<input type="button" value="SCREEN" disabled="disabled" style="width:25%;"><br>
		
			<c:forEach items="${SList }" var="SList" varStatus="status">
				
				<c:if test="${SList.s_choice eq 2 }">
					<input type="button" value="X" id="b1" class="${SList.s_num }" disabled="disabled">
				</c:if>
				
				<c:if test="${SList.s_choice eq 1 }">
					<input type="button" value="X" id="b" class="${SList.s_num }" disabled="disabled">
				</c:if>
				<c:if test="${SList.s_choice eq 0 }">
					<input type="checkbox" id="c" value="${SList.s_num }" class="${SList.s_num }" name="seat">
				</c:if>
					
				<c:if test="${status.index==11 || status.index==23 || status.index==35 || status.index==47 || status.index==59 }">
					<br>
				</c:if>
			</c:forEach>
		
			<span id="s">성인   : </span>
			<c:forEach begin="1" end="8" step="1" var="i">
				<input type="button" value="${i }" name="adult">	
			</c:forEach>
			<br>
			<span id="s">청소년 : </span>
			<c:forEach begin="1" end="8" step="1" var="i">
				<input type="button" value="${i }" name="youth">
			</c:forEach>

			<input type="hidden" value="" name="aNum">
			<input type="hidden" value="" name="yNum">
			<input type="hidden" value="${T_num }" name="T_num">
			<hr>
			<input type="submit" value="예매하기">
		
		</form>	
		
		
		
		
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