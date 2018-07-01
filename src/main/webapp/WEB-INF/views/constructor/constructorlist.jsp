<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<rapid:override name="title">
	<title>施工方管理</title>
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
							<th>单位名称</th>
							<th>公司地址</th>
							<th>电话</th>
							<th>押金</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="org" items="${constructorllist}">
							<tr>
								<td align="center">${org.ONAME}</td>
								<td align="center">${org.ADDRESS}</td>
								<td align="center">${org.TEL}</td>
								<td align="center">${org.DEPOSIT}</td>	
								<td align="center">${org.STATUS}</td>							
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
                    添加施工方信息
                </h4>
            </div>
            <div class="modal-body">
                <form action="/regjob" method="POST">
                    <table class="table table-striped">
                        <tr>
                            <td align="right">
                                单位名称：
                            </td>
                            <td align="left">
                                <input type="text" name="ONAME" placeholder="" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                               公司地址：
                            </td>
                            <td align="left">
                                <input type="text" name="ADDRESS" placeholder="" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                电话：
                            </td>
                            <td align="left">
                                <input type="text" name="TEL" placeholder="" required/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                               押金：
                            </td>
                            <td align="left">
                                <input type="text" name="DEPOSIT" placeholder="" required/>
                            </td>
                        </tr>    
                         <tr>
                            <td align="right">
                               状态：
                            </td>
                             <td align="left">
                                <select id="status">
                                    <option value="">0</option>
                                    <option value="">1</option>
                                </select>
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