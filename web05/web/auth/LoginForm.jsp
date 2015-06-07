<%--
  Created by IntelliJ IDEA.
  User: 1001235
  Date: 15. 5. 21.
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
  <h2>사용자 로그인</h2>
  <form action="login.do" method="post">
    이메일 : <input type="text" name="email"><br>
    암호 : <input type="password" name="password"><br>
    <input type="submit" value="로그인">
  </form>
</body>
</html>
