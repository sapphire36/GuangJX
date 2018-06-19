<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加验证</title>
</head>
<body>
	<%
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");

		String cash = request.getParameter("cash");

		String handcash = request.getParameter("handcash");

		String fine = request.getParameter("fine");
		//设置请求编码格式
		String residue = request.getParameter("residue");
		request.setCharacterEncoding("utf-8");
		//连接数据库
		/*
		DbUtil dbUtil = new DbUtil();
		Connection con = dbUtil.getCon();
		//获取connection对象
		String sql = "insert into cash(id,cash,handcash,fine,residue)" + "values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		//定义日期格式
		//String pattern="yyyy-MM-dd";
		//SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		//String date = sdf.format(new java.util.Date());
		ps.setString(1, id);
		ps.setString(2, cash);
		ps.setString(3, handcash);
		ps.setString(4, fine);
		ps.setString(5, residue);
		ps.executeUpdate();
		//关闭资源
		ps.close();
		*/
		//重定向到shopping.jsp界面
		response.sendRedirect("/LightBox/views/Cash/CCash.jsp");
	%>

</body>
</html>