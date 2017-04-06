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
<title>公告列表</title>
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
	<span class="c-gray en">&gt;</span> 消息发布 
	<span class="c-gray en">&gt;</span> 分类管理 
	<a title="刷新" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:window.location.reload();" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 
		<a class="btn btn-primary radius" onclick="editNotice(0)" href="javascript:;">
		<i class="Hui-iconfont">&#xe604;</i> 添加分类</a></span> </div>
		
		<div class="mt-20">
		<table id="tb" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25%">ID</th>
					<th width="25%">分类名称</th>
					<th width="25%">排序</th>
					<th width="25%">操作</th>
				</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${rows}" var="row">
			<tr>
				<td>${row.noticeTypeId}</td>
				<td>${row.noticeTypeName}</td>
				<td>${row.noticeTypeOrd}</td>
				<td>
					<a href="javascript:;" onclick="editNotice(${row.noticeTypeId})" 
					class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i> 编辑</a>
					<a href="javascript:;" onclick="delNotice(${row.noticeTypeId})" 
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
function editNotice(noticeTypeId){
		var index = layer.open({
            type: 2,
            title: '编辑参数',
            content: 'Notice/editNoticeType?noticeTypeId='+noticeTypeId
        });
        layer.full(index);
}

function delNotice(noticeTypeId){
	if(!window.confirm("确定删除该记录？")){
		return ;
	}
	$.post("Notice/delNoticeType",{
		"row.noticeTypeId":noticeTypeId
	},function(data){
		if(data == "SUCCESS"){
			layer.alert('操作成功', {icon: 1});
		}
		else{
			alert(data);
		}
		window.location.reload();
	},"text");
}
</script>
</body>
</html>