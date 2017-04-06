<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${row.noticeTitle}</title>
<link rel="stylesheet" type="text/css" href="/Public/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css" />
</head>
<body>
<h2 style="text-align:center;">${row.noticeTitle}</h2>
<div style="margin:0 20px;">
${row.noticeContent}
</div>
</body>
<script type="text/javascript" src="/Public/easyUI/jquery.min.js"></script>
<script type="text/javascript" src="/Public/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<script type="text/javascript" src="/Public/tpl/base/ljbUtil.js"></script>
<script type="text/javascript">
//显示代码高亮
SyntaxHighlighter.highlight();
</script>
</html>