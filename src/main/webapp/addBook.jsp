<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
table {
  border-collapse: collapse;
}
td {
  padding: 5px;
}
</style>
<script>
	function showAlertAndRedirect() {
		alert('접근 권한이 없습니다.');
		location.href = "indexPage.do";
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/topMenu.jsp" />
	</header>
	<section style="display: flex; justify-content: center;">
		<div>
		<h1>도서 등록</h1>
		<form action="addBook.do" method="post">
			<table border="1">
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<td>저자</td>
					<td><input type="text" name="author" /></td>
				</tr>
				<tr>
					<td>출판사</td>
					<td><input type="text" name="publisher" /></td>
				</tr>
				<tr>
					<td>출판년도</td>
					<td><input type="text" name="publicationyear" pattern="[0-9]+"/></td>
				</tr>
				<tr>
					<td>isbn</td>
					<td><input type="text" name="isbn" maxlength="13"/></td>
				</tr>
				<tr>
					<td>장르</td>
					<td><input type="text" name="category" /></td>
				</tr>
				<tr>
					<td>보유권수</td>
					<td><input type="text" name="totaln" pattern="[0-9]+" /></td>
				</tr>
				<tr>
					<td>대출가능권수</td>
					<td><input type="text" name="availablen" pattern="[0-9]+" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="등록" /></td>
				</tr>
			</table>
		</form>
		</div>
	</section>
	<c:if test="${ login.role != 'admin' }">
		<script>
			showAlertAndRedirect();
		</script>
	</c:if>
</body>
</html>