<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
<script>
	if (confirm("로그아웃 하시겠습니까?")) {
		location.href="/login/logoutOk";
	}else{
		history.back();
	}
</script>
</body>
</html>