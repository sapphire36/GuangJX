<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1;
%>
<rapid:override name="title">
	<title>系统日志</title>
	<script type="text/javascript">
//script内容需要放在rapid override标签之间
$(document).ready(function(){
	//页面加载时自动执行该函数
	$("#refresh").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
	     location.reload();//刷新界面
	  }); 
	
	$("#deleteAll").click(function(){
	    if(confirm("确定清空日志吗")){  
			$.ajax({
				type : "POST",
				url : "<%=basePath1%>/manage/system/deletealljournal",
				data:{"ID":"tt"
				},
				success : function(data) {
					if(data.ret=="true"){
						toastr.success("清空日志成功!");
						location.reload();//刷新界面
					}else{
						toastr.error("清空日志失败!");
					}
				}
			});
	       return true;  
	    }  
	  }); 
	
	});	
	</script>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">
		<div class="panel-heading">		
			<button class="btn btn-default" id="refresh">
				<i class=" fa fa-refresh "></i>更新
			</button>
			<button class="btn btn-default" id="deleteAll">
				<i class=" fa fa-refresh "></i>清空表
			</button>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							
							<th>日志类型</th>
							<th>日志内容</th>						
							<th>时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="jour" items="${journallist}">
							<tr>								
								<td align="center">${jour.CONTENT}</td>		
														
								<c:if test="${jour.TYPE==1}">
                                 <td align="center">消息</td>
                                 </c:if>
                                 <c:if test="${jour.TYPE==2}">
                                 <td align="center">警告</td>
                                 </c:if>
                                 <c:if test="${jour.TYPE==3}">
                                 <td align="center">故障</td>
                                 </c:if>
                                 									
								<td align="center">${jour.ADDTIME}</td>															
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</rapid:override>
<%@ include file="../home/base.jsp"%>