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
				<span style="color: #FF5722;">您好！</span>此页面可以修改个人资料！
			</blockquote>
		</div>
	<form class="layui-form" id="edit_form" loadUrl="">
		<input type="hidden" name="role_id" value="<s:property value="#userRole.roleID"/>" />
		<input type="hidden" name="depart_id" value="<s:property value="#userDepartment.departmentID"/>" />
		<input type="hidden" id="filePath" name="filePath" />
	  <div class="layui-form-item">
	    <label class="layui-form-label">工号</label>
	    <div class="layui-input-inline">
	      <input type="text" name="user_id" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input layui-disabled" disabled="disabled" value="<s:property value="#login.userID"/>"/>
	    </div>
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="name" required="" lay-verType="tips" lay-verify="required"  autocomplete="off" class="layui-input layui-disabled" disabled="disabled" value="<s:property value="#login.name"/>"/>
			</div>
		  <label class="layui-form-label">用户组</label>
		  <div class="layui-input-inline">
			  <input name="roleName" lay-verify="required" lay-verType="tips"  value="<s:property value="#userRole.rolename"/>"  class="layui-input layui-disabled" disabled="disabled" autocomplete="off"/>
		  </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学院</label>
			<div class="layui-input-inline">
				<input type="text" name="departmentName" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input layui-disabled" disabled="disabled" value="<s:property value="#userDepartment.departmentName" />"/>
			</div>
			<label class="layui-form-label">专业</label>
			<div class="layui-input-inline">
				<input name="subject" lay-verify="required" lay-verType="tips"  value="<s:property value="#login.subject"/>"  class="layui-input" autocomplete="off"/>
			</div>
			<label class="layui-form-label">学历</label>
			<div class="layui-input-inline">
				<select lay-verify="" id="subject" name="qualification" disabled="disabled">
					<option value="1">博士</option>
					<option value="2">硕士</option>
					<option value="3">本科</option>
					<option value="4">专科</option>
					<option value="5">高中</option>
					<option value="6">其他</option>
				</select>
				<script>
                    $(function(){
                        $("#qualification option").each(function() {
                            if($(this).val() == "<s:property value="#user_profession.professionID"/>") {
                                $(this).attr("selected","selected");
                            }else {
                                $(this).removeAttr("selected")
                            }
                        })
                    })
				</script>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">职称</label>
			<div class="layui-input-inline">
				<select lay-verify="" id="profession_id" name="profession_id" disabled="disabled">
					<option value="1">教授</option>
					<option value="2">副教授</option>
					<option value="3">讲师</option>
					<option value="4">助教</option>
				</select>
				<script>
                    $(function(){
                        $("#profession_id option").each(function() {
                            if($(this).val() == "<s:property value="#login.qualification"/>") {
                                $(this).attr("selected","selected");
                            }else {
                                $(this).removeAttr("selected")
                            }
                        })
                    })
				</script>
			</div>
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline">
				<input type="text" name="sex"  lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input layui-disabled" disabled="disabled"value="<s:property value="#login.sex"/>"/>
			</div>
			<label class="layui-form-label">年龄</label>
			<div class="layui-input-inline">
				<input type="text" name="age"  lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input layui-disabled" disabled="disabled"value="<s:property value="#login.age"/>"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">身份证</label>
			<div class="layui-input-inline">
				<input type="text" name="id_card" required="" lay-verType="tips" lay-verify="required"  autocomplete="off" class="layui-input" value="<s:property value="#login.idCard"/>"  class="layui-input"/>
			</div>
			<label class="layui-form-label">籍贯</label>
			<div class="layui-input-inline">
				<input type="text" name="province" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input" value="<s:property value="#login.province"/>"  class="layui-input layui-disabled" disabled="disabled" />
			</div>
			<label class="layui-form-label">出生日期</label>
			<div class="layui-input-inline">
				<input type="text" name="born"  lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input fsDate" dateType="datetime" value="<s:date name="#login.born" format="yyyy-MM-dd HH:mm:ss"/>"  class="layui-input layui-disabled" disabled="disabled"  />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">政治面貌</label>
			<div class="layui-input-inline">
				<select lay-verify="" id="political-select" name="political">
					<option value="1">共产党员</option>
					<option value="2">共青团员</option>
					<option value="3">群众</option>
					<option value="4" >其他党派</option>
				</select>
				<script>
                    $(function(){
                        $("#political-select option").each(function() {
                            if($(this).val() == "<s:property value="#login.political"/>") {
                                $(this).attr("selected","selected");
                            }else {
                                $(this).removeAttr("selected")
                            }
                        })
                    })
				</script>
			</div>
			<label class="layui-form-label">家庭住址</label>
			<div class="layui-input-inline">
				<input type="text" name="address" required="" lay-verType="tips" lay-verify="required"  autocomplete="off" class="layui-input" value="<s:property value="#login.address"/>"/>
			</div>
			<label class="layui-form-label">联系方式</label>
			<div class="layui-input-inline">
				<input type="text" name="phone" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input" value="<s:property value="#login.phone"/>"/>
			</div>
		</div>
		<div class="layui-form-item">

			<label class="layui-form-label">民族</label>
			<div class="layui-input-inline">
				<input type="text" name="nation" required="" lay-verType="tips" lay-verify="required"  autocomplete="off" class="layui-input" value="<s:property value="#login.nation"/>"/>
			</div>
			<label class="layui-form-label">电子邮件</label>
			<div class="layui-input-inline">
				<input type="text" name="email" required="" lay-verType="tips" lay-verify="required" autocomplete="off" class="layui-input" value="<s:property value="#login.email"/>"/>
			</div>
			<div style="float: left">
				<label class="layui-form-label">头像</label>
				<img id="img" src="<s:property value="#login.headUrl"/>" class="layui-nav-img">
				<button type="button"  class="layui-btn" function="upload" fileElem="#filePath" fileAccept="image" fileExts="" fileSize="2048" inputs="type:test">点击上传新头像</button>
			</div>
		</div>
	    <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">备注</label>
	    <div class="layui-input-block">
	      <textarea id="description" name="description" class="fsLayedit" height="80"><s:property value="#login.description"/></textarea>
	    </div>
	     </div>
	  <hr/>
		<div class="layui-form-item" style="text-align: center;">
			 <button class="layui-btn" lay-submit="" lay-filter="save" url="/userInfoModifyAction">保存</button>
		</div>
     </form>
	</div>
</body>

</html>
