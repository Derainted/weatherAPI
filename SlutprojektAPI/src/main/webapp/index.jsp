<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>The Weather App</title>
</head>
<body>
	
	<!-- Search weather -->
	<div class="container">
	
	<h2>Weather App</h2>
	
		<form action="WeatherServlet" method="get">
			<table>
				<tr>
					<td>City</td>
					<td><input type="text" name="cityname" /></td>
				</tr>

				<tr>
					<td>Country</td>
					<td><input type="text" name="countrycode" /></td>
				</tr>
				<tr>
					<td></td>
					<td id="sub"><input type="submit" value="GO" /></td>
				</tr>

			</table>
		</form>
	</div>
	

</body>
</html>



