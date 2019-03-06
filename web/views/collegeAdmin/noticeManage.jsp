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
<s:set var="login" value="#session['login']"/>
<div class="layui-fluid">
    <div class="layui-row layui-col-space1">
        <blockquote class="layui-elem-quote">
            <span style="color: #FF5722;">院系管理员您好！</span>此页面可以新增、编辑和删除本院教师的公告信息！
        </blockquote>
        <div class="layui-col-md3">
            <button class="layui-btn" style="background-color:#5FB878" function="refresh">
                <i class="layui-icon">&#x1002;</i>刷新
            </button>
        </div>
        <div class="layui-col-md12">
            <table id="fsDatagrid" lay-filter="fsDatagrid" class="fsDatagrid" isLoad="1" url="/queryNoticeAction?roleId=3&departId=<s:property value="#login.departId"/>"
                   isPage="1" defaultForm="query_form" height="full-135" page-size="10" >

            </table>
            <div class="fsDatagridCols">
                <p checkbox="true"/>
                <p type="numbers" title="序号"/>
                <p field="id" title="公告ID" width="80" sort="true"/>
                <p field="roleId" title="角色名称" width="120" sort="true" dict="role_id"/>
                <p field="departId" title="学院" width="150" dict="depart_id" sort="true"/>
                <p field="content" title="公告内容" width="600" sort="true"/>
                <p fixed="right" align="center" toolbar="#barDemo" title="操作" width="120"/>
            </div>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/editNotice.jsp"
                   topMode="readonly" topWidth="800px" topHeight="350px" topTitle="查看公告信息" inputs="id:">查看</a>
                <a class="layui-btn layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/editNotice.jsp" topMode="edit"
                   topWidth="800px" topHeight="350px" topTitle="编辑公告信息" inputs="roleId:,departId:,">编辑</a>
                <!--
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="submit" url="/deleteNoticeAction" isConfirm="1"
                   confirmMsg="是否确定删除当前记录？" inputs="roleId:,id:">删除</a>
                   -->
            </script>
        </div>
    </div>
</div>
</body>
</html>
