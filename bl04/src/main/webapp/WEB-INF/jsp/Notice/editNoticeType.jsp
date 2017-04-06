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
<title>编辑消息分类</title>
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
		<span>基本信息</span>
	</div>
	<div class="tabCon">
	<form class="form form-horizontal" id="FF">
		<div class="row cl">
			<div class="col-xs-7 col-sm-8 col-offset-2">
				<button class="btn btn-primary radius" type="submit">
					<i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">编号：</label>
			<div class="formControls col-xs-7 col-sm-8">
				${row.noticeTypeId}
				<input type="hidden" id="noticeTypeId" name="row.noticeTypeId" value="${row.noticeTypeId}" />
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>分类名称：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="分类名称" value="${row.noticeTypeName}"
				id="noticeTypeName" name="row.noticeTypeName" datatype="*1-255" nullmsg="分类名称不能为空">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" placeholder="排序100" value="${row.noticeTypeOrd}"
				id="noticeTypeOrd" name="row.noticeTypeOrd" datatype="n" nullmsg="排序为数字">
			</div>
		</div>
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
function updateNotice(){
	var method = "updateNoticeType";
	if(!$("#noticeTypeId").val()){
		method = "addNoticeType";
	}
	$.post("Notice/" + method,
		$("#FF").serialize(),
	function(data){
		if(data == "SUCCESS"){
			top.layer.msg("操作成功");
			parent.location.reload();
			return;
		}
		else{
			alert(data);
			return ;
		}
	},"text");
}

$(function(){
	$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
	$("#FF").Validform({
		tiptype:2,
		callback:function(form){
			updateNotice();
			return false;
		}
	});
});
</script>
</body>
</html>