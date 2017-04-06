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
<title>系统参数列表</title>
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
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 系统设置 
	<span class="c-gray en">&gt;</span> 系统参数 
	<a title="刷新" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:window.location.reload();" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 
		<a class="btn btn-primary radius" onclick="editSysParm(0)" href="javascript:;">
		<i class="Hui-iconfont">&#xe604;</i> 添加参数</a></span> </div>
		
		<div class="mt-20">
		<table id="tb" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th style="width:40px;">ID</th>
					<th>参数名</th>
					<th>参数值</th>
					<th style="width:80px;">分组</th>
					<th>备注</th>
					<th style="width:120px;">操作</th>
				</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${rows}" var="row">
			<tr class="row_${row.parmId}">
				<td class="parmId">${row.parmId}</td>
				<td class="parmKey">${row.parmKey}</td>
				<td class="parmValue">${row.parmValue}</td>
				<td class="parmGroup">${row.parmGroup}</td>
				<td class="parmRemark">${row.parmRemark}</td>
				<td align="center">
					<a href="javascript:;" onclick="editSysParm(${row.parmId})" 
					class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i> 编辑</a>
					<a href="javascript:;" onclick="delSysParm(${row.parmId})" 
					class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe609;</i> 删除</a>
				</td>
			</tr>
			</c:forEach>
			
			</tbody>
		</table>
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
function editSysParm(parmId){
		var index = layer.open({
            type: 2,
            title: '编辑参数',
            content: 'BaseWeb/editSysParm?parmId='+parmId
        });
        layer.full(index);
}

function delSysParm(parmId){
	if(!window.confirm("确定删除该记录？")){
		return ;
	}
	xpAjax("BaseWeb/delSysParm",{
		"row.parmId":parmId
	},function(data){
		if(data == "SUCCESS"){
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