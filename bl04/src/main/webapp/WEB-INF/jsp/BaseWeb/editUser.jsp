<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Index/webInfo.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>编辑用户</title>
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
<body>

<div id="tab-system" class="HuiTab">
	<div class="tabBar cl">
		<span>用户信息</span>
		<c:if test="${not empty row.userId}">
		<span>配置角色</span>
		</c:if>
	</div>
	<div class="tabCon">
	<article class="page-container">
	<form method="post" action="BaseWeb/addUser" class="form form-horizontal" id="form-admin">
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input value="提交" class="btn btn-primary radius" type="submit">
				<c:if test="${not empty row.userId}">
				<input value="密码重置" class="btn btn-primary radius" type="button" onclick="resetPsw();">
				</c:if>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录账号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input class="input-text" placeholder="登录账号" name="user.userNum" value="${row.userNum}" datatype="*2-16" nullmsg="用户账号不能为空">
				<input id="id" name="user.userId" value="${row.userId}" type="hidden">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input class="input-text" placeholder="用户名称" name="user.userName" value="${row.userName}" datatype="*2-20" nullmsg="用户名不能为空">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">联系方式：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input class="input-text" placeholder="联系方式" name="user.userMobile" value="${row.userMobile}">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">联系邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input class="input-text" placeholder="联系邮箱" name="user.userEmail" value="${row.userEmail}">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="user.userRemark" class="textarea"  
				placeholder="100个字符以内" dragonfly="true" 
				onKeyUp="textarealength(this,100)">${row.userRemark}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
		</div>
		
	</form>
	</article>
	</div>

	<div class="tabCon">
		<form class="form form-horizontal" id="FF2">
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<button class="btn btn-primary radius" type="button" onclick="saveUserRole()">
					<i class="Hui-iconfont">&#xe632;</i> 保存用户角色</button>
			</div>
		</div>
		<ul id="treeDemo" class="ztree" style="margin-left:30px;"></ul>
		</form>
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
var rolesData = $.parseJSON('${roles}');

//用户角色
var userRoles = $.parseJSON('${userRoles}');

function save(){
	var editType = $("#id").val()?"updateUser":"addUser";
	if(!$("#userEmail").val()){
		$("#userEmail").attr("name","");
	}
	
	xpAjax("BaseWeb/"+editType,
		$("#form-admin").serialize(),
		function(data){
		parent.sel();
		if(data == "SUCCESS"){
			window.top.layer.msg('操作成功!',{icon:1,time:1000});
			parent.closeEditWin();
			return;
		}
		else{
			alert(data);
		}
	},"text");
}

$(function(){
	$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
	
	//点击提交时，触发验证。
	$("#form-admin").Validform({
		tiptype:2,
		callback:function(form){
			save();
			return false;
		}
	});
	
	$.fn.zTree.init($("#treeDemo"), {
		check: {
			enable: true,
			chkboxType: { "Y" : "", "N" : "" }
		},
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
		}
	}, rolesData);
	setUserRoleTree(userRoles);
});

function setUserRoleTree(data){
	var treeDemo = $.fn.zTree.getZTreeObj("treeDemo");
	for(var i in data){
		var node = treeDemo.getNodeByParam("roleId", data[i].roleId, null);
		treeDemo.checkNode(node, true, false, false);
	}
}

function saveUserRole(){
	var treeDemo = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeDemo.getCheckedNodes(true);
	var tmpForm = $("<form>");
	tmpForm.attr("id","tmpForm");
	tmpForm.hide();
	tmpForm.appendTo("body");
	$("<input>").attr("name","size").val(nodes.length).appendTo(tmpForm);
	$("<input>").attr("name","userId").val("${row.userId}").appendTo(tmpForm);
	for(var i in nodes){
		$("<input>").attr("name","userRole_"+i+".userId").val("${row.userId}").appendTo(tmpForm);
		$("<input>").attr("name","userRole_"+i+".roleId").val(nodes[i].roleId).appendTo(tmpForm);
	}
	xpAjax("BaseWeb/saveUserRole",
		tmpForm.serialize(),
		function(data){
		tmpForm.remove();
		parent.xpdatagrid();
		if(data == "SUCCESS"){
			layer.msg('操作成功!',{icon:1,time:1000});
		}
		else{
			alert(data);
		}
		return ;
	},"text");
}


function resetPsw(){
	if(!window.confirm("确定为该用户重置密码？")){
		return ;
	}
	
	xpAjax("BaseWeb/resetPsw",
		{
			userId:"${row.userId}"
		},
		function(data){
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