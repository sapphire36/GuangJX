<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加押金信息页面</title>
</head>
<body>
	<form action="/LightBox/views/Cash/addCashlogic.jsp" method="post">
		<table width="500px" height="60px" align="center">
			<tr height="30px">
				<td align="center" width="120px">序号</td>
				<td align="center" width="56px"><input type="text" name="id">
				</td>
				<td align="center" width="120px">押金单位</td>
				<td align="center" width="56px"><input type="text" name="cash">
				</td>
			</tr>
			<tr height="30px">
				<td align="center" width="120px">上交押金数量</td>
				<td align="center" width="56px"><input type="text"
					name="handcash"></td>
				<td align="center" width="120px">罚款金额</td>
				<td align="center" width="56px"><input type="text" name="fine">
				</td>
			</tr>
			<tr height="30px">
				<td align="center" width="120px">剩余金额</td>
				<td align="center" width="56px"><input type="text"
					name="residue"></td>
			</tr>
		</table>
		<div align="center">
			<input type="submit" value="添加">
		</div>
	</form>
</body>
</html>