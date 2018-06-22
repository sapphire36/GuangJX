<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<rapid:override name="title">
	<title>业务控制</title>
</rapid:override>
<rapid:override name="content">

	<div class="row">
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">请求开箱队列</div>
				<div class="panel-body">
					<div class="list-group">
						<a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#open"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a> <a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a> <a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a>
					</div>
					<div class="text-right">
						<a href="#">更多<i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">请求关箱队列</div>
				<div class="panel-body">
					<div class="list-group">

						<a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a> <a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a>
					</div>
					<div class="text-right">
						<a href="#">更多<i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">待评价队列</div>
				<div class="panel-body">
					<div class="list-group">

						<a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a> <a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a> <a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a> <a href="#" class="list-group-item" data-toggle="modal"
							data-backdrop="static" data-target="#add"> <span
							class="badge">7 minutes ago</span> <i class="fa fa-fw fa-comment"></i>
							Commented on a post
						</a>
					</div>
					<div class="text-right">
						<a href="#">更多<i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
    <!-- /.请求关箱队列处理模态框 开始-->
	<div class="modal fade" id="open" tabindex="-1" role="dialog"
		aria-labelledby="open" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">打开光交箱</h4>
				</div>
				<div class="modal-body">
				<img src="http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png" alt="" style="width:100%;">  
				
					<form action="/regjob" method="POST">
						<table class="table table-striped">
 
							<tr>
								<td align="right">
									<button id="submit" class="btn btn-default" type="submit">确认开锁</button>
								</td>
								<td align="center">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">拒绝开锁</button>
								</td>
								<td align="left">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- /.请求关箱队列处理模态框 结束 -->
</rapid:override>
<%@ include file="../home/base.jsp"%>
