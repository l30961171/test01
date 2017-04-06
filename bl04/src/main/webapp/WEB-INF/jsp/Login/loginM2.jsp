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

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="/Public/ace-master/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/Public/ace-master/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="/Public/ace-master/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="/Public/ace-master/assets/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="/Public/ace-master/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="/Public/ace-master/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/Public/ace-master/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="/Public/ace-master/assets/js/html5shiv.min.js"></script>
		<script src="/Public/ace-master/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout light-login" style="background:#fff;height:100%;">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="left">
								<h1>
									<img src='/Public/upload/login-logo.jpg'/ style="max-width:70px;">
									<span class="blue"><%=webTitle%></span>
								</h1>
							</div>

							<div class="space-6"></div>

							<div class="position-relative" style="width:80%;margin:0 auto;">
								<div id="login-box" >
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请先登录
											</h4>

											<div class="space-6"></div>

											<form id="FF">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="账号" 
															id="userNum" name="userNum"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" 
															id="userPsw" name="userPsw"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div style="float:right;">
													
														<button type="button" class="btn btn-sm btn-primary" 
															onclick="loginPost()">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登陆</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->

									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->


							</div><!-- /.position-relative -->

						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="/Public/ace-master/assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="/Public/ace-master/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='/Public/ace-master/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		function loginPost(){
			$.post("Login/loginPostFromWeChat", 
					$("#FF").serialize(),
					function(data){
					if(data == "SUCCESS"){
						window.top.location.href = "<%=basePath%>.${sessionScope.redirectUrl}";
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
