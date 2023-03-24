<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<style type="text/css">

.btn-5{
  backface-visibility: hidden;
  position: relative;
  cursor: pointer;
  display: inline-block;
  white-space: nowrap;
  border-color: #7c7c7c;
  background: linear-gradient(180deg,#e6e6e6 0%,rgba(0, 0, 0, 0.25) 49%, rgba(38, 38, 38, 0.6) 51%,rgba(0, 0, 0, 0.25) 100%);
  border-radius: 5px;
}

h1{
  text-align: left; margin-left: 50px; 
}

    body{
        line-height:2em;        
        font-family:"맑은 고딕";
}
    ul, li{ 
        list-style:none;
        text-align:center;
        padding:0;	
        margin:0;
        
}

    #mainWrapper{
        width: 1000px;
        margin: 200px auto; /*가운데 정렬*/
    }

     #mainWrapper > ul > li:first-child {
        text-align: center;
        font-size:14pt;
        height:40px;
        vertical-align:middle;
        line-height:30px;
} 

    #ulTable {margin-top:30px;}
    

    #ulTable > li:first-child > ul > li {
        background-color:#c9c9c9;
        font-weight:bold;
        text-align:center;
}

    #ulTable > li > ul {
        clear:both;
        padding:0px auto;
        position:relative;
        min-width:40px;
}
    #ulTable > li > ul > li { 
        float:left;
        font-size:10pt;
        border-bottom:1px solid silver;
        vertical-align:baseline;
}    

    #ulTable > li > ul > li:first-child               {width:10%;} /*번호 열 크기*/
    #ulTable > li > ul > li:first-child +li           {width:40%;} /*제목 열 크기*/
    #ulTable > li > ul > li:first-child +li+li        {width:20%;} /*작성자 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li     {width:15%;} /*작성일 열 크기*/
   
   li {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  

}

    #divPaging {
        clear:both; 
        margin:0 auto; 
        width:220px; 
        height:50px;
}

    #divPaging > div {
        float:left;
        width: 50px;
        margin: auto;
        text-align:left;
}

    #liSearchOption {clear:both;}
    #liSearchOption > div {
        margin:0 auto; 
        margin-top: 30px; 
        width:auto; 
        height:100px;

}

    .left {
        text-align : left;
}
.table_search{
  margin-right: 100px;
}

</style>

</head>
<body>
<jsp:include page="../inc/top.jsp"/>
<jsp:include page="../inc/adminCenter.jsp"/>

<div id="mainWrapper">
 <ul>
<h1>공지사항</h1>
<hr>
  
  <table>
  <li>

 	<div align= "right" id="table_search">
	  <form action="./NoticeSearchAction.no" method="post">
		<input type="text" name="search" class="input_box" >
		<input type="submit" value="검색" class="btn">
	  </form>
	</div>
  
	<ul id="ulTable">
		<li>
		  <ul>
		    <li>번호</li>
		    <li>제목</li>
		    <li>작성자</li>
		    <li>등록일</li>
		  </ul> 
		</li>
		
		
		<c:forEach var="dto" items="${noticeList }">    
			<li>
			  <ul>
				<li>${dto.no_num }</li>
				<li class="left">
					<a href="./NoticeContentAction.no?No_num=${dto.no_num }&pageNum=${pageNum}">${dto.no_title }</a>
				</li>				
			    <li>${dto.mem_id }</li>  
			    <li>${dto.no_date }</li>
			  <%
			  String id = (String)session.getAttribute("id");

		  	  if(id != null){
		  	  if(id.equals("admin")){
  	%>
	       <li><a href="./NoticeUpdateAction.no?No_num=${dto.no_num }&pageNum=${pageNum} " style="text-decoration: none;" id="btn-5">수정</a>
	          <a href="./NoticeDeleteAction.no?No_num=${dto.no_num }&pageNum=${pageNum}" style="text-decoration: none;" >삭제</a></li>
  	<%
  	 		 	}
  			  }
  	%> 
			    </ul>
			  </li> 
	    </c:forEach>
 
  </table>
  
  	  <%
			  String id = (String)session.getAttribute("id");

		  	  if(id != null){
		  	  if(id.equals("admin")){
  	%>
	  <div align="right" class="btngreen">
          <a href="./NoticeWrite.no" class="Btn" value="글쓰기">글쓰기</a>
      </div>
		<%
  	 		 	}
  			  }
  	%> 
		
	<div id="page_control">
	
	    <c:if test="${startPage > pageBlock }">
			<a href="./NoticeListAction.no?pageNum=${startPage-pageBlock }">Prev</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
			<a href="./NoticeListAction.no?pageNum=${i }">${i }</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount }">
			<a href="./NoticeListAction.no?pageNum=${startPage+pageBlock }">Next</a>
		</c:if>
	</div>
	</ul>
</article>

<!-- 푸터들어가는 곳 -->
<footer>
<hr>

<jsp:include page="../inc/footer.jsp"/>
</footer>
<!-- 푸터들어가는 곳 -->

</body>
</html>