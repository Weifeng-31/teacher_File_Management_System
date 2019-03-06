<%--
  Created by IntelliJ IDEA.
  User: qiyu
  Date: 2018/2/20
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录检测</title>
</head>
<body>
<%
    if(session.getAttribute("login") == null||session.getAttribute("role") !="teacher") {
%>
<script type="text/javascript" language="javascript">
    alert("您无权查看本页面或者登录对话已失效，点击跳转到登录页面！");
    top.location.href="/login.jsp";
</script>
<%
    }
%>
</body>
</html>
