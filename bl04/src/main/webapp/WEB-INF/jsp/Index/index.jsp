<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="webInfo.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<script type="text/javascript">
function isMobile() {
	var ua = window.navigator.userAgent.toLowerCase();
	return (ua.match(/mobile/i) == 'mobile');
}
	if(isMobile()){
		//window.top.location.href = "<%=basePath%>BusinessWeChat/listBusinessOrder";
	}
</script>
<LINK rel="Bookmark" href="favicon.ico" >
<LINK rel="Shortcut Icon" href="favicon.ico" />
<title><%=webTitle%></title>
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
<link rel="stylesheet" type="text/css" href="/Public/H-/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/css/style.css" />

<!--[if IE 6]>
<script type="text/javascript" src="/Public/H-/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="<%=basePath%>"><%=companyName%></a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="<%=basePath%>"><%=companyName%></a>  <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>${User.userName}</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${User.userNum} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="openUserInfo(1)">个人信息</a></li>
							<li><a href="javascript:;" onclick="openUserInfo(2)">修改密码</a></li>
							<li><a href="javascript:;" onclick="openAccessLog()">访问记录</a></li>
							<li><a href="BusinessWeChat/listBusinessOrder">手机端</a></li>
							<li><a href="javascript:;" onclick="fullScreen();">全屏</a></li>
							<li><a href="Login/logout">退出</a></li>
						</ul>
					</li>
					<li><img src="/Public/H-/static/h-ui.admin/images/header_logo.png"></li>
					<!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>-->
					
				</ul>
			</nav>
		</div>
	</div>
</header>
	
<aside class="Hui-aside">
	<div id="leftMenu" class="menu_dropdown bk_2">
		
	</div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="首页" data-href="welcome.html">首页</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="Index/welcome"></iframe>
		</div>
	</div>
</section>
<div style="display:none;">
	<ul id="menuTree" class="ztree" style="margin-left:10px;"></ul>
</div>

<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="/Public/H-/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script> 

<script type="text/javascript">
var zNodes = ${userMenu};

	$(function(){
		$.fn.zTree.init($("#menuTree"), {
		data: {
			simpleData: {
				enable: true,
				idKey: "menuId",
				pIdKey: "menuFid",
				rootPId: "0"
			},
			key:{
				name:"menuName",
				url:"menuUrl"
			}
		}
		} , zNodes);
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		var menuObj = zTree.getNodes();
		makeMenu(menuObj);
		$.Huifold(".menu_dropdown dl dt",".menu_dropdown dl dd","fast",2,"click");
	});

function makeMenu(menuObj){
	for(var i in menuObj){
		var menuHeader = menuObj[i];
		if(menuHeader.level > 0){
			continue;
		}
		var tplH = makeMenuHeader(menuHeader);
		$(tplH).appendTo("#leftMenu");
		
		var items = menuHeader.children;
		for(var j in items){
			var item =items[j];
			var tplI = makeMenuItem(item);
			$(tplI).appendTo("#menuI_"+menuHeader.menuId);
		}
	}
}

function makeMenuHeader(menu){
	var menuId = menu.menuId;
	var menuIcon = menu.menuIcon;
	var menuName = menu.menuName;
	menuIcon = menuIcon?menuIcon:"&#xe63d;";
	var html = '<dl id="menuH_'+menuId+'">';
		html += '<dt><i class="Hui-iconfont">'+menuIcon+'</i> '+menuName;
		html += '<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
		html += '<dd>';
		html += '<ul id="menuI_'+menuId+'"></ul>';
		html += '</dd>';
		html += '</dl>';
	return html;
}

function makeMenuItem(menu){
	var menuUrl = menu.menuUrl;
	var menuIcon = menu.menuIcon;
	var menuName = menu.menuName;
	menuIcon = menuIcon?'<i class="Hui-iconfont">'+menuIcon+'</i> ':'';
	var html = '<li><a _href="'+menuUrl+'" href="javascript:void(0)" data-title="'+menuName+'">'+menuIcon+menuName+'</a></li>';
	return html;
}

var userInfoWin = null;
function openUserInfo(infoType){
		var w = ($(window).width() - 100);
		var h = ($(window).height() - 100);
		userInfoWin = layer.open({
            type: 2,
            title: '用户信息',
            area: [w+'px', h +'px'],
            content: 'Index/userInfo?infoType='+infoType
        });
       // layer.full(index);
}

function openAccessLog(){
		var w = ($(window).width() - 100);
		var h = ($(window).height() - 100);
		var days = 30;
		userInfoWin = layer.open({
            type: 2,
            title: '访问记录(近'+days+'天)',
            area: [w+'px', h +'px'],
            content: 'Index/loginLog?days='+days
        });
}
function closeUserInfoWin(){
	layer.close(userInfoWin);
}

</script> 
</body>
</html>