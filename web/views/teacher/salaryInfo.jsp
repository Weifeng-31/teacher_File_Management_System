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
    <script type="text/javascript" src="../../plugins/echarts/echarts.min.js"></script>
    <%@ include file="/loginCheckTeacher.jsp"%>
</head>
<body>
<s:set var="login" value="#session['login']"/>
<div class="layui-fluid">
    <div class="layui-row layui-col-space1">
        <div class="layui-col-md12">
            <div class="layui-form-query">
                <form class="layui-form" id="query_form" >
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-mid">月份：</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="month"  lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input fsDate" dateType="datetime" />
                            </div>
                        </div>
                        <div class="layui-inline">
                                <button class="layui-btn" type="button" function="query" tableId="fsDatagrid" ><i
                                        class="layui-icon">&#xe615;</i>查询
                                </button>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <a class="layui-btn" style="background-color:#5FB878"  href="/exportTeacherSalaryExcel?userId=<s:property value="#login.userId"/>&name=<s:property value="#login.name"/>">
                                    <i class="layui-icon">&#xe61e;</i>导出
                                </a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="layui-col-md12">
             <table id="fsDatagrid" lay-filter="fsDatagrid" class="fsDatagrid" isLoad="1" url="/queryOwnSalaryAction?userId=<s:property value="#login.userId"/>"
                    isPage="1" defaultForm="query_form" height="full-135" page-size="10" >
            </table>
            <div class="fsDatagridCols">
                <p checkbox="true"/>
                <p type="numbers" title="序号"/>
                <p field="userId" title="教师工号" width="120" sort="true"/>
                <p field="name" title="姓名" width="100" sort="true"/>
                <p field="month" title="月份" width="180" sort="true"/>
                <p field="baseSalary" title="基本工资" width="110" sort="true"/>
                <p field="bonus" title="奖金" width="70" sort="true"/>
                <p field="total" title="合计" width="70" sort="true"/>
                <p field="roleId" title="角色" width="120" dict="role_id" sort="true"/>
                <p field="departId" title="学院" width="150" dict="depart_id" sort="true"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
