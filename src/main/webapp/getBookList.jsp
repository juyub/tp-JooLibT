<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<body>

	<header>
		<jsp:include page="./topMenu.jsp" />
	</header>

	<section style="display: flex; justify-content: center;">
		<div>
			<c:if test="${ login.role == 'admin'}">
				<button>
					<a href="/JooLib/addBook.jsp">도서등록</a>
				</button>
				<br>
				<br>
			</c:if>

			<form action="searchBook.do" method="post">
				<select name="searchBy" id="searchBy">
					<option value="title">제목</option>
					<option value="author">작가</option>
				</select> <input type="text" name="search"> <input type="submit"
					value="검색">
			</form>
			<br> <br>
			<c:choose>
				<c:when test="${ empty bookList }"> 
			 		도서가 없습니다.
			 	</c:when>
				<c:otherwise>
					<table border="1">
						<tr>
							<td>no</td>
							<td>제목</td>
							<td>저자</td>
							<td>출판사</td>
							<td>장르</td>
						</tr>
						<c:forEach var="book" items="${ bookList }">
							<tr>
								<td>${ book.bookno }</td>
								<td><a href="getBook.do?bookno=${ book.bookno }">${ book.title }</a></td>
								<td>${ book.author }</td>
								<td>${ book.publisher }</td>
								<td>${ book.category }</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>

	</section>

</body>
</html>