<%@ page language="java" import="java.util.*" 
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<h1>演示传参</h1>
	<!-- 
		http://localhost:8080/Spring04/index.jsp
		http://localhost:8080/Spring04/demo/test1.do
	 -->
	<form action="demo/hello.do" method="post">
		账号：<input type="text" name="userName"/><br/><br/>
		密码：<input type="password" name="password"/><br/><br/>
		<input type="submit" value="提交"/>
	</form>    
  </body>
</html>
