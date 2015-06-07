<%--
  Created by IntelliJ IDEA.
  User: 1001235
  Date: 15. 5. 21.
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html><head><title>회원정보</title></head>
<body>
<jsp:include page="/Header.jsp" />
<h1>회원정보</h1>
<form action='update.do' method='post'>
  번호 : <input type='text' name='no' value='${member.no}' readonly><br>
  이름 : <input type='text' name='name' value='${member.name}'><br>
  이메일 : <input type='text' name='email' value='${member.email}'><br>
  암호 : <input type='text' name='password' value='${member.password}'><br>
  가입일 : ${member.createdDate}<br>
  <input type='submit' value='저장'>
  <input type='button' value='삭제' onclick='location.href="delete.do?no=${member.no}"'>
  <input type='button' value='취소' onclick='location.href="list.do"'>
</form>
<jsp:include page="/Tail.jsp" />
</body></html>
