<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:forEach var="timeList" items="${timeList }">
		<ul>
			<li id="startTime">
			<input type="radio" name="T_num" value="${timeList.t_num }"> ${timeList.t_startTime }</li>
		</ul>
	</c:forEach>
	</html>
