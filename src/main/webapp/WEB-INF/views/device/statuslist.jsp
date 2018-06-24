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
<script src="http://code.jquery.com/jquery-latest.js"></script>

<rapid:override name="title">
	<title>光交箱状态管理</title>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">
		<div class="panel-heading">	
			<button class="btn btn-default">
				<i class=" fa fa-refresh "></i>更新
			</button>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>箱体名称</th>
							<th>光交箱状态</th> <!--1.开锁   2.关锁   3.非法开锁  -->
							<th>电池电压</th>
							<th>机箱温度</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sta" items="${llist}">
							<tr>
								<td align="center">${sta.NAME}</td>
								<td align="center">${sta.STATUS}</td>
								<td align="center">${sta.VOLTAGE}</td>
								<td align="center">${sta.TEMPERATURE}</td>								
								<td align="center">
									<button class="btn btn-primary">
										<i class="fa fa-edit "></i> Edit
									</button>
									<button id="delete" class="btn btn-default" data-dismiss="modal" onclick="showConfirm()">
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
	
	
	<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>               
            </div>
            <div class="modal-body">
                <form action="/regjob" method="POST">
                    <table class="table table-striped">                                                                                                                                                                                                                                                                   
                        <tr>
                            <td align="right">
                                <button id="submit" class="btn btn-default" type="submit">添加</button>
                            </td>
                            <td align="left">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</rapid:override>
<%@ include file="../home/base.jsp"%>