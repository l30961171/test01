<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>菜单管理</title>
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

<div style="top:0;right:0;position:fixed;width:60%;">
<nav class="breadcrumb">
<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统设置 <span class="c-gray en">&gt;</span> 菜单管理 
<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>

		<form class="form form-horizontal" id="FF">
		<div class="row cl">
			<div class="col-xs-7 col-sm-8 col-offset-2">
				<input value="保存节点" class="btn btn-primary radius" type="submit">
				<input value="添加子节点" class="btn btn-primary radius" type="button" onclick="addMenu()">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>节点名称：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="节点名称" id="menuName" name="row.menuName" datatype="*1-100" nullmsg="节点名称不能为空">
				<input type="hidden" id="menuId" name="row.menuId" />
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">节点图标：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="节点图标如  &amp;#xe63d;" id="menuIcon" name="row.menuIcon">
				<br/>
				<sub><a href="/Public/H-/lib/Hui-iconfont/1.0.7/demo.html" target="_icondemo">参考图标集</a></sub>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">转跳地址：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="转跳地址" id="menuUrl" name="row.menuUrl">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">是否显示：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<select id="isShow" name="row.isShow">
					<option value="y">显示</option>
					<option value="n">不显示</option>
				</select>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">菜单排序：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="菜单排序" id="menuOrd" name="row.menuOrd"  datatype="*1-16" nullmsg="菜单排序不能为空">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="备注" id="menuRemark" name="row.menuRemark">
			</div>
		</div>
	</form>


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
//节点树
var zNodes = $.parseJSON('${rows}');

var setting = {
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
		edit: {
				enable: true,
				showRemoveBtn: true,
				showRenameBtn: false
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				nodes = zTree.getSelectedNodes(),
				editMenu(treeNode,treeNode.menuId);
				return true;
			},
			beforeRemove: function(treeId, treeNode) {
				if(confirm("确认删除 节点 -- " + treeNode.menuName + " 吗？")){
					var result = false;
					xpAjax("BaseWeb/delMenu",{
						"row.menuId":treeNode.menuId
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
					$.post("BaseWeb/updateMenu",{
						"row.menuId":treeNodes[0].menuId,
						"row.menuFid":targetNode.menuId
					});
				}
				else if(treeNodes[0].level == targetNode.level){
					$.post("BaseWeb/updateMenu",{
						"row.menuId":treeNodes[0].menuId,
						"row.menuFid":targetNode.menuFid
					});
				}
				return targetNode ? targetNode.drop !== false : true;
			}
		}
	};

$(function(){
	$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
	$.fn.zTree.init($("#treeDemo"), setting , zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
	
	$("#FF").Validform({
		tiptype:2,
		callback:function(form){
			
			updateMenu();
			return false;
		}
	});
});

function editMenu(treeNode,menuId){
	var url = "BaseWeb/singleMenuJson?menuId="+menuId;
	$.post(url,function(data){
		initForm($("#FF"),data);
	},"json");
}

function initForm(form,data){
	for(var key in data){
		$("#"+key,form).val(data[key]);
	}
}

function updateMenu(){
	if(!$("#FF #menuId").val()){
		return ;
	}
	
	xpAjax("BaseWeb/updateMenu",
		$("#FF").serialize(),
		function(data){
			if(data == "SUCCESS"){
				layer.msg('操作成功!',{icon:1,time:1000});
			}
			else{
				alert(data);
			}
	},"text");
}

function addMenu(){
	$("#FF #menuId").attr("name","row.menuFid");
	xpAjax("BaseWeb/addMenu",
		$("#FF").serialize(),
		function(data){
			if(data == "SUCCESS"){
				layer.msg('操作成功!',{icon:1,time:1000});
			}
			else{
				alert(data);
				return ;
			}
			window.location.reload();
	},"text");
}
</script>
</body>
</html>