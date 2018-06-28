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
	gettodolist();
  //toastr.success("ok");
});
//var waittime=1000; //等待时间
function gettodolist(){
	//获取开箱队列
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/gettodolist",
		data :"test",
		success : function(data) {
			if(data.IsFlush=="true"){
				$("#todolist").html(data.data); 
				//getopenlist();
				//getcloselist();
				//getgradelist();
			}else{
			}
		}
	});
	setTimeout(gettodolist,200);
}


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
	setTimeout(getopenlist,1000);
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
	setTimeout(getgradelist,1000);
}

function doopen(btn){
	$("ul").children("li")
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/doopenlock",
		data:{"ID":0}, 
		success : function(data) {
			if(data.data=="true"){
				toastr.success("开锁成功!");
				//getopenlist();
				//getcloselist();
			}else{
				toastr.error("开锁失败!");
			}
		}
     });
}
	
function doclose(btn){
  $.ajax({
	type : "POST",
		url : "<%=basePath%>/control/docloselock",
		data:{"ID":0},
			success : function(data) {
			if(data.data=="true"){
					toastr.success("关锁成功!");
					//getcloselist();
					//getgradelist();
				}else{
					toastr.error("关锁失败!");
				}
			}
		});
	}
		
function dograde(btn){
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/dograde",
		data:{"ID":0},
		success : function(data) {
			if(data.data=="true"){
				toastr.success("评价成功!");
				//getgradelist();
			}else{
				toastr.error("评价失败!");
			}
		}
	});
}
</script>
</rapid:override>
<rapid:override name="content">
  <div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">待执行队列</div>
				<div class="panel-body">
					<div class="list-group" id="todolist"></div>
				</div>
			</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">请求开箱队列</div>
				<div class="panel-body">
					<div class="list-group" id="openlist"></div>
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
					<div class="list-group" id="closelist"></div>
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
					<div class="list-group" id="gradelist"></div>
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
							<td align="right">-
								<button id="doopen" class="btn btn-default" data-dismiss="modal"
									onclick="doopen(this)">确认开锁</button>
							</td>
							<td align="center">
								<button id="jujueopen" type="button" class="btn btn-default"
									data-dismiss="modal">拒绝</button>
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

	<!-- /.请求关箱队列处理模态框 开始-->
	<div class="modal fade" id="close" tabindex="-1" role="dialog"
		aria-labelledby="close" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">关闭光交箱</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td align="right">
								<button id="doclose" class="btn btn-default"
									data-dismiss="modal" onclick="doclose(this)">确认关闭</button>
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



	<!-- /.评价队列处理模态框 开始-->
	<div class="modal fade" id="grade" tabindex="-1" role="dialog"
		aria-labelledby="grade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">业务评价</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td align="right">
								<button id="dograde" class="btn btn-default"
									data-dismiss="modal" onclick="dograde(this)">提交</button>
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
	<!-- /.评价队列处理模态框 结束 -->
</rapid:override>
<%@ include file="../home/base.jsp"%>
