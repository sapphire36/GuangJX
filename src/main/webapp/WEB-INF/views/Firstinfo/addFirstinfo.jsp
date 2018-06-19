<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加员工信息页面</title>
</head>
<body>
	<form action="/LightBox/views/Firstinfo/addFirstinfologic.jsp"
		method="post">
		<table width="500px" height="60px" align="center">
			<tr height="30px">
				<td align="center" width="120px">职工编号</td>
				<td align="center" width="56px"><input type="text" name="id">
				</td>
				<td align="center" width="120px">姓名</td>
				<td align="center" width="56px"><input type="text" name="name">
				</td>
			</tr>
			<tr height="30px">
				<td align="center" width="120px">电话</td>
				<td align="center" width="56px"><input type="text" name="phone">
				</td>
			</tr>
		</table>
		<div align="center">
			<input type="submit" value="添加">
		</div>
	</form>
</body>
</html>