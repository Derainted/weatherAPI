<%@page import="model.WeatherBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather</title>
</head>
<body>

	<div class="container">

		<%
		WeatherBean wBean = (WeatherBean) request.getAttribute("wBean");
		out.print("The weather in " + wBean.getCity() + " is now " + 
		wBean.getCloud() + "<br>" + " Wind is " + wBean.getWind() + "<br>" 
		+ wBean.getDateAndTime());
		%>

		<footer><%@ include file="index.jsp"%></footer>
		
		

	</div>

</body>
</html>