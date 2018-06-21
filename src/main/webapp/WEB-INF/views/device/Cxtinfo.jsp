<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">		
			 规格<button>${spec}</button>
			 <table cellspacing="0" border="1" class="table1">
<thead>
   <tr>
    	<th width="300">规格</th>
    	<th width="300">出产地</th>
    	<th width="300">安装位置</th>
    	<th width="300">锁编号</th>
   		<th  width="300">编辑</th>
   		<th  width="300">删除</th>
   </tr>
</thead>
<tbody>

<c:forEach var="p" items="${llist}">
	<tr>
		<td align="center">${p.SPEC}</td>
		<td align="center">${p.MADEADDRESS}</td>
		<td align="center">${p.LOCATION}</td>
		<td align="center">${p.ID }</td>
	</tr>
</c:forEach>

</tbody>
</table>
			 
		</div>
</body>
</html>