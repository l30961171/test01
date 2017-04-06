<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>历史看板记录</title>
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
<article class="page-container">		
	<div class="cl pd-5 bg-1 bk-gray">
		<div id="xpCondition"></div>
	</div>
		
	
<table id="tb" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
				    <th funName="funIndex">序号</th>
				    <th colName="macNo">机台</th>
					<th colName="zhuangTai">状态</th>
					<th colName="matName">物料品名</th>
					<th colName="biaoZhunChanNeng">标准产能</th>
					<th colName="zhuSuQty">注塑数</th>
					<th colName="yaJianQty">压键数</th>
					<th colName="zhuangXiangQty">装箱数</th>
					<th colName="buLiangQty">不良数</th>
					<th colName="banCiDate">班次日期</th>
					<th colName="banBie">班别</th>
					<th colName="createUser">创建者</th>
					<th colName="createTime">创建时间</th>
					
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		
		<div style="display:none;">
					<span id="pager" class="pd-10 l"></span>
					<span class="pd-10 r">共有数据：<strong id="total">0</strong> 条</span>
		</div>
</article>
</body>
<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/Public/H-/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script>  
<script type="text/javascript">
function funIndex(row,index){
	return index+1;
}
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
	var url = "KanBan/listKb01RecJson";
	xpAjax(url,
		mapIn,
		function(data){
		$("#tb").jsonTr(data);
		$("#total").text(data.length);
		jump = false;
		xplaypage(data.totalPage);
		$("#tb tbody tr").addClass("text-c");
	},"json");
}
xplaypage();

var xpConditionObj = [{
	"text":"机台",
	"inputName":"macNo"
},{
	"text":"班次日期",
	"inputName":"banCiDate"
}];
$("#xpCondition").xpCondition(xpConditionObj);



function sel(){
	initMapIn();
	var condition = $("#xpCondition").getXPCondition();
	mapIn["where"] = condition.where;
	mapIn["values"] = condition.values;
	xplaypage();
}


</script>
</html>