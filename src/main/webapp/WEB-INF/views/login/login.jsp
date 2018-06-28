<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>光交箱管控系统</title>
<link rel="stylesheet" href="/GuangJX/css/Login/login.css">
<!--为了让IE支持HTML5元素，做如下操作：-->
<!--[if IE]> 
		<script type="text/javascript"> 
		document.createElement("section"); 
		document.createElement("header"); 
		document.createElement("footer"); 
		</script> 
		<![endif]-->
</head>
<body>
	<div class="wrap">
		<form action="#" method="post">
			<section class="loginForm"> <header>
			<h1>光交箱管控系统</h1>
			</header>

			<div class="loginForm_content">
				<fieldset>
					<div class="inputWrap">
						<input type="text" name="username" placeholder="邮箱/会员帐号/手机号"
							autofocus required>
					</div>
					<div class="inputWrap">
						<input type="password" name="password" placeholder="请输入密码"
							required>
					</div>
				</fieldset>

				<fieldset>
					<input type="checkbox" checked="checked"> <span>下次自动登录</span>
				</fieldset>

				<fieldset>
					<a type="submit" href="http://localhost:8080/GuangJX/home/index">
						<button>登录</button>
					</a> <a href="#">忘记密码？</a> <a href="#">注册</a>
				</fieldset>

			</div>
			</section>
		</form>
	</div>

</body>
</html>