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
<div class="layui-fluid">
	<form class="layui-form" id="edit_form" loadUrl="">
		<input type="hidden" name="id"/>
		<div class="layui-form-item">
			<label class="layui-form-label">角色</label>
			<div class="layui-input-inline">
				<select name="roleId" lay-verify="required" lay-verType="tips" class="fsSelect" dict="role_id">
				</select>
			</div>
			<label class="layui-form-label">学院</label>
			<div class="layui-input-inline">
				<select name="departId" lay-verify="required" lay-verType="tips" class="fsSelect" dict="depart_id">
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">工号</label>
			<div class="layui-input-inline">
				<input type="text" name="userId" required="" lay-verType="tips" lay-verify="required" placeholder="请输入教师工号" autocomplete="off" class="layui-input"/>
			</div>
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="name" required="" lay-verType="tips" lay-verify="required" placeholder="请输入名字" autocomplete="off" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">民族</label>
			<div class="layui-input-inline">
				<input type="text" name="nation" required="" lay-verType="tips" lay-verify="required" placeholder="请输入民族" autocomplete="off" class="layui-input"/>
			</div>
			<label class="layui-form-label">专业</label>
			<div class="layui-input-inline">
				<input type="text" name="subject" required="" lay-verType="tips" lay-verify="required" placeholder="请输入专业" autocomplete="off" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学历</label>
			<div class="layui-input-inline">
				<select name="qualification" lay-verify="required" lay-verType="tips" class="fsSelect" dict="qualification" >
				</select>
			</div>
			<label class="layui-form-label">职称</label>
			<div class="layui-input-inline">
				<select name="professionId" lay-verify="required" lay-verType="tips" class="fsSelect" dict="profession_id">
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">身份证</label>
			<div class="layui-input-inline">
				<input type="text" name="idCard" required="" lay-verType="tips" lay-verify="required" placeholder="请输入身份证号码" autocomplete="off" class="layui-input"/>
			</div>
			<label class="layui-form-label">住址</label>
			<div class="layui-input-inline">
				<input type="text" name="address" required="" lay-verType="tips" lay-verify="required" placeholder="请输入家庭住址" autocomplete="off" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-inline">
				<input type="text" name="phone" required="" lay-verType="tips" lay-verify="required" placeholder="请输入电话" autocomplete="off" class="layui-input"/>
			</div>
			<label class="layui-form-label">电子邮件</label>
			<div class="layui-input-inline">
				<input type="text" name="email" required="" lay-verType="tips" lay-verify="required" placeholder="请输入电子邮件" autocomplete="off" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">政治面貌</label>
			<div class="layui-input-inline">
				<select name="political" lay-verify="required" lay-verType="tips" class="fsSelect" dict="political">
				</select>
			</div>
			<label class="layui-form-label">状态</label>
			<div class="layui-input-inline">
				<input type="checkbox" name="state" lay-skin="switch" lay-text="开启|禁用" value="1" checked="checked">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">头像</label>
			<div class="layui-input-inline">
				<input type="text" id="filePath" name="filePath" autocomplete="off" disabled="disabled" class="layui-input"/>
			</div>
			<div class="layui-input-inline">
				<button type="button"  class="layui-btn" function="upload" fileElem="#filePath" fileAccept="image" fileExts="" fileSize="2048" inputs="type:test">上传头像</button>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea id="description" name="description" placeholder="请输入教师的个人简介" class="fsLayedit" height="80"></textarea>
			</div>
		</div>
		<hr/>
		<div class="layui-form-item" style="text-align: center;">
			<s:set var="userDepartment" value="#session['userDepartment']"/>
			<button class="layui-btn fsAdd" lay-submit="" lay-filter="save" url="/saveCollegeTeacher?userDepartId=<s:property value="#userDepartment.departmentId"/>&departmentName=<s:property value="#userDepartment.departmentName"/>">新增</button>
			<button class="layui-btn fsEdit" lay-submit="" lay-filter="edit" url="/editTeacherAction">保存</button>
			<button type="button" class="layui-btn layui-btn-primary" function="close">关闭</button>
		</div>
     </form>
	</div>
</body>
</html>
