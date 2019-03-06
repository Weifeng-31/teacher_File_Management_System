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
        <div class="layui-col-md12">
            <div class="layui-form-query">
                <form class="layui-form" id="query_form" >
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-mid">工号：</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="userId" autocomplete="off" class="layui-input"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-mid">名字：</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="name" autocomplete="off" class="layui-input"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <button class="layui-btn" type="button" function="query" tableId="fsDatagrid" ><i
                                        class="layui-icon">&#xe615;</i>查询（支持模糊查询）
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="layui-col-md3">
            <button class="layui-btn layui-btn-normal" function="top" topUrl="views/collegeAdmin/addSalary.jsp" topMode="add" topWidth="800px"
                    topHeight="300px" topTitle="录入本院教师工资">
                <i class="layui-icon">&#xe654;</i>新增
            </button>
            <button class="layui-btn layui-btn-danger" function="submit" url="/deleteSalaryAction" isMutiDml="1" isConfirm="1"
                    confirmMsg="是否确定删除选中的数据？" inputs="id:">
                <i class="layui-icon">&#xe640;</i>删除
            </button>

            <button class="layui-btn" style="background-color:#5FB878" function="refresh">
                <i class="layui-icon">&#x1002;</i>刷新
            </button>

            <s:set var="userDepartment" value="#session['userDepartment']"/>
            <a class="layui-btn" style="background-color:#1aa094"  href="/exportSalaryExcel?departId=<s:property value="#userDepartment.departmentId"/>&departmentName=<s:property value="#userDepartment.departmentName" />">
                <i class="layui-icon">&#xe61e;</i>导出
            </a>
        </div>
        <div class="layui-col-md12">

             <table id="fsDatagrid" lay-filter="fsDatagrid" class="fsDatagrid" isLoad="1" url="/querySalaryAction?depart_id=<s:property value="#userDepartment.departmentId"/>"
                    isPage="1" defaultForm="query_form" height="full-135" page-size="10" >
            </table>
            <div class="fsDatagridCols">
                <p checkbox="true"/>
                <p type="numbers" title="序号"/>
                <p field="id" title="工资ID" width="90" />
                <p field="userId" title="教师工号" width="120" sort="true"/>
                <p field="name" title="姓名" width="100" />
                <p field="month" title="月份" width="180" />
                <p field="baseSalary" title="基本工资" width="110" />
                <p field="bonus" title="奖金" width="70" />
                <p field="total" title="合计" width="70" />
                <p field="roleId" title="角色" width="120" dict="role_id" />
                <p field="departId" title="学院" width="150" dict="depart_id" />
                <p fixed="right" align="center" toolbar="#barDemo" title="操作" width="180"/>
            </div>
            <script type="text/html" id="stateTpl">
                <input type="checkbox" name="state" lay-skin="switch" disabled lay-text="开启|禁用" {{ d.state == 1 ? 'checked' : '' }}>
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/editSalary.jsp"
                   topMode="readonly" topWidth="800px" topHeight="350px" topTitle="查看工资信息" inputs="id:">查看</a>
                <a class="layui-btn layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/editSalary.jsp" topMode="edit"
                   topWidth="800px" topHeight="350px" topTitle="编辑工资信息" inputs="id:,user_id:,">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="submit" url="/deleteSalaryAction" isConfirm="1"
                   confirmMsg="是否确定删除当前记录？" inputs="id:">删除</a>
            </script>
        </div>
    </div>
</div>
</body>
</html>
