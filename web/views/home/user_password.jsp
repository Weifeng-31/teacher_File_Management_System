<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<meta content="text/html;charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv ="Pragma" content = "no-cache"/>
	<meta http-equiv="Cache-Control" content="no cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="format-detection" content="telephone=no"/>
	<script src="https://cdn.bootcss.com/pace/1.0.2/pace.min.js"></script>
	<link href="https://cdn.bootcss.com/pace/1.0.2/themes/pink/pace-theme-flash.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../plugins/layui/css/layui.css" media="all"/>
	<link rel="stylesheet" type="text/css" href="../../css/fs.css" media="all"/>
	<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../../plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js/fsDict.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js/fs.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js/frame.js"></script>
</head>
<body>
	<s:set var="login" value="#session['login']"/>
	<s:set var="userRole" value="#session['userRole']"/>
	<s:set var="userDepartment" value="#session['userDepartment']"/>
	<s:set var="user_profession" value="#session['user_profession']"/>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space1">
			<blockquote class="layui-elem-quote">
				<span style="color: #FF5722;">您好！</span>此页面可以修改个人密码！
			</blockquote>
		</div>
	<form id="edit_form" loadUrl=""  class="layui-form changePwd">
		<div class="layui-form-item">
			<label class="layui-form-label">工号</label>
			<div class="layui-input-inline">
				<input type="text" name="user_id" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input layui-disabled"  disabled  value="<s:property value="#login.userId"/>"/>
			</div>
		</div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">旧密码</label>
		  <div class="layui-input-inline">
	      <input type="password" name="password" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入旧密码"  />
	    </div>
	  </div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="newPassword" required="" lay-verType="tips" lay-verify="required"  autocomplete="off" class="layui-input" placeholder="请输入新密码" />
			</div>
		</div>
		<div class="layui-form-item">
		  <label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
			  <input name="newPassword1" type="password" lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input" placeholder="请确认新密码" />
		  </div>
		</div>

	  <hr/>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn"  lay-submit="" lay-filter="save" url="/userPasswordModifyAction">立即修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
     </form>
	</div>
</body>

</html>
