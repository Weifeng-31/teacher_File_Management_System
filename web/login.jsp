<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="zh-cn">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
    <title>后台管理</title>
    <link href="login.css" rel="stylesheet" type="text/css" />
    <style>
        .errorMessage {text-align: center;margin-bottom: 10px;color: red}
    </style>
</head>
<body>
<div class="login_box">
      <div class="login_l_img"><img src="images/login-img.png" /></div>
      <div class="login">
          <div class="login_logo"><a href="#"><img src="images/login_logo.png" /></a></div>
          <div class="login_name">
               <p>后台管理系统</p>
          </div>
          <form method="post" action="loginAction">
              <input name="userId" type="text"  value="2014102241"  placeholder="工号" required>
              <input name="password" type="password"  value="123456"  placeholder="密码"  required>
              <input name="securityCode" id="securityCode"  type="securityCode"  value="a" placeholder="验证码" required>
              <img src="SecurityCodeImageAction" id="Verify"  style="cursor:hand;clear: both;float: right;width: 135px;height: 50px;"alt="看不清，换一张"/>
              <s:fielderror />
              <input value="登录" style="width:100%;" type="submit">
          </form>
      </div>
      <div class="copyright">Qiyu版权所有©2018</div>
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
