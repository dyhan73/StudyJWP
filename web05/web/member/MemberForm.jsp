<%--
  Created by IntelliJ IDEA.
  User: 1001235
  Date: 15. 5. 21.
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원등록</title>
</head>
<body>
<jsp:include page="/Header.jsp" />
<h1>회원 등록</h1>
<form action='add.do' method='post'>
  이름: <input type='text' name='name'><br>
  이메일: <input type='text' name='email'><br>
  암호: <input type='text' name='password'><br>
  <input type='submit' value='추가'>
  <input type='reset' value='취소' onclick='location.href="list.do"'>
</form>
<jsp:include page="/Tail.jsp" />
</body></html>
