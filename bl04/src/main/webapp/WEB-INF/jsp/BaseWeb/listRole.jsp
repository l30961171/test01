<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>角色管理</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/Public/H-/lib/html5.js"></script>
<script type="text/javascript" src="/Public/H-/lib/respond.min.js"></script>
<script type="text/javascript" src="/Public/H-/lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/Public/H-/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/Public/H-/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/css/style.css" />

<!--[if IE 6]>
<script type="text/javascript" src="/Public/H-/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
</head>
<body class="pos-r">
<div class="pos-a" style="width:40%;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5;">
	<ul id="treeDemo" class="ztree" style="margin-left:10px;">
	</ul>
</div>

<div style="margin-left:40%;">
<nav class="breadcrumb">
<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统设置 <span class="c-gray en">&gt;</span> 角色管理 
<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>

	<div id="tab-system" class="HuiTab">
	<div class="tabBar cl">
		<span>角色信息</span>
		<span>菜单权限设置</span>
		<%--<span>权限对象</span>
		<span>库存地权限设置</span>--%>
	</div>
	<div class="tabCon">
	<article class="page-container">
		<form class="form form-horizontal" id="FF">
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-offset-2">
				<button class="btn btn-primary radius" type="submit">
					<i class="Hui-iconfont">&#xe632;</i> 保存角色</button>
				<button class="btn btn-primary radius" type="button" onclick="addRole()">
					<i class="Hui-iconfont">&#xe604;</i> 添加角色</button>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input readonly id="roleId" name="role.roleId" style="border:0;" />
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="角色名称" id="roleName" name="role.roleName" datatype="*1-16" nullmsg="角色名称不能为空">
				
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="备注" id="roleRemark" name="role.roleRemark">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">识别码：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="角色识别码，代码中识别，非程序员慎填！" id="roleCode" name="role.roleCode">
			</div>
		</div>
		</form>
	</article>
	</div>
	
	<div class="tabCon">
		<form class="form form-horizontal" id="FF2">
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-offset-2">
				<button class="btn btn-primary radius" type="button" onclick="saveRoleMenu()">
					<i class="Hui-iconfont">&#xe632;</i> 保存角色菜单</button>
			</div>
		</div>
		<ul id="menuTree" class="ztree" style="margin-left:10px;"></ul>
		</form>
	</div>
	
	</div>


</div>


<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/Public/H-/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script> 
<script type="text/javascript">
//角色树
var zNodes = $.parseJSON('${roles}');

//菜单树
var menuTreeDatas = $.parseJSON('${menus}');

var curRoleId = null;

var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "roleId",
				pIdKey: "roleFid",
				rootPId: "0"
			},
			key:{
				name:"roleName"
			}
		},
		edit: {
				enable: true,
				showRemoveBtn: true,
				showRenameBtn: false
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				curRoleId = treeNode.roleId;
				$.getJSON("BaseWeb/singleRoleJson?roleId="+curRoleId,
					function(data){
					editRole(data.role);
					setMenuTree(data.roleMenus);
					//editAuthObj(data.authObjs);
					//editAuthLGORT(data.authLGORTs);
				});
				return true;
			},
			beforeRemove: function(treeId, treeNode) {
				if(confirm("确认删除 节点 -- " + treeNode.roleName + " 吗？")){
					var result = false;
					xpAjax("BaseWeb/delRole",{
						"role.roleId":treeNode.roleId
					},function(data){
						if(data == "SUCCESS"){
							result = true;
							layer.msg('操作成功!',{icon:1,time:1000});
						}
						else{
							alert(data);
						}
					},"text",{
						async:false
					});
					return result;
				}
				return false;
			},
			onDrop: function(event, treeId, treeNodes, targetNode, moveType, isCopy) {
				if(treeNodes[0].level > targetNode.level){
					$.post("BaseWeb/updateRole",{
						"role.roleId":treeNodes[0].roleId,
						"role.roleFid":targetNode.roleId
					});
				}
				else if(treeNodes[0].level == targetNode.level){
					$.post("BaseWeb/updateRole",{
						"role.roleId":treeNodes[0].roleId,
						"role.roleFid":targetNode.roleFid
					});
				}
				return targetNode ? targetNode.drop !== false : true;
			}
		}
	};
	
var setting_menuTree = {
		data: {
			simpleData: {
				enable: true,
				idKey: "menuId",
				pIdKey: "menuFid",
				rootPId: "0"
			},
			key:{
				name:"menuName"
			}
		},
		check:{
			enable:true,
			chkStyle:"checkbox",
			chkboxType: { "Y" : "", "N" : "" }
		}
	};

$(function(){
	$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
	
	//角色树
	$.fn.zTree.init($("#treeDemo"), setting , zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
	
	//菜单树
	$.fn.zTree.init($("#menuTree"), setting_menuTree , menuTreeDatas);
	
	
	$("#FF").Validform({
		tiptype:2,
		callback:function(form){
			updateRole();
			return false;
		}
	});
});

function updateRole(){
	if(!curRoleId){
		return ;
	}
	$.post("BaseWeb/updateRole",
		$("#FF").serialize(),
		function(data){
		if(data == "SUCCESS"){
		}
		else{
			alert(data);
			return ;
		}
		window.location.reload();
	},"text");
}

function addRole(){
	if(!curRoleId){
		return ;
	}
	$("#FF #roleId").attr("name","role.roleFid");
	$.post("BaseWeb/addRole",
		$("#FF").serialize(),
		function(data){
		if(data == "SUCCESS"){
			window.location.reload();
		}
		else{
			alert(data);
		}
	},"text");
}

function editRole(data){
	for(var key in data){
		$("#"+key,$("#FF")).val(data[key]);
	}
}

function setMenuTree(data){
	var menuTree = $.fn.zTree.getZTreeObj("menuTree");
	menuTree.checkAllNodes(false);
	
	for(var i in data){
		var node = menuTree.getNodeByParam("menuId", data[i].menuId, null);
		menuTree.checkNode(node, true, false, false);
	}
}

function saveRoleMenu(){
	if(!curRoleId){
		return ;
	}
	var menuTree = $.fn.zTree.getZTreeObj("menuTree");
	var nodes = menuTree.getCheckedNodes(true);
	var tmpForm = $("<form>");
	tmpForm.attr("id","tmpForm");
	tmpForm.hide();
	tmpForm.appendTo("body");
	$("<input>").attr("name","size").val(nodes.length).appendTo(tmpForm);
	$("<input>").attr("name","roleId").val(curRoleId).appendTo(tmpForm);
	for(var i in nodes){
		$("<input>").attr("name","menu_"+i+".menuId").val(nodes[i].menuId).appendTo(tmpForm);
		$("<input>").attr("name","menu_"+i+".roleId").val(curRoleId).appendTo(tmpForm);
	}
	$.post("BaseWeb/saveRoleMenu",
		tmpForm.serialize(),
		function(data){
		tmpForm.remove();
		if(data == "SUCCESS"){
			layer.msg('操作成功!',{icon:1,time:1000});
		}
		else{
			alert(data);
		}
	},"text");
}

</script>
</body>
</html>