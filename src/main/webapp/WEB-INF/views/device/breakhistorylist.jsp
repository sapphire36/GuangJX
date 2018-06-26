<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.kzcw.service.LightboxService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<rapid:override name="title">
<title>故障历史表</title>
<script type="text/javascript">
//script内容需要放在rapid override标签之间
$(document).ready(function(){		
	$("#refresh").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
	     location.reload();//刷新界面
	  })
</script>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button id="refresh" class="btn btn-default">
				<i class=" fa fa-refresh "></i>更新
			</button>
		</div>
		<div class="panel-body" id="body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th align="center">设备ID</th>						
							<th align="center">设备IEMI编号</th>
							<th align="center">故障原因</th>	
							<th align="center">时间</th>						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bre" items="${list}">
							<tr>
								<td align="center">${bre.ID}</td>
								<td align="center">${bre.IEME}</td>
								<td align="center">${bre.TYPE}</td>
								<td align="center">${bre.ADDTIME}</td>								                                                                
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</rapid:override>

<%@ include file="../home/base.jsp"%>