<%@ page language="java" import="java.util.*" errorPage="test.jsp" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><%-- 
System.out.println(path);
System.out.println(basePath);
// /Test
//  http://localhost:8080/Test/
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<form action="servlet/Hello" method="post">
		用户名：
		<input type="text" name="username" />
		<input type="submit" name="tijiao" value="提交"/>
		<!--html注释-->
		<%--JSP注释 --%>
		<%
			11111111
			int a =10/0;
		 %>
		
	</form>
  </body>
</html>
