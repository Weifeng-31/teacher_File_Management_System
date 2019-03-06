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
    <script type="text/javascript" src="../../plugins/frame/js/fsDict.js"></script>
    <script type="text/javascript" src="../../plugins/frame/js/fs.js"></script>
    <script type="text/javascript" src="../../plugins/frame/js/frame.js"></script>
    <%@ include file="/loginCheckSysAdmin.jsp"%>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space1">
        <blockquote class="layui-elem-quote">
            <span style="color: #FF5722;">系统管理员您好！</span>此页面可以新增、编辑和删除学院信息！已有的信息请勿随意修改！
        </blockquote>
        <div class="layui-col-md3">
            <button class="layui-btn layui-btn-normal" function="top" topUrl="views/admin/addDepart.jsp" topMode="add" topWidth="800px"
                    topHeight="200px" topTitle="新增学院信息">
                <i class="layui-icon">&#xe654;</i>新增
            </button>
            <button class="layui-btn layui-btn-danger" function="submit" url="/deleteDepartAction" isMutiDml="1" isConfirm="1"
                    confirmMsg="是否确定删除选中的数据？" inputs="departmentId">
                <i class="layui-icon">&#xe640;</i>删除
            </button>
            <button class="layui-btn" style="background-color:#5FB878" function="refresh">
                <i class="layui-icon">&#x1002;</i>刷新
            </button>
        </div>
        <div class="layui-col-md12">
            <table id="fsDatagrid" lay-filter="fsDatagrid" class="fsDatagrid" isLoad="1" url="/queryDepartDict"
                   isPage="1" defaultForm="query_form" height="full-135" page-size="10" >

            </table>
            <div class="fsDatagridCols">
                <p checkbox="true"/>
                <p type="numbers" title="序号"/>
                <p field="departmentId" title="学院ID" width="80" sort="true"/>
                <p field="departmentName" title="学院名称" width="220" sort="true"/>
                <p fixed="right" align="center" toolbar="#barDemo" title="操作" width="180"/>
            </div>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="top" topUrl="views/admin/editDepart.jsp"
                   topMode="readonly" topWidth="800px" topHeight="200px" topTitle="查看学院信息" inputs="id:">查看</a>
                <a class="layui-btn layui-btn-xs" lay-event="top" topUrl="views/admin/editDepart.jsp" topMode="edit"
                   topWidth="800px" topHeight="200px" topTitle="编辑学院信息" inputs="id:,user_id:,">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="submit" url="/deleteDepartAction" isConfirm="1"
                   confirmMsg="是否确定删除当前记录？" inputs="departmentId:">删除</a>
            </script>
        </div>
    </div>
</div>
</body>
</html>
