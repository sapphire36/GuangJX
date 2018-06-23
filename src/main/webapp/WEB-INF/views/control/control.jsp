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
	<title>业务控制</title>
<link href="/GuangJX/css/toastr.css" rel="stylesheet" />
<script src="/GuangJX/js/toastr.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
// 测试引用是否成功
window.onbeforeunload=function(){
	return "是否要离开";
}
function doRefresh(){
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/dorefresh",
		data :"test",
		success : function(data) {
			if(data.data=="true"){
			}else{
				return "是否要离开";
			}
		}
	});
}

$(document).ready(function(e) {
	doRefresh();//刷新页面
	getopenlist();
	getcloselist();
	getgradelist();
  //toastr.success("ok");
});

function getopenlist(){
	//获取开箱队列
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/getopenlist",
		data :"test",
		success : function(data) {
			if(data.IsFlush=="true"){
				$("#openlist").html(data.data); 
			}else{
			}
		}
	});
	setTimeout(getopenlist,100);
}

function getcloselist(){
	//获取关箱队列
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/getcloselist",
		data :"test",
		success : function(data) {
			if(data.IsFlush=="true"){
				$("#closelist").html(data.data); 
			}else{
			}
		}
	});
	setTimeout(getcloselist,1000);
}
function getgradelist(){
	//获取关箱队列
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/getgradelist",
		data :"test",
		success : function(data) {
			if(data.IsFlush=="true"){
				$("#gradelist").html(data.data); 
			}else{
			}
		}
	});
	setTimeout(getgradelist,10000);
}

function doopen(){
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/doopenlock",
		data :"ID",
		success : function(data) {
			if(data.data=="true"){
				toastr.success("开锁成功!");
			}else{
				toastr.error("开锁失败!");
			}
		}
	});
}
</script>
</rapid:override>
<rapid:override name="content">

	<div class="row">
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">请求开箱队列</div>
				<div class="panel-body">
					<div class="list-group" id="openlist">
		 
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
					<div class="list-group" id="closelist">
	 
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
					<div class="list-group" id="gradelist">
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
					<table class="table table-striped">
						<tr>
							<td align="right">
								<button id="doopen" class="btn btn-default" data-dismiss="modal" onclick="doopen()">确认开锁</button>
							</td>
							<td align="center">
								<button type="button" class="btn btn-default"
									data-dismiss="modal"></button>
							</td>
							<td align="left">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- /.请求关箱队列处理模态框 结束 -->
</rapid:override>
<%@ include file="../home/base.jsp"%>
