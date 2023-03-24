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
    <style type="text/css">
     [type="radio"] {
  vertical-align: middle;
  appearance: none;
  border: max(2px, 0.1em) solid gray;
  border-radius: 50%;
  width: 1.25em;
  height: 1.25em;
  transition: border 0.5s ease-in-out;
}

[type="radio"]:checked {
  border: 0.4em solid tomato;
}

[type="radio"]:focus-visible {
  outline-offset: max(2px, 0.1em);
  outline: max(2px, 0.1em) dotted tomato;
}

[type="radio"]:hover {
  box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
  cursor: pointer;
}

[type="radio"]:disabled {
  background-color: lightgray;
  box-shadow: none;
  opacity: 0.7;
  cursor: not-allowed;
}

[type="radio"]:disabled + span {
  opacity: 0.7;
  cursor: not-allowed;
}
    </style>
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
		var T_num = null;
		
		console.log(M_num);
		console.log(Sc_num);
		console.log(T_date);
		
		$('input[name=M_num]').on("click", function(){
			M_num = $(this).val();
    		console.log("M_num : "+$(this).val());
    		choiceMovie(M_num);
    		getGrade(M_num,this);
    		//$('input[name=M_num]').val(M_num);
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
					
					$('input[name=T_num]').on('click', function(){
						T_num = $(this).val();
						console.log("T_num : "+T_num);
						//$('input[name=T_num]').val(T_num);
					})
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
						//$('input[name=T_date]').val(T_date);
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
					 
					 $('input[name=Sc_num]').off('click').on('click', function(){
							Sc_num = $(this).val();
							console.log("Sc_num : "+$(this).val());
							
							// 현재 선택된 영화 값을 사용하여 요청
							choiceScreen(M_num, Sc_num);
							// $('input[name=Sc_num]').val(Sc_num); 
						});
				},
				error: function(){
					alert('error');
				}
			});
		} ///choiceMovie
		
	});
	
	function getGrade(movieCd,tag){
		
	$.ajax({
		url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.xml?key=09a2696ef753c4b8196ac759ba9b0007&movieCd=" + movieCd ,
		
		success : function(data) {
			$(data).find('movieInfoResult').each(function() {
				var tmp4 = $(this).find('watchGradeNm').text();
				console.log('등급 : '+tmp4);
				$(tag).append(tmp4);
			})
		}
	});	
}
		
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
                <!-- <script type="text/javascript">
                	getGrade();
                </script> -->
                	<input type="radio" name="M_num"  value="${dto.m_num }" > ${dto.m_name }  <br>
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
                    </div>
                    <div class="theater-place-wrapper">
                    <c:forEach var="scDTO" items="${screenList }">
                        <input type="radio" class="theater-place"  name="Sc_num" value="${scDTO.sc_num }">${scDTO.sc_name } <br>
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
            <!-- <input type="text" name="M_num" value="">
            <input type="hidden" class="movieAge" name="movieAge">
            <input type="text" name="Sc_num" value="">
            <input type="text"  name="T_date" value="">
            <input type="text"  name="T_num" value=""> -->
            <button class="moveSeatButton" type="submit">좌석 선택 하기</button>
            </div> 
        </div>
    </div>
 </form> 
 
 
 
</body>
<!-- <script src="./js/booking.js"></script> -->
	<jsp:include page="../inc/footer.jsp" />
	
	<!-- <div class="text-center">
		Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a>
	</div> -->
	</footer>
</html>