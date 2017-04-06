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
<title>信息列表</title>
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
	<span class="c-gray en">&gt;</span> 消息列表 
	<a title="刷新" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:window.location.reload();" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	
	<div class="row cl" style="background:#f5f5f5;padding:10px;">
		<div id="xpCondition"></div>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a class="btn btn-primary radius" onclick="edit(0)" href="javascript:;">
		<i class="Hui-iconfont">&#xe607;</i> 添加公告</a></span> 
		<span class="r">共有数据：<strong id="total">0</strong> 条</span> </div>
	<div class="mt-20">
		<table id="tb" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th funName="colNoticeTitle" width="55%">标题</th>
					<th colName="noticeTypeName" width="15%">分类</th>
					<th colName="lastUpdate" width="15%">修改日期</th>
					<th funName="colManage" width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div>
			<span id="pager" style="margin:10px;float:left;"></span>
			<span id="pageInfo" style="margin:10px;float:left;"></span>
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
	    	$('#pageInfo').html('目前正在第'+ e.curr +'页，一共有：'+ e.pages +'页');
	    	if(!jump){
	    		jump = true;
	    		return ;
	    	}
	    	mapIn.page = e.curr;
	    	xpdatagrid();
	    },
	    groups: 0, //连续显示分页数r
	    pages: pages //总页数
	});
}
function xpdatagrid(){
	var url = "Notice/listNoticeJson";
	$.post(url,
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
function colNoticeTitle(row,index){
	var content = 
		'<a href="javascript:;" onclick="viewNotice('+row.noticeId+')" class="ml-5" style="text-decoration:none">'
	+row.noticeTitle+'</a> ';
	return content;
}

function colManage(row,index){
	var content = 
		'<a href="javascript:;" onclick="edit('+row.noticeId+')" class="ml-5" style="text-decoration:none">'
	+'<i class="Hui-iconfont">&#xe6df;</i>编辑</a> '
	+'<a href="javascript:;" onclick="delUser('+row.noticeId+')" class="ml-5" style="text-decoration:none">'
	+'<i class="Hui-iconfont">&#xe68f;</i>删除</a> ';
	return content;
}
//************end渲染列**********

/*添加*/
var editWin = null;
function edit(noticeId){
	editWin = layer.open({
		type: 2,
		title: "编辑消息",
		content: 'Notice/editNotice?noticeId='+noticeId
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
	"text":"标题",
	"inputName":"noticeTitle"
},{
	"text":"分类",
	"inputName":"noticeTypeName"
}];
$("#xpCondition").xpCondition(xpConditionObj);

function delUser(noticeId){
	if(!window.confirm("确定删除该记录？")){
		return ;
	}
	$.post("Notice/delNotice",{
		"row.noticeId":noticeId
	},function(data){
		xpdatagrid();
		if(data == "SUCCESS"){
			layer.msg('操作成功', {icon: 1});
		}
		else{
			alert(data);
		}
	},"text");
}

function viewNotice(noticeId){
		var index = layer.open({
            type: 2,
            title: '阅读公告',
            content: 'Index/viewNotice?noticeId='+noticeId
        });
        layer.full(index);
}
</script>
</body>
</html>