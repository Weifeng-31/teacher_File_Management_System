<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta charset="UTF-8">
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
	<link rel="stylesheet" type="text/css" href="css/main.css"/>
	<script type="text/javascript" src="../../plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../../plugins/echarts/echarts.min.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js/fs.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js/frame.js"></script>
	<style>
		body{contenteditable:flase!important;}
		.layui-layedit-tool{display:none}
	</style>
</head>
<body class="childrenBody">
<s:set var="login" value="#session['login']"/>
<s:set var="notice" value="#session['notice']"/>
<s:set var="userRole" value="#session['userRole']"/>
<blockquote class="layui-elem-quote layui-bg-green">
	<div id="nowTime"></div>
</blockquote>
<div class="row">
	<div class="sysNotice col">
		<blockquote class="layui-elem-quote title">版本更新记录</blockquote>
		<div style="height:600px;overflow: auto;">
			<ul class="layui-timeline">
				<jsp:include page="updateRecord.jsp"></jsp:include>
			</ul>
		</div>
	</div>
	<s:set var="userDepartment" value="#session['userDepartment']"/>
	<div class="sysNotice col">
		<blockquote class="layui-elem-quote title">系统基本参数</blockquote>
		<table class="layui-table">
			<colgroup>
				<col width="150">
				<col>
			</colgroup>
			<tbody>
			<tr>
				<td>本院教师总数</td>
				<td class="getTeacherAccount"><span style="color: lightseagreen"><s:property  value="#session['getCollegeTeacherAccount']" /></span></td>
			</tr>
			<tr>
				<td>当前用户组</td>
				<td class="userRights"><span style="color: #1E92FB"><s:property value="#userDepartment.departmentName" /><s:property value="#userRole.rolename" /></span></td>
			</tr>
			<tr>
				<td>上次登录时间</td>
				<td class="userRights"><span style="color: #FD482C"><s:property value="#login.lastLoginTime" /></span></td>
			</tr>
			<tr>
				<td>当前版本</td>
				<td class="version">V1.0.0</td>
			</tr>
			<tr>
				<td>开发作者</td>
				<td class="author">Qiyu</td>
			</tr>
			<tr>
				<td>最大上传限制</td>
				<td class="maxUpload">2 MB</td>
			</tr>
			</tbody>
		</table>
	</div>
	</div>
</div>
<script>
    //获取系统时间
    var newDate = '';
    getLangDate();
    //值小于10时，在前面补0
    function dateFilter(date){
        if(date < 10){return "0"+date;}
        return date;
    }
    function getLangDate(){
        var dateObj = new Date(); //表示当前系统时间的Date对象
        var year = dateObj.getFullYear(); //当前系统时间的完整年份值
        var month = dateObj.getMonth()+1; //当前系统时间的月份值
        var date = dateObj.getDate(); //当前系统时间的月份中的日
        var day = dateObj.getDay(); //当前系统时间中的星期值
        var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
        var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
        var hour = dateObj.getHours(); //当前系统时间的小时值
        var minute = dateObj.getMinutes(); //当前系统时间的分钟值
        var second = dateObj.getSeconds(); //当前系统时间的秒钟值
        var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
        newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
        document.getElementById("nowTime").innerHTML = "亲爱的<s:property value="#userRole.rolename" /><s:property value="#login.name"/>，"+timeValue+"好！ 欢迎使用教师档案管理系统。当前时间为： "+newDate+"　"+week;
        setTimeout("getLangDate()",1000);
    }
</script>
</body>
</html>
