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
<script>
	function checkForm() {
		let borrowForm = document.forms[0];
		let availablen = parseInt("${book.availablen}");
		let borrown = parseInt("${login.borrown}");
		let userno1 = parseInt("${login.userno}");
		let userno2 = parseInt("${borrow.userno}");
		
		if (availablen <= 0) {
			alert('대여가능도서가 없습니다.');
			return false;
		}
		if (borrown <= 0) {
			alert('대출가능권수를 초과하였습니다.');
			return false;
		}
		if (userno1 == userno2) {
		      alert('이미 대출하신 도서입니다');
		      return false;
		}

		return true;
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="./topMenu.jsp" />
	</header>
	
	<section style="display: flex; justify-content: center;">
	<div>
	<form action="updateBook.do" method="post"> 
    <input name="bookno" type="hidden" value="${ book.bookno }"> 
    <table border="1"> 
        <tr> 
            <td>제목</td> 
            <td><input type="text" name="title" value="${book.title}" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr> 
            <td>저자</td> 
            <td><input type="text" name="author" value="${book.author}" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr> 
            <td>출판사</td> 
            <td><input type="text" name="publisher" value="${book.publisher}" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr> 
            <td>출판연도</td> 
            <td><input type="text" name="publicationyear" value="${book.publicationyear}" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr> 
            <td>ISBN</td> 
            <td><input type="text" name="isbn" value="${book.isbn}" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr> 
            <td>장르</td> 
            <td><input type="text" name="category" value="${book.category}" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr>
            <td>보유권수</td>
            <td><input type="text" name="totaln" value="${book.totaln}" ${login.role == 'admin' ? '' : 'readonly'} /></td>
        </tr>
        <tr>
            <td>대여가능권수</td>
            <td><input type="text" name="availablen" value="${book.availablen}" ${login.role == 'admin' ? '' : 'readonly'} />
            </td>
            <td>	
            	<c:choose>
           			<c:when test="${book.availablen == 0}">
                		대여불가
            		</c:when>
           			<c:otherwise>
              		  	대여가능
           		 	</c:otherwise>
        		</c:choose>
            </td>
        </tr>
        <c:if test="${ login.role == 'admin' }">
            <tr>
                <td colspan="3">
                    <input type="submit" value="수정" />
                    <button><a href="deleteBook.do?bookno=${ book.bookno }">삭제</a></button>
                </td>
            </tr>
        </c:if>
    </table>
	</form>
	<c:if test="${ login.role == 'user' }">
	<br>
    <form action="borrowBook.do" method="post" onsubmit="return checkForm()">
        <input name="bookno" type="hidden" value="${ book.bookno }">
        <input name="userno" type="hidden" value="${ login.userno }"> 
        <input name="userno" type="hidden" value="${ borrow.userno }"> 
        <%-- <input name="userno" type="hidden" value="${ login.borrown }">  --%>
        <button type="submit">대출</button>
    </form>
	</c:if>
	</div>
	</section>
	
</body>
</html>
