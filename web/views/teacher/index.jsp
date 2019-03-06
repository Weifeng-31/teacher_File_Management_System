<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta content="text/html;charset=UTF-8"/>
	<title>教师后台</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv ="Pragma" content = "no-cache"/>
	<meta http-equiv="Cache-Control" content="no cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no"/>
	<script src="https://cdn.bootcss.com/pace/1.0.2/pace.min.js"></script>
	<link href="https://cdn.bootcss.com/pace/1.0.2/themes/pink/pace-theme-flash.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="./plugins/layui/css/layui.css" media="all"/>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<script type="text/javascript" src="../../plugins/jquery/jquery.min.js"></script>
	<link href="../../plugins/contextMenu/jquery.contextMenu.min.css" rel="stylesheet">
	<script src="../../plugins/contextMenu/jquery.contextMenu.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../../css/fs.css?v=1.7.0" media="all"/>
	<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_teacher/fsDict.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_teacher/fs.js"></script>
	<script type="text/javascript" src="../../plugins/frame/js_teacher/main.js"></script>
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	<link rel="stylesheet" href="../../views/home/css/main.css" media="all" />
	<%@ include file="/loginCheckTeacher.jsp"%>
	<style>
		.layui-nav-item a cite{padding: 0 5px}
	</style>
</head>
<body>
<s:set var="login" value="#session['login']"/>
<s:set var="userRole" value="#session['userRole']"/>
<s:set var="userDepartment" value="#session['userDepartment']"/>
<div class="layui-layout layui-layout-admin">
	<!-- 顶部 -->
	<div class="layui-header">
		<div class="layui-logo">教师档案管理系统</div>
		<a href="javascript:;" class="layui-hide-xs">
			<div class="fsSwitchMenu"><i class="fa fa-outdent"></i></div>
		</a>
		<!-- 顶部菜单 -->
		<ul class="layui-nav layui-layout-left fsTopMenu" id="fsTopMenu" lay-filter="fsTopMenu">
		</ul>
		<ul class="layui-nav layui-layout-right rightMenu" id="rightMenu" lay-filter="rightMenu">
			<li class="layui-nav-item" >
				<a href="javascript:;" class="showNotice">
						<i class="fa fa-volume-up"></i>&nbsp;系统公告<span class="layui-badge" id="notice_state"><s:property value="#login.notice_state"/></span>
				</a>
			</li>
			<li class="layui-nav-item layui-hide-xs" lay-id="13" datapid="1">
				<a href="javascript:;">

					<img id="img" src="<s:property value="#login.headUrl"/>" class="layui-nav-img">
					<s:property value="#login.userId" /><span class="layui-nav-more"></span>
				</a>
				<dl class="layui-nav-child" >
					<dd lay-id="131">
						<a href="javascript:;" dataurl="views/home/user_info.jsp" menuid="131"> <i class="fa fa-info-circle"></i><cite>信息修改</cite></a>
					</dd>
					<dd lay-id="132">
						<a href="javascript:;" dataurl="views/home/user_password.jsp" menuid="132"><i class="fa fa-lock"></i><cite>密码修改</cite></a>
					</dd>
					<dd lay-id="133">
						<a href="/loginOutAction"menuid="133"><i class="fa fa-sign-out"></i><cite>安全退出</cite></a>
					</dd>
				</dl>
			</li>
			<!-- 天气信息 -->
			<div class="weather" style="color:#fff; float:left; margin:15px;">
				<div id="tp-weather-widget"></div>
				<script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
				<script>tpwidget("init", {
					"flavor": "slim",
					"location": "WX4FBXXFKE4F",
					"geolocation": "enabled",
					"language": "zh-chs",
					"unit": "c",
					"theme": "chameleon",
					"container": "tp-weather-widget",
					"bubble": "disabled",
					"alarmType": "badge",
					"color": "#FFFFFF",
					"uid": "U9EC08A15F",
					"hash": "039da28f5581f4bcb5c799fb4cdfb673"
				});
				tpwidget("show");</script>
			</div>
		</ul>
	</div>
<!-- 左边菜单 -->
  <div class="layui-side layui-bg-black">
	  <!-- 左侧头像-->
	  <div class="user-photo">
		  <a class="img" title="我的头像" ><img src="<s:property value="#login.headUrl"/>" class="userAvatar"></a>
		  <p>你好！<span class="userName"><s:property value="#login.name"/></span>, 欢迎登录</p>
	  </div>
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree fsLeftMenu"  lay-filter="fsLeftMenu" id="fsLeftMenu">
      </ul>
    </div>
  </div>
  <!-- 右边内容区域 -->
  <div class="layui-body layui-form"  style="overflow:hidden">
  	<div class="layui-tab layui-tab-card fsTab" lay-filter="fsTab" lay-allowClose="true">
  		<!-- 菜单导航 -->
		<ul class="layui-tab-title" id="fsTabMenu">
			<li class="layui-this" lay-id="11"><i class="layui-icon">&#xe68e;</i><cite>首页</cite><p class="layui-tab-close" style="display: none;"></p></li>
		</ul>
		<!-- 内容 -->
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show" lay-id="1">
				<iframe src="views/home/teacher_iframe.jsp"></iframe>
			</div>
		</div>
	</div>
  </div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
		©2018<a href="javascript:;">教师档案管理系统</a>
	</div>>
</div>

<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide">
  <i class="layui-icon">&#xe602;</i>
</div>
<div class="site-mobile-shade"></div>
</body>
<s:set var="notice" value="#session['notice']"/>

<script>
    layui.use(['form','jquery',"layer"],function() {
        var form = layui.form,
            $ = layui.jquery,
            layer = parent.layer === undefined ? layui.layer : top.layer;
        //公告层
        function showNotice(){

            layer.open({
                type: 1,
                title: "系统公告",
                area: '300px',
                shade: 0.8,
                id: 'LAY_layuipro',
                btn: ['知道了'],
                moveType: 1,
                content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red"><s:property value="#notice.content" /></p></div>',
                success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.css('text-align', 'center');
                    btn.on("click",function(){
                        tipsShow();
                    });
                },
                cancel: function(index, layero){
                    tipsShow();
                }
            });


            $.ajax({
                type:"POST",
                url:"/updateNoticeAction",
                data:{userId:<s:property value="#login.userId"/>},
                success:function(data){
                    var obj = eval(data);
                    $("#notice_state").text(obj.results);
                }
            });

        }

		function tipsShow(){
			window.sessionStorage.setItem("showNotice","true");
			if($(window).width() > 432){  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
				layer.tips('系统公告躲在了这里', '#userInfo', {
					tips: 3,
					time : 1000
				});
			}
		}
        $(".showNotice").on("click",function(){
            showNotice();
        })
    })
</script>
</html>
