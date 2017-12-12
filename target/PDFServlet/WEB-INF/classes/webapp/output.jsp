<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Download converted document</title>
</head>
<body>
	<a href="documents4j/<%=request.getAttribute("result")%>" download>Скачать
		файл</a>
	<p>Файл будет доступен в течение 10 минут</p>
</body>
</html>