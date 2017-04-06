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
<title>访问记录</title>
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
<div class="pl-20 pr-20 pt-5">
	<div class="cl pd-5 bg-1 bk-gray">
		<div id="xpCondition"></div>
	</div>
		
		<table id="tb" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th colName="logTime">登录时间</th>
					<th colName="logIP">登录IP</th>
					<th colName="userNum">账号</th>
					<th colName="userName">用户名</th>
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


</body>

<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/Public/H-/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
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
	var url = "BaseWeb/loginLogJson";
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


function sel(){
	initMapIn();
	var condition = $("#xpCondition").getXPCondition();
	mapIn["where"] = condition.where;
	mapIn["values"] = condition.values;
	xplaypage();
}


/**
 * 动态条件组合
 */
var xpConditionObj = [{
	"text":"登录IP",
	"inputName":"a.logIP"
},{
	"text":"登录时间",
	"inputName":"a.logTime"
},{
	"text":"账号",
	"inputName":"b.userNum"
}];
$("#xpCondition").xpCondition(xpConditionObj);
addXPfieldRow("#xpCondition",{"condition":">","fieldName":"a.logTime","inputVal":yyyy_mm_dd(),"logic":"and"});

sel();
//*******************end 动态条件组合
</script>
</html>