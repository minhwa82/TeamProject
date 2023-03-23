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





</style>
</head>
<body>
<jsp:include page="../inc/top.jsp"/>
<jsp:include page="../inc/adminCenter.jsp"/>
<div id="mainWrapper">
 <ul>
<h1>1:1 문의</h1>
  <li>
    
	<table class=qnaT>
		<tr style="background-color: white;">    
		    <th>번호</th>
		    <th>제목</th>
		    <th>작성자</th>
		    <th>등록일</th>
		</tr>   
		
		<c:forEach var="dto" items="${qnaList }">    
			<tr>
				<td>${dto.q_num }</td>
				<td class="left">
					<a href="./QnaContentAction.qa?Q_num=${dto.q_num }&pageNum=${pageNum}">${dto.mem_subject }</a>
				</td>				
			   
			    <td>${dto.mem_id }</td>
			    <td>${dto.q_date }</td>
			     <%
			  String id = (String)session.getAttribute("id");

		  	  if(id != null){
  	%>
	       <td><a href="./QnaUpdateAction.qa?Q_num=${dto.q_num }&pageNum=${pageNum}" style="text-decoration: none;" >수정</a>
	          <a href="./QnaDeleteAction.qa?Q_num=${dto.q_num }&pageNum=${pageNum}" style="text-decoration: none;" >삭제</a></td>
  	<%
  	 		 	}
  	%> 
			 </tr> 
	    </c:forEach>
      </table>
	  
	      <div align="right" class="btn" value="글쓰기" onclick='location.href="./QnaWrite.qa"'>
	      <button type="submit">글쓰기</button>
      </div>
	

	<div id="page_control">
	
	    <c:if test="${startPage > pageBlock }">
			<a href="./QnaListAction.qa?pageNum=${startPage-pageBlock }">Prev</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
			<a href="./QnaListAction.qa?pageNum=${i }">${i }</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount }">
			<a href="./QnaListAction.qa?pageNum=${startPage+pageBlock }">Next</a>
		</c:if>
	</div>

<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<footer>
<hr>

</footer>
<!-- 푸터들어가는 곳 -->

</body>
</html>