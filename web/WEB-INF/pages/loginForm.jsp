<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/9/1
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
</head>
<body>
<div>
   <%-- <form action="/api/admin/yuansheng" method="post">--%>  <%--第一种--%>
       <%--<form action="/api/admin/modelAndView" method="post">--%>  <%--第二种--%>
       <%--<form action="/api/admin/model" method="post">--%>  <%--第三种--%>
       <%--<form action="/api/admin/modelMap" method="post">--%> <%--第四种--%>
               <form action="/api/admin/map" method="post">  <%-- 第五种--%>
       用户名: <input type="text" name="adminName"><br/>
       密码: <input type="text" name="adminPwd"><br/>
        <input type="submit" value="登录">
    </form>

</div>


<%-- 第二遍<div>
     <form action="/api/admin/yuansheng" method="post">  第一种
    <<form action="/api/admin/modelAndView" method="post">  第二种-
    <form action="/api/admin/model" method="post"> 第三种
    <form action="/api/admin/modelMap" method="post"> 第四种
    <form action="/api/admin/map" method="post">  第五种
        用户名: <input type="text" name="adminName"><br/>
        密码: <input type="text" name="adminPwd"><br/>
        <input type="submit" value="登录">
    </form>

</div>--%>


<%--三遍<div>
     <form action="/api/admin/yuansheng" method="post">  第一种
    <<form action="/api/admin/modelAndView" method="post">  第二种-
    <form action="/api/admin/model" method="post"> 第三种
    <form action="/api/admin/modelMap" method="post"> 第四种
    <form action="/api/admin/map" method="post">  第五种
        用户名: <input type="text" name="adminName"><br/>
        密码: <input type="text" name="adminPwd"><br/>
        <input type="submit" value="登录">
    </form>

</div>--%>

<%--第四遍<div>
     <form action="/api/admin/yuansheng" method="post">  第一种
    <<form action="/api/admin/modelAndView" method="post">  第二种-
    <form action="/api/admin/model" method="post"> 第三种
    <form action="/api/admin/modelMap" method="post"> 第四种
    <form action="/api/admin/map" method="post">  第五种
        用户名: <input type="text" name="adminName"><br/>
        密码: <input type="text" name="adminPwd"><br/>
        <input type="submit" value="登录">
    </form>

</div>--%>


<%--第五遍<div>
     <form action="/api/admin/yuansheng" method="post">  第一种
    <<form action="/api/admin/modelAndView" method="post">  第二种-
    <form action="/api/admin/model" method="post"> 第三种
    <form action="/api/admin/modelMap" method="post"> 第四种
    <form action="/api/admin/map" method="post">  第五种
        用户名: <input type="text" name="adminName"><br/>
        密码: <input type="text" name="adminPwd"><br/>
        <input type="submit" value="登录">
    </form>

</div>--%>

</body>
</html>
