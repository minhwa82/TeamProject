<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Nectaria - Free HTML Template by WowThemes.net</title>
<style>
/* Form */


.question{margin:auto; width:50%; border-top:5px solid #bdbdbd; border-bottom:5px solid #bdbdbd;font-family:"NanumGothic",sans-serif; }
.question .qtit{text-align:left; margin: 100px auto;}
.question tr{border-top:1px solid #cdcdcd; margin-right: 200px}
.question tr:first-child{border-top:0;}
.question th{background:#F6F6F6; width:20%;text-align:left; padding-left:3%}
.question td{text-align:left;background:#F6F6F6;padding-top:1%!important;padding-bottom:10;color:#5c5c5c;font-size:15px;line-height:20px; linpadding-left:3%}
.question input[type="text"],
.question select{margin-right:1%;color:#5c5c5c;line-height:12px;font-size:14px;font-family:"NanumGothic",sans-serif;background: #white;vertical-align:middle;border:1px solid #cdcdcd;padding:1%;}
.question select{padding:0.9%;}
.question textarea{vertical-align:middle;border:5px solid #cdcdcd; width:94%; height:100px;background:#white;padding:1%}
.question label{margin-right:1%;}
.question .button{margin-right:15%;  line-height:12px;font-size:14px;font-family:"NanumGothic",sans-serif;background:#white;vertical-align:middle;border:1px solid #5e5e5e;padding:1%; cursor:pointer;}
.question .button a{color:#fff;}

.btn{

   margin-right: 150px;

}


</style>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>
</head>
<body class="home-template">

	<header>
<jsp:include page="/inc/top.jsp"/>
				<span></span><span></span><span></span>
	<a class="scroll-down icon-arrow-left" href="#content" data-offset="-45"><span class="hidden">Scroll Down</span></a>
	</header>
	
<form action="./NoticeUpdateProAction.no?No_num=${dto.no_num }&pageNum=${pageNum }" method="post">
<input type="hidden" value="${dto.mem_id }" name="Mem_id">
 <table class="question" id="notice">
  <caption class="qtit"><h2>공지사항</h2></caption> 	 
  <tr>
   <td>글번호</td>
   <td>${dto.no_num }</td>
   </tr>
   <tr>
   <td>글쓴이</td>
   
   <td colspan="2">${dto.mem_id }</td>
   
   <td>작성일</td>
   <td name="No_date" colspan="3" >${dto.no_date }</td>
   </tr>
   <tr>
      <td>제목</td>
        <td colspan="2">
               <input type="text" name="No_title" value="${dto.no_title }">
       </td>
    </tr>
	
     <tr>
      <td>내용</td>
      <td colspan="3">
        <textarea rows="10" cols="30" name="No_content">${dto.no_content }</textarea>

       </td>
	  </tr>
    </table>
        <div class="btn">
        <input type="submit" value="수정하기" class="btn" style="float: right";>       
       </div>
  </form>
<!-- //Form -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>