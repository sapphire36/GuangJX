<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1;
%>
<rapid:override name="title">
<title>设备状态历史纪录</title>
<script type="text/javascript">
//script内容需要放在rapid override标签之间
$(document).ready(function(){		
	$("#refresh").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
	     location.reload();//刷新界面
	  })
});
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
							<th align="center">电池电压</th>
							<th align="center">机箱温度</th>
							<th align="center">设备状态</th>
							<th align="center">时间</th>							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sta" items="${statuslist}">
							<tr>
							    <td align="center">${sta.ID}</td>
								<td align="center">${sta.IEME}</td>
								<td align="center">${sta.VOLTAGE}</td>
								<td align="center">${sta.TEMPERATURE}</td>
							    <c:if test="${sta.LOCKSTATUS==1}">
                                <td align="center">开</td>
                                </c:if>
                                <c:if test="${sta.UNLOCKSTATUS==0}">
                                <td align="center">关</td>
                                </c:if>
								<td align="center">${sta.ADDTIME}</td>								                                                                
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</rapid:override>

<%@ include file="../home/base.jsp"%>