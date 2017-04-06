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
<title>用户列表</title>
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
	<span class="c-gray en">&gt;</span> 用户列表 
	<a title="刷新" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:window.location.reload();" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pt-5 pr-5 pl-20">
	
	<div class="cl pd-5 bg-1 bk-gray">
		<div id="xpCondition"></div>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-5"> <span class="l"> <a class="btn btn-primary radius" onclick="edit(0)" href="javascript:;">
		<i class="Hui-iconfont">&#xe607;</i> 添加用户</a></span></div>
	<div class="mt-5">
		<table id="tb" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th colName="userId" width="40">ID</th>
					<th colName="userNum" width="80">登录账号</th>
					<th colName="userName" width="120">用户名称</th>
					<th colName="roleName" width="150">用户角色</th>
					<th colName="remark" width="80">备注</th>
					<th funName="colManage" width="100">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div>
					<span id="pager" class="pd-10 l"></span>
					<span class="pd-10 r">共有数据：<strong id="total">0</strong> 条</span>
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

var mapIn = {rows : 10,page : 1	};
function initMapIn(){
	mapIn = {rows : 10,page : 1	};
}
var jump = true;
function xplaypage(pages){
	laypage({
	    cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
	    curr: mapIn.page, //初始化当前页
	    jump: function(e){ //触发分页后的回调
	    	if(!jump){
	    		jump = true;
	    		return ;
	    	}
	    	mapIn.page = e.curr;
	    	xpdatagrid();
	    },
	    groups: 5, //连续显示分页数r
	    pages: pages //总页数
	});
}
function xpdatagrid(){
	var url = "BaseWeb/listUserJson";
	xpAjax(url,
		mapIn,
		function(data){
		$("#tb").jsonTr(data.list);
		$("#total").text(data.totalRow);
		jump = false;
		xplaypage(data.totalPage);
		$("#tb tbody tr").addClass("text-c");
	},"json");
}
xplaypage();

function sel(){
	initMapIn();
	var condition = $("#xpCondition").getXPCondition();
	mapIn["where"] = condition.where;
	mapIn["values"] = condition.values;
	xplaypage();
}

//************渲染列***********

function colManage(row,index){
	var content = 
		'<a title="编辑" href="javascript:;" onclick="edit('+row.userId+')" class="ml-5" style="text-decoration:none">'
	+'<i class="Hui-iconfont">&#xe6df;</i>编辑</a> '
	+'<a title="删除" href="javascript:;" onclick="delUser('+row.userId+')" class="ml-5" style="text-decoration:none">'
	+'<i class="Hui-iconfont">&#xe68f;</i>删除</a> ';
	return content;
}
//************end渲染列**********

/*添加*/
var editWin = null;
function edit(userId){
	userId = userId?userId:0;
	editWin = layer.open({
		type: 2,
		title: "编辑用户",
		content: 'BaseWeb/editUser?userId='+userId
	});
	layer.full(editWin);
}
function closeEditWin(){
	layer.close(editWin);
}

/**
 * 动态条件组合
 */
var xpConditionObj = [{
	"text":"登录账号",
	"inputName":"userNum"
},{
	"text":"用户名称",
	"inputName":"userName"
},{
	"text":"用户角色",
	"inputName":"roleName"
}];
$("#xpCondition").xpCondition(xpConditionObj);

function delUser(userId){
	if(!window.confirm("确定删除该记录？")){
		return ;
	}
	
	xpAjax("BaseWeb/delUser",{
		"userId":userId
	},function(data){
		if(data == "SUCCESS"){
			layer.msg('操作成功!',{icon:1,time:1000});
		}
		else{
			alert(data);
		}
		xplaypage();
	},"text");
}

</script>
</body>
</html>