<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">

<title>添加一级商品种类</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">



<style type="text/css">


body {
	font: 16px/100% Arial, Verdana, "宋体";
}

div.categoryClass {
	margin-bottom: 20px;
}

#categoryFieldset {
	width: 370px;
	height:340px;
	padding: 50px;
	position:relative;
	top:0px;
	left:200px;
}

fieldset legend {
	font-size:20px;
}

fieldset label {
	margin-right: 50px;
	
}

/*bootstrap 按钮样式*/
.btn {
  display: inline-block;
  padding: 6px 12px;
  margin-bottom: 0;
  font-size: 14px;
  font-weight: normal;
  line-height: 1.42857143;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
      touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
}

.btn-success {
  color: #fff;
  background-color: #5cb85c;
  border-color: #4cae4c;
}

.addCategoryBtn {
	position:absolute;
	right:50px;
	bottom:50px;
}

.form-control
 {
  
  padding: 0px 10px;
  font-size: 16px;
  border-radius: 3px;
}
</style>



</head>

<body>
	
	<form action="CategoryServlet" method="post">
		<fieldset id="categoryFieldset">
			<legend>添加一级商品种类</legend>
			<div class="categoryClass">
				<label for="cname">商品种类名称</label><input type="text" class="form-control" name="cname"
					id="cname" />
			</div>

			<div class="categoryClass">
				<label for="cdesc">商品种类描述</label><input type="text" class="form-control" name="cdesc"
					id="cdesc" />
			</div>

			<div class="categoryClass">
				<input type="submit" class="btn btn-success addCategoryBtn" value="添加一级商品种类" />
			</div>
		</fieldset>
	</form>

</body>
</html>
