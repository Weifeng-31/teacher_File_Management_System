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
        <s:set var="userDepartment" value="#session['userDepartment']"/>
        <s:set var="login" value="#session['login']"/>
        <div class="layui-col-md3">
            <button class="layui-btn layui-btn-normal" function="top" topUrl="views/collegeAdmin/addTeacher.jsp" topMode="add" topWidth="800px"
                    topHeight="680px" topTitle="新增本院教师">
                <i class="layui-icon">&#xe654;</i>新增
            </button>
            <button class="layui-btn layui-btn-danger" function="submit" url="/deleteTeacherAction?ownUserId=<s:property value="#login.userId"/>" isMutiDml="1" isConfirm="1"
                    confirmMsg="是否确定删除选中的数据？" inputs="userId">
                <i class="layui-icon">&#xe640;</i>删除
            </button>
            <button class="layui-btn" style="background-color:#5FB878" function="refresh">
                <i class="layui-icon">&#x1002;</i>刷新
            </button>
            <a class="layui-btn" style="background-color:#1aa094"  href="/exportCollegeTeacherExcel?departId=<s:property value="#userDepartment.departmentId"/>&departmentName=<s:property value="#userDepartment.departmentName" />">
                <i class="layui-icon">&#xe61e;</i>导出
            </a>
        </div>
        <div class="layui-col-md12">
            <table id="fsDatagrid" lay-filter="fsDatagrid" class="fsDatagrid" isLoad="1" url="/queryCollegeTeacherAction?departId=<s:property value="#userDepartment.departmentId"/>"
                   isPage="1" defaultForm="query_form" height="full-135" page-size="10" >

            </table>
            <div class="fsDatagridCols">
                <p checkbox="true"/>
                <p type="numbers" title="序号"/>
                <p field="userId" title="工号" width="110" sort="true"/>
                <p field="name" title="姓名" width="80" sort="true"/>
                <p field="sex" title="性别" width="70"/>
                <p field="age" title="年龄" width="70" />
                <p field="phone" title="电话" width="130" sort="true"/>
                <p field="email" title="电子邮件" width="130" sort="true"/>
                <p field="nation" title="民族" width="70" sort="true"/>
                <p field="subject" title="专业" width="150" sort="true"/>
                <p field="headUrl" title="头像" width="65" templet="#show_img"/>
                <p field="born" title="出生日期" width="110" />
                <p field="idCard" title="身份证" width="180"/>
                <p field="province" title="籍贯" width="120"/>
                <p field="address" title="住址" width="200"/>
                <p field="roleId" title="角色" width="110" dict="role_id" sort="true"/>
                <p field="qualification" title="学历" width="80" sort="true" dict="qualification"/>
                <p field="professionId" title="职称" width="80" dict="profession_id" sort="true"/>
                <p field="political" title="政治面貌" width="100" dict="political" sort="true"/>
                <p field="departId" title="学院" width="150" dict="depart_id" sort="true"/>
                <p field="state" title="状态" width="90" templet="#stateTpl"/>
                <p fixed="right" align="center" toolbar="#barDemo" title="操作" width="240"/>
            </div>
            <style>.layui-table-cell img{width:28px;height:28px;margin-right:10px;border-radius:50%;}
            </style>
            <script type="text/html" id="show_img"><img src="{{d.headUrl}}"/></script>
            <script type="text/html" id="stateTpl">
                <input type="checkbox" name="state" lay-skin="switch" disabled lay-text="开启|禁用" {{ d.state == 1 ? 'checked' : '' }}>
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/editTeacher.jsp" topMode="edit"
                   topWidth="800px" topHeight="600px" topTitle="编辑教师信息" inputs="id:,userId:,">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="submit" url="/deleteTeacherAction?ownUserId=<s:property value="#login.userId"/>" isConfirm="1"
                   confirmMsg="是否确定删除当前记录？" inputs="userId:">删除</a>
                <a class="layui-btn layui-btn-xs" lay-event="top" topUrl="views/collegeAdmin/addSalaryByTool.jsp" topMode="edit"
                   topWidth="800px" topHeight="350px" topTitle="增加教师工资" inputs="id:,userId:,">增加工资</a>
                <a class="layui-btn layui-btn-resetPwd layui-btn-xs" lay-event="submit" url="/resetPwdAction?ownUserId=<s:property value="#login.userId"/>&idCard=<s:property value="#login.userId"/>" isConfirm="1"
                   confirmMsg="是否将密码重置为用户工号后六位？" inputs="userId:">重置密码</a>
            </script>
        </div>
    </div>
</div>
</body>
</html>
