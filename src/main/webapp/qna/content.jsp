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
</head>

<body>


<div id="wrap">
  <jsp:include page="../inc/top.jsp"/>
  
  <table id="qna">
  <caption><h1>1:1문의내역</h1></caption> 	 
  <tr>
   <th>글번호</th>
   <td>${dto.q_num }</td>
  </tr>
   
   <tr>
   <th>글쓴이</th>
   <td>${dto.mem_id }</td>
   </tr>
   
   <tr>
   <th>작성일</th>
   <td>${dto.q_date }</td>
   </tr>
   
   <tr>
     <th>제목</th>
     <td colspan="3">${dto.q_subject }</td>
    </tr>

   <tr>
   <th>내용</th>
     <td colspan="3">
     <textarea rows="10" cols="40">${dto.q_content }</textarea>
     </td>
   </tr>
  
  </table>
 
</div>
     <input type="button" value="목록으로" class="btn"
            onclick="location.href='./QnaListAction.qa?pageNum=${pageNum}';">    
</body>
</html>