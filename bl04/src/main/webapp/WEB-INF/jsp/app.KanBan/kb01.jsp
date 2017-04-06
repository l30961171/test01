<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>车间大看板</title>
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

<style>
body{background:#000;width:100%;}
.tile{border-radius:10px;float:left;margin:0 3px 5px;width:16%;height:260px;}

.mac{width:100%;border:0;line-height: 1.3;}
.mac td{font-size:14px;} /* 字体可配置 14px */

.tileGreen{background-color: limegreen;}
.tileGreen td{color:#000;}

.titeYellow{background-color: yellow;}
.titeYellow td{color:#000;}

td.bolder{font-size:18px;font-weight: bolder;}
td.bolder2{font-size:16px;}
</style>
</head>
<body>
	<c:forEach items="${rows}" var="row">
	<div class="tile mac${row.macNo}" onclick="openDetail('${row.macNo}');">
	<table class="mac">
	<tr>
		<td>机台</td>
		<td class="macNo bolder"></td>
	</tr>
	<tr>
		<td>状态</td>
		<td class="zhuangTai bolder"></td>
	</tr>
	<tr>
		<td>品名</td>
		<td class="matName bolder2"></td>
	</tr>
	<tr>
		<td>标准产能</td>
		<td class="biaoZhunChanNeng bolder"></td>
	</tr>
	<tr>
		<td>注塑数</td>
		<td class="zhuSuQty"></td>
	</tr>
	<tr>
		<td>压键数</td>
		<td class="yaJianQty"></td>
	</tr>
	<tr>
		<td>装箱数</td>
		<td class="zhuangXiangQty bolder"></td>
	</tr>
	<tr>
		<td>不良数</td>
		<td class="buLiangQty bolder"></td>
	</tr>
	<tr>
		<td>达成率</td>
		<td class="daChenglv bolder"></td>
	</tr>
	<tr>
		<td>不良率</td>
		<td class="buLiangLv bolder"></td>
	</tr>
	</table>
	</div>
	</c:forEach>
	
	<div style="clear:both;"></div>
	
</body>

<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script> 

<script type="text/javascript">
$(function(){
	loadData();
	paintColor();
	setInterval(loadData,5*1000);
});

function loadData(){
	xpAjax("KanBan/kb01Json",{
		
	},function(data){
		for(var index in data){
			var row = data[index];
			for(var key in row){
				$(".mac"+row.macNo).find("."+key).text(row[key]);
			}
		}
		paintColor();
	},"json");
}

function paintColor(){
	$(".tile").each(function(index){
		var zt = $(this).find("td.zhuangTai");
		$(this).removeClass("tileGreen");
		$(this).removeClass("titeYellow");
		if(zt.text() == "生产"){
			$(this).addClass("tileGreen");
		} else {
			$(this).addClass("titeYellow");
		}
	});
}

//自动刷新网页，每5分钟
setTimeout(function(){
	window.location.reload();
},1000*60*5);
</script>
</html>