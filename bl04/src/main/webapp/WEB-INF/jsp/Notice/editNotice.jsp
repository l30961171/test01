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
<title>编辑消息</title>
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
<div class="pd-20">
		<form class="form form-horizontal" id="FF">
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-offset-3">
				<input value="提交" class="btn btn-primary radius" type="button" onclick="doSave()">
			</div>
		</div>
	
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				${row.noticeId}
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input class="input-text" placeholder="标题" id="noticeTitle"
				value="${row.noticeTitle}" datatype="*2-16" nullmsg="标题不能为空">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类别：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select id="noticeTypeId">
					<c:forEach items="${types}" var="type">
					<option value="${type.noticeTypeId}" <c:if test="${row.noticeTypeId == type.noticeTypeId}">selected</c:if>>${type.noticeTypeName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>内容：</label>
		</div>
		
		<div class="row cl">
			<div class="formControls col-xs-12 col-sm-12">
				<script id="noticeContent" type="text/plain" style="width:100%;" >${row.noticeContent}</script>
			</div>
		</div>
		</form>
</div>
</body>
<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/Public/H-/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script> 
<script type="text/javascript" src="/Public/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/Public/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">
	//实例化编辑器
	var noticeContent = UE.getEditor('noticeContent');
	var checkSubmit = false; //检测是否已提交
	
	function checkInput()
	{
		if(!($("#noticeTitle").val().length > 0 && noticeContent.getContent().length > 0))
		{
			alert("输入点内容吧~~");
			return false;
		}
		if(!$("#noticeTypeId").val())
		{
			alert("请选择分类");
			return false;
		}
		return true;
	}
	
	function doSave(){
		var editType = "${row.noticeId}"==""?"addNotice":"updateNotice";
		if(checkInput()){
			$.post("Notice/"+editType,{
				"row.noticeId":"${row.noticeId}",
				"row.noticeTitle":$("#noticeTitle").val(),
				"row.noticeContent":noticeContent.getContent(),
				"row.noticeTypeId":$("#noticeTypeId").val()
			},function(data){
				if(data == "SUCCESS"){
					parent.xpdatagrid();
					layer.msg('操作成功', {icon: 1});
					return;
				}
				else{
					alert(data);
				}
			},'text');
		}
	}
	
</script>
</html>