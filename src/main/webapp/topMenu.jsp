<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
.b-image {
    width: 20px;
    height: 20px;
}
.l-image {
    width: 80px;
}
</style>

<table style="width:90%" align="center">
	<tr>
		<td rowspan="2" align="left" >
		<img src="${contextPath}/image/libraryb.png" class="l-image" alt="로고">
		</td>
		<td colspan="2" align="center">
			&nbsp;
			<c:if test="${ not empty login }">
			[ ${ login.name }(${ login.userid })님 로그인중... ]
			</c:if>
		</td>
		<td align="center">
			<c:choose> 
				<c:when test="${ empty login }"> 
			 		<a href="addUserPage.do">회원가입</a> | 
			 		<a href="loginPage.do">로그인</a>
			 	</c:when>
			 	<c:otherwise> 
			 		<a href="getUserMy.do">마이페이지</a> |
			 		<a href="logout.do">로그아웃</a>
			 	</c:otherwise>
			</c:choose>  
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">환영합니다</td>
		<td align="center">
			<c:if test="${ login.role eq 'admin' }">
				<a href="getBorrowList.do">대출현황</a> |
				<a href="getUserList.do"> 회원목록</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>
		<button>
			<a href="javascript:history.back()">
			<img src="${contextPath}/image/back.png" class="b-image" alt="뒤로가기"></a>
		</button>
		<button>
			<a href="indexPage.do">
			<img src="${contextPath}/image/home.png" class="b-image" alt="홈"></a>
		</button>
		</td>
		<td align="center">
			<a href="getBookList.do">도서목록</a>
			<c:if test="${ login.role eq 'user' }">
			 	| <a href="getBorrowUser.do">대출내역</a>
			</c:if>
		</td>
		<td align="center">
			  <a href="getBoardList.do"> 게시판 </a> 
		</td>
		<td align="center">
			<a href="movieMain.do">영화정보</a>
		</td>
	</tr>
</table>
	





