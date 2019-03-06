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
	<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
	<script type="text/javascript" src="../../plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../../plugins/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="../../plugins/jquery/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_collegeAdmin/fsDict.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_collegeAdmin/fs.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_collegeAdmin/frame.js"></script>
	<%@ include file="/loginCheckColAdmin.jsp"%>
	<script type="text/javascript">
        $(function(){
            var search = $("#userId");
            var source = new Array();
            search.autocomplete({
                source:source,
            });

            search.keyup(function(){
                $.ajax({
                    type:"POST",
                    url:"/searchAction",
                    data:{userId:$("#userId").val(),depart_id:$("#departId").val()},
                    success:function(data){
                        var obj = eval(data);
                        for(var i = 0; i < obj.data.length; i++){
                            source[i] = obj.data[i][0];
                        }

                    }
                });
            });
            $('#userId').blur(function () {
                $.ajax({
                    type: "post",
                    async: true,
                    url: "/searchAction",
                    data:{userId:$("#userId").val(),depart_id:$("#departId").val()},
                    success: function(data) {
                        if(data.error==0){
                            $('#name').attr('value',data.data[0][1]);
                        }else {
                            alert("输入的教师工号不存在")
                            $('#name').attr('value',"");
						}
                    },
                });
            });
        });
	</script>
</head>
<body>

<div class="layui-fluid">	<form class="layui-form" id="edit_form" loadUrl="">
	<s:set var="userDepartment" value="#session['userDepartment']"/>
	<input type="hidden" name="depart_id" id="departId" value="<s:property value="#userDepartment.departmentId" />"/>
	<div class="layui-form-item">
		<label class="layui-form-label" for="userId">工号</label>
		<div class="layui-input-inline">
			<input type="text" name="userId" id="userId" placeholder="请输入工号" autocomplete="off"  required="" lay-verType="tips"   class="layui-input" />
		</div>
		<label class="layui-form-label">姓名</label>
		<div class="layui-input-inline">
				<input type="text" name="name" id="name" required="" lay-verType="tips" lay-verify="required" placeholder="请输入名字" autocomplete="off" class="layui-input layui-disabled" disabled="disabled" />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">基本工资</label>
		<div class="layui-input-inline">
			<input type="text" name="baseSalary" required="" lay-verType="tips" lay-verify="required" placeholder="请输入基本工资" autocomplete="off" class="layui-input"/>
		</div>
		<label class="layui-form-label">奖金</label>
		<div class="layui-input-inline">
			<input type="text" name="bonus" required="" lay-verType="tips" lay-verify="required" placeholder="请输入奖金" autocomplete="off" class="layui-input"/>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">月份</label>
		<div class="layui-input-inline">
			<input type="text" name="month"  lay-verify="required" lay-verType="tips" autocomplete="off" placeholder="请输入月份" class="layui-input fsDate" dateType="datetime" />
		</div>
	</div>
	<hr/>
	<div class="layui-form-item" style="text-align: center;">
		<button class="layui-btn fsAdd" lay-submit="" lay-filter="save" url="/saveSalaryAction">新增</button>
		<button class="layui-btn fsEdit" lay-submit="" lay-filter="edit" url="/editSalaryAction">编辑</button>
		<button type="button" class="layui-btn layui-btn-primary" function="close">关闭</button>
	</div>
</form>
</div>
</body>
</html>
