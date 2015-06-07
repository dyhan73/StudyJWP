<%--
  Created by IntelliJ IDEA.
  User: 1001235
  Date: 15. 5. 21.
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page import="spms.vo.Member"%>--%>
<%--<%@ page import="java.util.ArrayList"%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp" />
<h1>회원목록</h1>
<p><a href='add.do'>신규회원</a></p>

<%--<jsp:useBean id="members" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<spms.vo.Member>"/>--%>
<%
  //ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
  //for (Member member : members) {
%>

<c:forEach var="member" items="${members}">
    ${member.no},
    <a href='update.do?no=${member.no}'>
        ${member.name}</a>,
    ${member.email},
    ${member.createdDate},
    ${member.modifiedDate},
    <a href='delete.do?no=${member.no}'>[삭제]</a><br>
</c:forEach>

<jsp:include page="/Tail.jsp" />
</body>
</html>
