<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/15
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    成功页面
    ${msg}
    <a href="../../admin.jsp">admin jsp</a>
    <a href="../../user.jsp">user jsp</a>


    欢迎<shiro:principal></shiro:principal>
    <shiro:hasRole name="admin1">
        有admin权限
    </shiro:hasRole>

    <shiro:hasRole name="user">
        有user权限
    </shiro:hasRole>
</body>
</html>
