<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
    <title>登录</title>
    <link href="css/login.css" type="text/css" rel="stylesheet">
    <style>
        .errorMessage {text-align: center;margin-bottom: 10px;color: red}

    </style>
</head>
<body>

<div class="login">

    <div class="message">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;教师档案系统后台登录</div>
    <div id="darkbannerwrap"></div>
        <form action="loginAction" method="post">
        <input name="action" value="login" type="hidden">
        <input name="userId" placeholder="用户名"  type="text" value="2014102241" id="userId" required >
        <hr class="hr15">
        <input name="password" placeholder="密码"  type="password" value="123456" id="password" required>
        <hr class="hr15">

        <input name="securityCode" placeholder="验证码" type="securityCode" id="securityCode" value="a" required />
        <img src="SecurityCodeImageAction" id="Verify"  style="cursor:hand;clear: both;float: right;width: 135px;height: 50px;"alt="看不清，换一张"/>
        <hr class="hr15">
        <s:fielderror />
        <input value="登录" style="width:100%;" type="submit" id="button">
        <hr class="hr20">
    </form>

</div>
</body>
<script>
    window.onload=function(){
        var verifyObj = document.getElementById("Verify");
        verifyObj.onclick=function(){
            this.src="Security/SecurityCodeImageAction?timestamp="+new Date().getTime();
        };
    }
</script>
</html>