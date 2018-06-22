<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<rapid:override name="title">
	<title>光交箱管理</title>
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
							<th>锁编号</th>
							<th>规格</th>
							<th>出产地</th>
							<th>安装位置</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${llist}">
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
	
	
	<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加光交箱
                </h4>
            </div>
            <div class="modal-body">
                <form action="/regjob" method="POST">
                    <table class="table table-striped">
                        <tr>
                            <td align="right">
                                姓名：
                            </td>
                            <td align="left">
                                <input type="text" name="username" placeholder="姓名：" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                性别：
                            </td>
                            <td align="left">
                                <select id="sex">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                出生日期：
                            </td>
                            <td align="left">
                                <input type="date" name="date"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                籍贯：
                            </td>
                            <td align="left">
                                <input type="text" name="username" placeholder="籍贯" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                电话：
                            </td>
                            <td align="left">
                                <input type="text" name="username" placeholder="电话" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                邮箱：
                            </td>
                            <td align="left">
                                <input type="text" name="username" placeholder="邮箱" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                密 码：
                            </td>
                            <td align="left">
                                <input type="password" name="password" id="password" placeholder="密码" required
                                       onkeyup="passwd()"/>

                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                密码确认：
                            </td>
                            <td align="left">
                                <input type="password" name="password2" placeholder="确认密码" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <button id="submit" class="btn btn-default" type="submit">注册</button>
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