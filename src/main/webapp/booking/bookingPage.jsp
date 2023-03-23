<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello Movie</title>
    
 	<link rel="stylesheet" type="text/css" href="./css/screen.css"/>
 	<link rel="stylesheet" type="text/css" href="./css/booking.css"/>
    <!-- <link rel="stylesheet" href="css/reserve.css"> -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css' />
    <script src='//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js'></script>
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
    <!-- MATERIAL DESIGN ICONIC FONT -->
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"
        integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script type="text/javascript">
	$(function(){
		//var M_num = $('input[name=M_num]').val();
		//var Sc_num = $('input[name=Sc_num]').val();
		var M_num = null;
		var Sc_num = null;
		var T_date = null;
		
		console.log(M_num);
		console.log(Sc_num);
		console.log(T_date);
		
		$('input[name=M_name]').on("click", function(){
			M_num = $(this).val();
    		console.log("M_num : "+$(this).val());
    		choiceMovie(M_num);
    	});
		
		function choiceDate(val1, val2, val3){
			let movieNum=val1;
			let screenNum=val2;
			let movieDate=val3;
			
			$.ajax({
				url:'./DateChoice.bk',
				type:'post',
				data:{
					M_num: movieNum,
					Sc_num: screenNum,
					T_date: movieDate
				},
				success:function(data){
					alert('시간 정보 가져옴')
					console.log("data : "+data);
					$('.reserve-time-wrapper').empty().append(data);
				},
				error:function(){
					alert('error');
				}
			});
		} //choiceDate

		function choiceScreen(val1, val2){
    		let movieNum=val1;
    		let screenNum=val2;
    		
			$.ajax({
				url:'./ScreenChoice.bk',
				type:'post',
				data:{M_num: movieNum, Sc_num: screenNum},
				success:function(data){
					alert('날짜 정보 가져옴');
					console.log("data :"+data);
					
					$('.reserve-date').empty().append(data);
					
					$('input[name=T_date]').on('click', function(){
						T_date = $(this).val();
						//console.log("T_date : "+$(this).val());
						
						choiceDate(M_num, Sc_num, T_date);
					});
				},
				error:function(){
					alert('error');
				}
			});
		} ///choiceScreen
		
		function choiceMovie(val1){
			M_num = val1;
			let movieNum=val1;
			
			$.ajax({
				url:'./MovieChoice.bk',
				type:'post',
				data:{M_num:movieNum},
				success:function(data){
					console.log("data : "+data);
					
					$('.theater-place-wrapper').empty().append(data);
					 
					 $('input[name=Sc_name]').off('click').on('click', function(){
							Sc_num = $(this).val();
							console.log("Sc_num : "+$(this).val());
							
							// 현재 선택된 영화 값을 사용하여 요청
							choiceScreen(M_num, Sc_num);
						});
				},
				error: function(){
					alert('error');
				}
			});
		} ///choiceMovie
		
	});
    </script>
</head>

<body>
<jsp:include page="../inc/top.jsp" />
  <form class="moveSeatForm" action="./SeatChoice.st" method="get">
    <div class="reserve-container">
    <c:set var="dto" value="${dailyList }" />
    <c:set var="scDTO" value="${screenList }"/>
       <div class="movie-part">
            <div class="reserve-title">영화</div>
            <div class="sort-wrapper">
                <div class="sort-rate sort-selected">예매율순</div>
                
            </div>
                <div class="theater-movie">
                <c:forEach var="dto" items="${dailyList }">
                	<input type="hidden" name="M_num" value="${dto.m_num }">
                	<input type="button" name="M_name"  value="${dto.m_num }">${dto.m_name }  <br>
                </c:forEach>
                </div>
            <!--  <div class="movie-list-wrapper">
            </div> -->
        </div>
        <div class="theater-part">
            <div class="reserve-title">
                극장
            </div>
            <div class="theater-container">
                <div class="theater-wrapper">
                    <div class="theater-location-wrapper">
                        <!-- <button class="theater-location">서울</button>
                        <button class="theater-location">대전</button>
                        <button class="theater-location">대구</button>
                        <button class="theater-location">부산</button>
                        <button class="theater-location">광주</button> -->
                    </div>
                    <div class="theater-place-wrapper">
                    <c:forEach var="scDTO" items="${screenList }">
                    	<input type="hidden" value="${scDTO.sc_num }">
                        <input type="button" class="theater-place"  name="Sc_name" value="${scDTO.sc_num }">${scDTO.sc_name }
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="day-part">
            <div class="reserve-title">날짜</div>
            <div class="reserve-date">
             <!-- <button name="movieDate"></button> -->
            </div>
        </div>
        <div class="time-part">
            <div class="reserve-title">시간</div>
            <div class="reserve-time">
                <div class="reserve-time-wrapper">
                </div>
            </div>
			
            <div>
            <%--  <input type="hidden" name="M_num" value="${dto.m_num }">
            <input type="hidden" class="movieAge" name="movieAge">
            <input type="hidden" name="Sc_num" value="${scDTO.sc_num }"> --%>
            <input type="hidden" class="reserveDate" name="movieDate">
            <!-- <input type="hidden" class="runningTime" name="startTime" value=""> -->
            <button class="moveSeatButton" type="submit">좌석 선택 하기</button>
            </div> 
        </div>
    </div>
 </form> 
 
 
 
</body>
<!-- <script src="./js/booking.js"></script> -->
    <footer class="site-footer clearfix">
	<a href="#top" id="back-to-top" class="back-top"></a>
	
	<!-- <div class="text-center">
		Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a>
	</div> -->
	</footer>
</html>