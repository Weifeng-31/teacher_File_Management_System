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
	<script type="text/javascript" src="../../plugins/frame/js_collegeAdmin/fsDict.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_collegeAdmin/fs.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_collegeAdmin/frame.js"></script>
	<%@ include file="/loginCheckColAdmin.jsp"%>
</head>
<body>
	<div class="layui-fluid">	<form class="layui-form" id="edit_form" loadUrl="">
		<input type="hidden" name="id"/>
		<div class="layui-form-item">
			<label class="layui-form-label">工号</label>
			<div class="layui-input-inline">
				<input type="text" name="userId" required="" lay-verType="tips" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-disabled" disabled="disabled"/>
			</div>
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="name" required="" lay-verType="tips" lay-verify="required" placeholder="请输入名字" autocomplete="off" class="layui-input layui-disabled" disabled="disabled"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色</label>
			<div class="layui-input-inline">
				<select name="roleId" lay-verify="required" lay-verType="tips" class="fsSelect" dict="role_id"  class="layui-input layui-disabled" disabled="disabled">
				</select>
			</div>
			<label class="layui-form-label">学院</label>
			<div class="layui-input-inline">
				<select name="departId" lay-verify="required" lay-verType="tips" class="fsSelect" dict="depart_id"  class="layui-input layui-disabled" disabled="disabled">
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">基本工资</label>
			<div class="layui-input-inline">
				<input type="text" name="baseSalary" required="" lay-verType="tips" lay-verify="required" placeholder="请输入基本工资" autocomplete="off"  class="layui-input"/>
			</div>
			<label class="layui-form-label">奖金</label>
			<div class="layui-input-inline">
				<input type="text" name="bonus" required="" lay-verType="tips" lay-verify="required" placeholder="请输入奖金" autocomplete="off" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">月份</label>
			<div class="layui-input-inline">
				<input type="text" name="month"  lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input fsDate" dateType="datetime" disabled="disabled"/>
			</div>
		</div>
	  <hr/>
		<div class="layui-form-item" style="text-align: center;">
			<button class="layui-btn fsAdd" lay-submit="" lay-filter="save" url="/saveTeacherAction">新增</button>
			<button class="layui-btn fsEdit" lay-submit="" lay-filter="edit" url="/editSalaryAction">编辑</button>
			<!--      	  <button class="layui-btn" lay-submit="" lay-filter="save" url="/fsbus/1006">保存</button> -->
			<button type="button" class="layui-btn layui-btn-primary" function="close">关闭</button>
		</div>
     </form>
	</div>
</body>
</html>
