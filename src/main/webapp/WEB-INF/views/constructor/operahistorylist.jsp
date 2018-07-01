<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<rapid:override name="title">
	<title>施工操作历史表</title>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">		
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>光交箱箱体编号</th>
							<th>施工单位编号</th>
							<th>评分</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="his" items="${llist}">
							<tr>
								<td align="center">${his.ID}</td>
								<td align="center">${his.BOXID}</td>
								<td align="center">${his.ORGANIZATIONID}</td>
								<td align="center">${his.SCORE}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</rapid:override>
<%@ include file="../home/base.jsp"%>