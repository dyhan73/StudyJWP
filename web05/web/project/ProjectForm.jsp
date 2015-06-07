<%--
  Created by IntelliJ IDEA.
  User: 1001235
  Date: 15. 5. 31.
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>프로젝트 등록</title>
  <style>
    ul { padding:0;}
    li { list-style:none;}
    label {
      float: left;
      text-align: right;
      width: 60px;
    }
  </style>
</head>
<body>
<jsp:include page="/Header.jsp" />
<h1>프로젝트 등록</h1>
<form action="add.do" method="post">
<ul>
  <li><label for="title">제목</label><input id="title" type="text" name="title" size="50"></li>
  <li><label for="content">내용</label><textarea id="content" name="content" rows="5" cols="40"></textarea></li>
  <li><label for="sdate">시작일</label><input id="sdate" type="text" name="startDate" placeholder="예)2015-05-31"></li>
  <li><label for="edate">종료일</label><input id="edate" type="text" name="endDate" placeholder="예)2015-05-31"></li>
  <li><label for="tags">태그</label><input id="tags" type="text" name="tags" size="50" placeholder="예)태그1 태그2 태그3"></li>
</ul>
  <input type='submit' value='추가'>
  <input type='reset' value='취소' onclick='location.href="list.do"'>
</form>
<jsp:include page="/Tail.jsp" />
</body></html>
