<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Index/webInfo.jsp" %>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0);

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<base href="<%=basePath%>">
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
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/css/H-ui.login.css" />

<!--[if IE 6]>
<script type="text/javascript" src="/Public/H-/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>

<body>
	<article class="page-container">
<div class="header">
<span style="margin-left:20px;"><%=companyName%></span>
<span class="r"><img src="/Public/H-/static/h-ui.admin/images/header_logo.png"></span>
</div>

<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" id="FF" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="userNum" name="userNum" type="text" placeholder="账号" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="userPsw" name="userPsw" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl" style="display:none;">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" 
          name="validateCode" id="validateCode"
          onblur="if(this.value==''){this.value='验证码'}" 
          onclick="if(this.value=='验证码'){this.value='';}" 
          value="验证码" style="width:150px;">
          <img src="Login/validateCode" id="validateCodeImg" onclick="changeValidateCode()"> 
          <a id="kanbuq" href="javascript:changeValidateCode();">看不清，换一张</a> </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"
          	onclick="loginPost()">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer"><%=login_footer%></div>

	</article>
<script type="text/javascript" src="/Public/H-/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="/Public/H-/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/Public/H-/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/Public/H-/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script>  

<script type="text/javascript">
function loginPost(){
	$.post("Login/loginPost", 
			$("#FF").serialize(),
			function(data){
			if(data == "SUCCESS"){
				window.top.location.href = "<%=basePath%>";
			} else {
				alert("登陆失败");
				changeValidateCode();
			}
		}, "text");
}
$(function(){
	$("#FF").keypress(function(e){
		if (event.keyCode==13) {
			loginPost();
		}
	});
});
function changeValidateCode(){
	$("#validateCodeImg").attr("src","Login/validateCode?_="+Math.random());
}

</script>
</body>
</html>