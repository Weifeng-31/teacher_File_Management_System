/**
 * @Description: 字典配置
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.7.0
 * @License：MIT
 */
layui.fsDict = {

          qualification : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			spaceMode : " ",//展示多个数据分隔符，默认,
			data:[
				{"code":"1","name":"博士","css":"layui-badge layui-bg-orange"},
				{"code":"2","name":"硕士","css":"layui-badge layui-bg-orange"},
				{"code":"3","name":"本科","css":"layui-badge layui-bg-orange"},
				{"code":"4","name":"专科","css":"layui-badge layui-bg-orange"},
                {"code":"5","name":"高中","css":"layui-badge layui-bg-orange"},
				{"code":"6","name":"其他","css":"layui-badge layui-bg-orange"}]
				 }
			,
			political : {
				formatType : "local",
				labelField : "name",
				valueField : "code",
				spaceMode : " ",//展示多个数据分隔符，默认,
				data:[
					{"code":1,"name":"中共党员","css":"layui-badge"},
					{"code":2,"name":"共青团员","css":"layui-badge"},
					{"code":3,"name":"群众","css":"layui-badge"},
					{"code":4,"name":"其他党派","css":"layui-badge"}]
			}
			, role_id : {
				formatType : "server",
				loadUrl : "getRoleDict",
				inputs : "",
				labelField : "rolename",
				valueField : "roleId"
			}
			, depart_id : {
				formatType : "server",
				loadUrl : "getDepartDict",
				inputs : "",
				labelField : "departmentName",
				valueField : "departmentId"
			}, profession_id : {
				formatType : "server",
				loadUrl : "getProfessionDict",
				inputs : "",
				labelField : "professionName",
				valueField : "professionId"
	}
};