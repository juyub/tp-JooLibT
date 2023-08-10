<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var message = '<%= request.getAttribute("message") %>';
        if (message) {
            alert(message);
            window.location.replace("getBookList.do");
        }
    });
</script>
</head>
<body>

</body>
</html>