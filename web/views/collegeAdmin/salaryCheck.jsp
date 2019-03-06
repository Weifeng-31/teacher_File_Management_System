<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no cache"/>
    <meta http-equiv="Expires" content="0"/>
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
    <div class="layui-row layui-col-space1">
        <blockquote class="layui-elem-quote explain">
            <p style="color:#f00;">尊敬的院系管理员您好！当前页面显示的为本学院从未添加工资记录的教师！

                <button class="layui-btn" style="background-color:#5FB878" function="refresh">
                    <i class="layui-icon">&#x1002;</i>刷新表格
                </button>

            </p>

        </blockquote>
        <div class="layui-col-md3">

        </div>
        <div class="layui-col-md12">
            <s:set var="userDepartment" value="#session['userDepartment']"/>
            <table id="fsDatagrid" lay-filter="fsDatagrid" class="fsDatagrid" isLoad="1" url="/checkSalaryAction?depart_id=<s:property value="#userDepartment.departmentId"/>"
                   isPage="1" defaultForm="query_form" height="full-135" page-size="10" >
            </table>
            <div class="fsDatagridCols">
                <p checkbox="true"/>
                <p type="numbers" title="序号"/>
                <p field="userId" title="教师工号" width="120" sort="true"/>
                <p field="name" title="姓名" width="100" sort="true"/>
                <p field="roleId" title="角色" width="120" dict="role_id" sort="true"/>
                <p field="departId" title="学院" width="150" dict="depart_id" sort="true"/>
                <p fixed="right" align="center" toolbar="#barDemo" title="操作" width="180"/>
            </div>
            <script type="text/html" id="stateTpl">
                <input type="checkbox" name="state" lay-skin="switch" disabled lay-text="开启|禁用" {{ d.state == 1 ? 'checked' : '' }}>
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/addSalaryByTool.jsp" topMode="edit"
                   topWidth="800px" topHeight="350px" topTitle="增加教师工资" inputs="id:,userId:,">增加工资记录</a>
            </script>
        </div>
    </div>
</div>
</body>
</html>
