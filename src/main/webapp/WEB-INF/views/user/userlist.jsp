<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<rapid:override name="title">
	<title>光交箱管理</title>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button class="btn btn-primary">添加</button>
			<button class="btn btn-default">
				<i class=" fa fa-refresh "></i>更新
			</button>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>用户编号</th>
							<th>用户名</th>
							<th>用户类型</th>
							<th>创建时间</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="p" items="${ulist}">
							<tr>
								<td align="center">${p.SPEC}</td>
								<td align="center">${p.MADEADDRESS}</td>
								<td align="center">${p.LOCATION}</td>
								<td align="center">${p.ID }</td>
								<td align="center">
									<button class="btn btn-primary">
										<i class="fa fa-edit "></i> Edit
									</button>
									<button class="btn btn-danger">
										<i class="fa fa-pencil"></i> Delete
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</rapid:override>
<%@ include file="../home/base.jsp"%>