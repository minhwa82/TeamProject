<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello Movie</title>
    
    <style type="text/css">
    li{
    	list-style: none;
    }
    </style>
</head>
	
 	<c:forEach var="dateList" items="${dateList }">
		<ul>
			<li id="startTime">
			<input type="radio" name="T_date" value="${dateList.t_date }"> ${dateList.t_date }
			</li>
		</ul>
	</c:forEach>
	</html>
