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
	<%@ include file="/loginCheckSysAdmin.jsp"%>
</head>
<body>
	<div class="layui-fluid">
	<form class="layui-form" id="edit_form" loadUrl="">
	<input type="hidden" name="id"/>
		<div class="layui-form-item">
			<label class="layui-form-label">角色ID</label>
			<div class="layui-input-inline">
				<input type="text" name="roleId" required="" lay-verType="tips" lay-verify="required" placeholder="请输入角色ID" autocomplete="off"  class="layui-input" />
			</div>
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-inline">
				<input type="text" name="roleName" required="" lay-verType="tips" lay-verify="required" placeholder="请输入角色名称" autocomplete="off"   class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block">
				<textarea id="description" name="description" placeholder="请输入角色描述" class="fsLayedit" height="80"></textarea>
			</div>
		</div>
		<hr class="layui-bg-gray">
		<div class="layui-form-item" style="text-align: center;">
			<button class="layui-btn fsAdd" lay-submit="" lay-filter="save" url="/addRoleAction">新增</button>
			<button class="layui-btn fsEdit" lay-submit="" lay-filter="edit" url="/editRoleAction">编辑</button>
			<!--      	  <button class="layui-btn" lay-submit="" lay-filter="save" url="/fsbus/1006">保存</button> -->
			<button type="button" class="layui-btn layui-btn-primary" function="close">关闭</button>
		</div>
     </form>
	</div>
</body>
</html>
