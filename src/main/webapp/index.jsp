<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>
	<table>
	   <tr><td>kuva</td><td>tiimi</td><td>pisteet</td></tr>
		<c:forEach var="name" items="${names}">
       <tr><td><img height="50%" width="50%" src="/picture/${name}"/></td><td>${name}</td><td>${results.get(name)}</td></tr>
		</c:forEach>
	</table>
</body>
</html>