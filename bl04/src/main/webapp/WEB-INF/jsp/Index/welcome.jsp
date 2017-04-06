<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="webInfo.jsp" %>
<%
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
<link rel="stylesheet" type="text/css" href="/Public/H-/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/Public/H-/static/h-ui.admin/css/style.css" />

<!--[if IE 6]>
<script type="text/javascript" src="/Public/H-/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
</head>
<div class="pd-20" style="padding-top:20px;">
  <p class="f-20 text-success">欢迎使用<%=webTitle%>！</p>
  
  <c:forEach items="${types}" var="type">
  <table class="table table-border table-bordered table-bg mb-20">
    <thead>
      <tr>
        <th scope="col">${type.noticeTypeName}
        <span class="r"><a href="javascript:;" onclick="moreNotice(${type.noticeTypeId})">更多 &gt;&gt;</a></span></th>
      </tr>
    </thead>
    <tbody class="type_${type.noticeTypeId}">
    </tbody>
  </table>
  </c:forEach>
  
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
function initTr(noticeId,noticeTypeId,title,lastUpdate){
	var tr = "<tr>";
	tr += "<td><span class='l'><a href='javascript:;' onclick='viewNotice("+noticeId+")'>"+title+"</a></span>";
	tr += "<span class='r'>"+lastUpdate+"</span></td>";
	tr += "</tr>";
	$(".type_"+noticeTypeId).append(tr);
}
<c:forEach items="${notices}" var="notice">
initTr("${notice.noticeId}","${notice.noticeTypeId}","${notice.noticeTitle}","${notice.lastUpdate}");
</c:forEach>

function viewNotice(noticeId){
		var index = layer.open({
            type: 2,
            title: '阅读公告',
            content: 'Index/viewNotice?noticeId='+noticeId
        });
        layer.full(index);
}

function moreNotice(noticeTypeId){
		var index = layer.open({
            type: 2,
            title: '更多',
            content: 'Index/moreNotice?noticeTypeId='+noticeTypeId
        });
        layer.full(index);
}
</script>
</body>
</html>