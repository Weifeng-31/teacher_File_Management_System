/**
 * @Description: 菜单配置
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.7.0
 * @License：MIT
 */
layui.define(['element',"fsConfig","fsCommon"], function(exports){
	
	var menuConfig = {
			dataType : "server" , //获取数据方式，local本地获取，server 服务端获取
			loadUrl : "/getAdminMenu", //加载数据地址
			rootMenuId : "0", //根目录菜单id
			defaultSelectTopMenuId : "1", //默认选中头部菜单id
			defaultSelectLeftMenuId : "111", //默认选中左边菜单id
			menuIdField : "menuId", //菜单id
			menuNameField : "menuName", //菜单名称
			menuIconField : "menuIcon" , //菜单图标，图标必须用css
			menuHrefField : "menuHref" , //菜单链接
			parentMenuIdField : "parentMenuId" ,//父菜单id
		/*data : [
				{"menuId":"1","menuName":"信息管理","menuIcon":"fa-cog","menuHref":"","parentMenuId":"0"},
                //{"menuId":"2","menuName":"信息查看","menuIcon":"fa-commenting","menuHref":"","parentMenuId":"0"},
				{"menuId":"11","menuName":"导航栏","menuIcon":"fa-table","menuHref":"","parentMenuId":"1"},
				{"menuId":"12","menuName":"信息修改","menuIcon":"fa-envelope","menuHref":"","parentMenuId":"1"},
				{"menuId":"111","menuName":"首页","menuIcon":"&#xe68e;","menuHref":"views/home/tea_index.jsp","parentMenuId":"11"},
				//{"menuId":"datagrid","menuName":"数据表格","menuIcon":"fa-list","menuHref":"views/datagrid/tea_index.jsp","parentMenuId":"11"},
				{"menuId":"datagrid2","menuName":"人员管理","menuIcon":"fa-edit ","menuHref":"views/userManage/userManage.jsp","parentMenuId":"11"},
				{"menuId":"treeDatagrid","menuName":"工资查看","menuIcon":"fa-cogs","menuHref":"views/querySalary/querySalary.jsp","parentMenuId":"11"},
                //{"menuId":"multiDatagrid","menuName":"多数据表格","menuIcon":"fa-list","menuHref":"views/multiDatagrid/tea_index.jsp","parentMenuId":"11"},
                //{"menuId":"tabDatagrid","menuName":"tab数据表格","menuIcon":"fa-list","menuHref":"views/tabDatagrid/tea_index.jsp","parentMenuId":"11"},
                //{"menuId":"complexDatagrid","menuName":"复杂数据表格","menuIcon":"fa-list","menuHref":"views/complexDatagrid/tea_index.jsp","parentMenuId":"11"},
                //{"menuId":"linkageDatagrid","menuName":"联动数据表格","menuIcon":"fa-list","menuHref":"views/linkageDatagrid/tea_index.jsp","parentMenuId":"11"},
                //{"menuId":"linkageDatagrid2","menuName":"联动数据表格(复杂)","menuIcon":"fa-list","menuHref":"views/linkageDatagrid2/tea_index.jsp","parentMenuId":"11"},
                //{"menuId":"staticDatagrid","menuName":"表格数据提交","menuIcon":"fa-list","menuHref":"views/staticDatagrid/tea_index.jsp","parentMenuId":"11"},
				{"menuId":"121","menuName":"资料修改","menuIcon":"fa-address-book","menuHref":"404.html","parentMenuId":"12"},
                {"menuId":"121","menuName":"密码修改","menuIcon":"fa-navicon","menuHref":"404.html","parentMenuId":"12"},
                //{"menuId":"21","menuName":"基本元素","menuIcon":"","menuHref":"","parentMenuId":"2"},
                //{"menuId":"22","menuName":"徽章","menuIcon":"","menuHref":"http://www.layui.com/demo/badge.html","parentMenuId":"2"},
                //{"menuId":"23","menuName":"数据表格","menuIcon":"","menuHref":"http://www.layui.com/demo/table.html","parentMenuId":"2"},
                // //{"menuId":"211","menuName":"按钮","menuIcon":"","menuHref":"http://www.layui.com/demo/button.html","parentMenuId":"21"},
                //{"menuId":"212","menuName":"表单","menuIcon":"","menuHref":"http://www.layui.com/demo/form.html","parentMenuId":"21"},
                //{"menuId":"213","menuName":"选项卡","menuIcon":"","menuHref":"http://www.layui.com/demo/tab.html","parentMenuId":"21"}
		 ] //本地数据
		 */
	};
	
	var element = layui.element,
	fsCommon = layui.fsCommon,
	fsConfig = layui.fsConfig,
	statusName = $.result(fsConfig,"global.result.statusName","errorNo"),
  msgName = $.result(fsConfig,"global.result.msgName","errorInfo"),
  dataName = $.result(fsConfig,"global.result.dataName","results.data"),
	FsMenu = function (){
		
	};
	
	
	FsMenu.prototype.render = function(){
		
		this.loadData();
		
		this.showMenu();
	};
	
	/**
	 * 加载数据
	 */
	FsMenu.prototype.loadData = function(){
		
		if(menuConfig.dataType == "server"){//服务端拉取数据
			
			var url = menuConfig.loadUrl;
			if($.isEmpty(url)){
				fsCommon.errorMsg("未配置请求地址！");
				return;
			}
			
			fsCommon.invoke(url,{},function(data){
  			if(data[statusName] == "0")
  			{
  				menuConfig.data = $.result(data,dataName);
  			}
  			else
  			{
  				//提示错误消息
  				fsCommon.errorMsg(data[msgName]);
  			}
  		},false);
			
		}
		
	}
	
	
	/**
	 * 获取图标
	 */
	FsMenu.prototype.getIcon = function(menuIcon){
		
		if(!$.isEmpty(menuIcon)){
			
			if(menuIcon.indexOf("<i") == 0){
				return menuIcon;
			}else if (menuIcon.indexOf("&#") == 0){
				return '<i class="layui-icon">'+menuIcon+'</i>';
			}else if (menuIcon.indexOf("fa-") == 0){
				return '<i class="fa '+menuIcon+'"></i>';
			}else {
				return '<i class="'+menuIcon+'"></i>';
			}
		}
		return "";
	};
	
	/**
	 * 清空菜单
	 */
	FsMenu.prototype.cleanMenu = function(){
		$("#fsTopMenu").html("");
		$("#fsLeftMenu").html("");
	}
	/**
	 * 显示菜单
	 */
	FsMenu.prototype.showMenu = function(){
		var thisMenu = this;
		var data = menuConfig.data;
		if(!$.isEmpty(data)){
			var _index = 0;
			//显示顶部一级菜单
			var fsTopMenuElem = $("#fsTopMenu");
			var fsLeftMenu = $("#fsLeftMenu");
			$.each(data,function(i,v){
				if(menuConfig.rootMenuId === v[menuConfig.parentMenuIdField]){
					
					var topStr = '<li class="layui-nav-item';
					if($.isEmpty(menuConfig.defaultSelectTopMenuId) && _index === 0){//为空默认选中第一个
						topStr += ' layui-this';
					}else if(!$.isEmpty(menuConfig.defaultSelectTopMenuId) && menuConfig.defaultSelectTopMenuId == v[menuConfig.menuIdField]){//默认选中处理
						topStr += ' layui-this';
					}
					_index ++ ;
					topStr += '" dataPid="'+v[menuConfig.menuIdField]+'"><a href="javascript:;">'+thisMenu.getIcon(v[menuConfig.menuIconField])+' <cite>'+v[menuConfig.menuNameField]+'</cite></a></li>';
					fsTopMenuElem.append(topStr);
					
					//显示二级菜单，循环判断是否有子栏目
					$.each(data,function(i2,v2){
						if(v[menuConfig.menuIdField] === v2[menuConfig.parentMenuIdField]){
							
							var menuRow = '<li class="layui-nav-item';
							if(!$.isEmpty(menuConfig.defaultSelectLeftMenuId) && menuConfig.defaultSelectLeftMenuId == v2[menuConfig.menuIdField]){//默认选中处理
								menuRow += ' layui-this';
							}
							//显示三级菜单，循环判断是否有子栏目
							var menuRow3 = "";
							$.each(data,function(i3,v3){
								if(v2[menuConfig.menuIdField] === v3[menuConfig.parentMenuIdField]){
									if($.isEmpty(menuRow3)){
										menuRow3 = '<dl class="layui-nav-child">';
									}
									menuRow3 += '<dd';
									if(!$.isEmpty(menuConfig.defaultSelectLeftMenuId) && menuConfig.defaultSelectLeftMenuId == v3[menuConfig.menuIdField]){//默认选中处理
										menuRow3 += ' class="layui-this"';
										menuRow += ' layui-nav-itemed';//默认展开二级菜单
									}
									
									menuRow3 += ' lay-id="'+v3[menuConfig.menuIdField]+'"><a href="javascript:;" menuId="'+v3[menuConfig.menuIdField]+'" dataUrl="'+v3[menuConfig.menuHrefField]+'">'+thisMenu.getIcon(v3[menuConfig.menuIconField])+' <cite>'+v3[menuConfig.menuNameField]+'</cite></a></dd>';
									
								}
								
							});
							
							menuRow += '" lay-id="'+v2[menuConfig.menuIdField]+'" dataPid="'+v2[menuConfig.parentMenuIdField]+'" style="display: none;"><a href="javascript:;" menuId="'+v2[menuConfig.menuIdField]+'" dataUrl="'+v2[menuConfig.menuHrefField]+'">'+thisMenu.getIcon(v2[menuConfig.menuIconField])+' <cite>'+v2[menuConfig.menuNameField]+'</cite></a>';
							
							
							if(!$.isEmpty(menuRow3)){
								menuRow3 += '</dl>';
								
								menuRow += menuRow3;
							}
							
							menuRow += '</li>';
							
							fsLeftMenu.append(menuRow);
						}
						
					});
					
				}
			});
		}
		element.render("nav");
	};
	
	var fsMenu = new FsMenu();
	exports("fsMenu",fsMenu);
});

