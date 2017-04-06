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
<title>导入看板数据</title>
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
	<span class="c-gray en">&gt;</span> 导入数据 
	<span class="c-gray en">&gt;</span> 导入看板数据 
	<a title="刷新" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:window.location.reload();" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pt-5 pr-5 pl-20">
	
	<div>
		<form action="ImportKanBan/upload" method="post" enctype="multipart/form-data">
			<input type="file" name="excelFile" accept=".xls,.xlsx">
			<input type="submit" value="上传">
		</form>
	</div>
			<div>
			<h3><a href="doc/Import-KanBan-tpl.xls" target="_new">《看板数据模板下载》</a></h3>
			</div>
			
			<div>
			导入说明：
			<ul>
				<li>请检查每一行数据的完整性；</li>
			</ul>
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

/*添加*/
var editWin = null;
function edit(id){
	id = id?id:0;
	var w = ($(window).width() - 100);
	var h = ($(window).height() - 100);
	editWin = layer.open({
		type: 2,
		title: "编辑",
        area: [w+'px', h +'px'],
		content: 'MacInfo/edit?id='+id
	});
	//layer.full(editWin);
}
function closeEditWin(){
	layer.close(editWin);
}

function del(id){
	if(!window.confirm("确定删除该记录？")){
		return ;
	}
	
	xpAjax("MacInfo/del",{
		"id":id
	},function(data){
		if(data == "0"){
			layer.msg('操作成功!',{icon:1,time:1000});
		}
		else{
			alert(data);
		}
		xpdatagrid();
	},"text");
}

</script>
</body>
</html>