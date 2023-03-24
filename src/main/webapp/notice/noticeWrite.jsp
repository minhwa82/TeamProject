<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Nectaria - Free HTML Template by WowThemes.net</title>
<style>
/* Form */

.question{width:80%; border-top:5px solid #bdbdbd; border-bottom:5px solid #bdbdbd;font-family:"NanumGothic",sans-serif;}
.question .qtit{text-align:left; margin:100px 0 50px 0;}
.question tr{border-top:1px solid #cdcdcd;}
.question tr:first-child{border-top:0;}
.question th{background:#F6F6F6; width:20%;text-align:left; padding-left:3%}
.question td{text-align:left;background:#F6F6F6;padding-top:1%!important;padding-bottom:10;color:#5c5c5c;font-size:15px;line-height:20px;      linpadding-left:3%}
.question input[type="text"],
.question select{margin-right:1%;color:#5c5c5c;line-height:12px;font-size:14px;font-family:"NanumGothic",sans-serif;background: #white;vertical-align:middle;border:1px solid #cdcdcd;padding:1%;}
.question select{padding:0.9%;}
.question textarea{vertical-align:middle;border:1px solid #cdcdcd; width:94%; height:100px;background:#white;padding:1%}
.question label{margin-right:1%;}
.question .button{margin-right:15%;  line-height:12px;font-size:14px;font-family:"NanumGothic",sans-serif;background:#white;vertical-align:middle;border:1px solid #5e5e5e;padding:1%; cursor:pointer;}
.question .button a{color:#fff;}
.question .add{margin-top:1%;}
.question .wid10{width:20%;}
.question .wid20{width:50%;}
.question .wid30{width:80%;height: 300px;}


.btngreen{margin-top:30px; text-align: right; margin-right: 250px}
.btngreen a{width:8%;margin:0 auto;display:block;padding:0.7em 1.5em 0.8em 1.5em;font-size:16px;color:#fff;border:1px solid #008d2d;border-radius:2em;background:#008d2d; text-align:center;letter-spacing:-0.5px; font-weight:bold;}
.btngreen a:hover{background:#fff;color:#008d2d;}
</style>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>
</head>
<body class="home-template">
<div class="site-wrapper">
	<header>
<jsp:include page="/inc/top.jsp"/>
				<span></span><span></span><span></span>
	<a class="scroll-down icon-arrow-left" href="#content" data-offset="-45"><span class="hidden">Scroll Down</span></a>
	</header>
	<main id="content" class="content" role="main">
	<!-- Form -->
	
	<form action="./NoticeWriteAction.no" method="post">
<input type="hidden" name="Mem_id" >
<table class="question">
    <caption class="qtit"><h2>공지사항</h2></caption>
     <!-- <!--     <tr>
            <th class="th" scope="row">극장선택</th>
            <td><select class="wid10" title="카테고리 선택">
                <option value="극장선택">극장선택</option>
                <option value="서울">서울</option>
                <option value="대전">대전</option>
                <option value="대구">대구</option>
                <option value="부산">부산</option>
            </select>
        </tr>
      --> -->   <tr>
            <th class="th" scope="row">제목</th>
            <td><input type="text" title="제목" class="wid20"  name="No_title" />
            </td>
        </tr>
        <tr>
            <th class="th" scope="row">공지사항</th>
            <td>
                <textarea title="공지사항" class="wid30"  placeholder="1000자 이내(2000byte)로 입력해 주세요" name="No_content"></textarea>
            </td>
        </tr>
  </table>
      
      <div class="btngreen">
         <p> <input type="submit" class="Btn" value="등록하기"></p>
         	
      </div>
         </form>  
         <p> <input type="button" class="Btn" value="돌아가기" onclick="./NoticeListAction.no';"></p>
<!-- //Form -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>