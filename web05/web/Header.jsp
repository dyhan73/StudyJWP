<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1001235
  Date: 15. 5. 21.
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<jsp:useBean id="member" scope="session" class="spms.vo.Member" />--%>
<%
  //Member member = (Member) session.getAttribute("member");
%>
<div style="background-color:#00008b; color:#FFFFFF; height:20px; padding:5px;">
  SPMS (Simple Project Management System)

  <c:if test="${member.email != null}" var="result" scope="session">
  <span style="float:right;">
    ${member.name}
    <%--<%=member.getName()%>--%>
    <a style="color=white;" href="/auth/logout.do">로그아웃</a>
  </span>
  </c:if>
</div>