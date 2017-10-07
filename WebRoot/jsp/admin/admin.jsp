<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<base href="<%=basePath%>">
		<title>京东后台管理页面</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.6/bootstrap.css" />

		<style type="text/css">
			*{
				margin:0 auto;
				text-align: left;
			}
			
			body {
				font: 14px/100% Arial, Verdana, "宋体";
			}
			
			#logo {
				width: 1300px;
				height: 80px;
				line-height: 80px;
				text-align: center;
			}
			
			#logoImg {
				position:absolute;
				left:0px;
				top:10px;
			}
			
			#logoLabel {
				font-size: 36px;
			}
			
			
			#middle {
				width: 1300px;
				height: 500px;
				
			}
			
			/*菜单开始*/
			#menu {
				width: 200px;
				float: left;
				text-align: center;	
			}
			
			.list-group {
				padding-left: 0;
				margin-bottom: 0px;
			}
			
			a:link {
				text-decoration: none;
			}
			
			#menu>ul>li>a {
				font-size: 17px;
				padding-left:30px;
			}
			
			li.active>div {
				display: none;
			}
			
			#menu>ul>li>div {
				display: none;
			}
			
			.list-group-item>a {
				padding-left:30px;
				color:#666;
			}
			.list-group-item>a:hover {
				color:#337AB7;
			}
			/*菜单结束*/
			
			#main {
				width: 1100px;
				height: 500px;
			
				float: left;
			}
			/*版权信息*/
			
			#copyright {
				width: 1300px;
				height: 60px;
				line-height: 60px;
				text-align: center;
			}
			
			label.copyright-label {
				color: #666;
				font-size:12px;
			}
		</style>

	</head>

	<body>
		<div id="logo">
			<img src="img/jdLogo.png" alt="logo" />
			<label id="logoLabel">京东后台管理系统</label>
		</div>
		
		<div id="middle">
			<div id="menu">
				<ul class="nav nav-pills nav-stacked">
					<li class="active">
						<a href="javascript:void(0)">商品种类管理</a>
						<div >
							<ul class="list-group">
								<li class="list-group-item"><a 
									href="JDDispatcherServlet?target=admin/category/addCategory.jsp" target="mainIFrame">添加一级商品种类</a></li>
								<li class="list-group-item"><a href="">管理一级商品种类</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">商品种类修改</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">商品种类查询</a></li>
							</ul>
						</div>
					</li>
					
					
					<li class="active">
						<a href="javascript:void(0)">商品管理</a>
						<div >
							<ul class="list-group">
								<li class="list-group-item"><a href="javascript:void(0);">商品添加</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">商品删除</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">商品修改</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">商品查询</a></li>
							</ul>
						</div>
					</li>
					
					
					<li class="active">
						<a href="javascript:void(0)">订单管理</a>
						<div >
							<ul class="list-group">
								<li class="list-group-item"><a href="javascript:void(0);">查看订单</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">修改订单</a></li>
							</ul>
						</div>
					</li>
					
					
					<li class="active">
						<a href="javascript:void(0)">用户管理</a>
						<div >
							<ul class="list-group">
								<li class="list-group-item"><a href="javascript:void(0);">用户添加</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">用户删除</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">用户修改</a></li>
								<li class="list-group-item"><a href="javascript:void(0);">用户查询</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		
			
			<div id="main">
				<iframe name="mainIFrame" src="" width="1100px" height="500px" frameborder="0">
	   			</iframe>
			</div>
		</div>
		
		<div id="copyright">
			<label class="copyright-label">Copyright&copy;2004-2016 蓝桥.com 版权所有</label>
		</div>		

		<!--引入bootstrap需要的js文件-->
		<script src="js/jQuery/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/bootstrap3.3.6/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function hideAllMenu () {
				$("#menu div").hide();
			}
			
			$(function() {
				//给#menu>ul>li>a 绑定事件

				$("#menu>ul>li>a").click(function() {
					
					var currentHeight = $("#menu").height();
					
					var clickedDivHeight = $(this).next().height();
					
					
					if($(this).next().is(":hidden")){//点击的li对应的菜单是隐藏的
						currentHeight = currentHeight + clickedDivHeight;
					}
					
					if(currentHeight>500){ //现在的菜单宽度 + 要显示的div宽度 >500
						//先关闭所有的
						hideAllMenu();
						$(this).next().toggle();
					}else{
						$(this).next().toggle();
					}
					
					
					
				});
			});
		</script>
	</body>

</html>