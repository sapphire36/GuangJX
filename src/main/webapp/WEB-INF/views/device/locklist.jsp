<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<rapid:override name="title">
	<title>通信锁管理</title>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button class="btn btn-primary" data-toggle="modal" data-backdrop="static" data-target="#add">添加</button>
			<button class="btn btn-default">
				<i class=" fa fa-refresh "></i>更新
			</button>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>IMEI编号</th>
							<th>PSK码</th>
							<th>规格</th>							
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lock" items="${llist}">
							<tr>
								<td align="center">${lock.IMEI}</td>
								<td align="center">${lock.PSK}</td>
								<td align="center">${lock.SPEC}</td>
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
	
	
	<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加通信锁信息
                </h4>
            </div>
            <div class="modal-body">
                <form action="/regjob" method="POST">
                    <table class="table table-striped">
                        <tr>
                            <td align="right">
       		IEMI编号：
                            </td>
                            <td align="left">
                                <input type="text" name="IEMI" placeholder="" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
      		PSK码：
                            </td>
                            <td align="left">
                                <input type="text" name="PSK" placeholder="" required/>
                            </td>                            
                        </tr>
                        <tr>
                            <td align="right">
          	规格：
                            </td>
                            <td align="left">
                                <input type="text" name="SPEC" placeholder="" required/>
                            </td>
                        </tr>                                               
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