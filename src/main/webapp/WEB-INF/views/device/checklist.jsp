<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.kzcw.service.LightboxService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path1;
%>

<rapid:override name="title">
	<title>安装审核</title>
	<script type="text/javascript">

$(document).ready(function(e) {
	getchecklist();
	$('#applylist').on('click','a',function(){
		var text=$(this).find("i").text();
		var data = text.split(':');
		$("#editlightboxname").attr("value",data[0]);
		$("#editlockid").attr("value",data[1]);
		$("#editlocation").attr("value",data[2]);
	 })
	 
	 $("#dopass").click(function(){
		//通过审核
		 dopass();//执行编辑
     });
	 $("#doreject").click(function(){
		//拒绝审核
		 doreject();//执行编辑
     }); 
});
function dopass(){
	//通过审核 editlightboxname editlockid editspec editmadetype editlocation editpeople
	var editlightboxname=$("#editlightboxname").val();//获取id为lightboxname的值
	var editlockid=$("#editlockid").val();//获取id为lockid的值
	var editspec=$("#editspec").val();//获取id为spec的值
	var editmadetype=$("#editmadetype").val();//获取id为madetype的值
	var editlocation=$("#editlocation").val();//获取id为location的值
	var editpeople=$("#editpeople").val();//获取id为people的值
	//根据选择器获取数据
	//参考文档:http://www.w3school.com.cn/jquery/attributes_attr.asp
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/device/doPassCheck",
		data:{"editlightboxname":editlightboxname,
			  "editlockid":editlockid,
			  "editspec":editspec,
			  "editmadetype":editmadetype,
			  "editlocation":editlocation,
			  "editpeople":editpeople
			  },
		success : function(data) {
			if(data.data=="true"){
				toastr.success("审核成功!");
			}else{
				toastr.error(data.data);
			}
		}
	});
}

function doreject(){
	//拒绝审核
	var editlockid=$("#editlockid").val();//获取id为lockid的值
	//根据选择器获取数据
	//参考文档:http://www.w3school.com.cn/jquery/attributes_attr.asp
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/device/doRejectCheck",
		data:{
			  "editlockid":editlockid
			  },
		success : function(data) {
			if(data.data=="true"){
				toastr.success("已拒绝该申请!");
			}else{
				toastr.error(data.data);
			}
		}
	});
}
function getchecklist(){
	//获取审核队列
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/device/getchecklist",
		data : "test",
		success : function(data) {
				$("#applylist").html(data.data);
				}
			});
			setTimeout(getchecklist, 1000); //设置定时器,每1000ms执行一次
		}
	</script>
</rapid:override>
<rapid:override name="content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">申请审核队列</div>
			<div class="panel-body">
				<div class="list-group" id="applylist"></div>
				<div class="text-right">
					<a href="#">更多<i class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
		</div>
	</div>


	<!-- /.申请审核队列处理模态框 开始-->
	<div class="modal fade" id="docheck" tabindex="-1" role="dialog"
		aria-labelledby="open" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">申请审核</h4>
				</div>

				<div class="modal-body" style="width: 600px; height:425px;">

					<div id="nav" style="width: 330px; float: left;padding:5px;">
						<table class="table table-striped">
							<tr>
								<td align="right">箱体名称：</td>
								<td align="left"><input id="editlightboxname" type="text"
									name="NAME" placeholder="" /></td>
							</tr>
							<tr>
								<td align="right">IMEI编号：</td>
								<td align="left"><input id="editlockid" type="text"
									name="LOCKID" placeholder="" /></td>
							</tr>
							<tr>
								<td align="right">规格：</td>
								<td align="left"><input id="editspec" type="text"
									name="SPEC" placeholder="" /></td>
							</tr>
							<tr>
								<td align="right">厂家型号：</td>
								<td align="left"><input id="editmadetype" type="text"
									name="MADETYPE" placeholder="" /></td>
							</tr>
							<tr>
								<td align="right">安装位置：</td>
								<td align="left"><input id="editlocation" type="text"
									name="LOCATION" placeholder="" /></td>
							</tr>
							<tr>
								<td align="right">安装人员：</td>
								<td align="left"><input id="editpeople" type="text"
									name="PEOPLE" placeholder="" /></td>
							</tr>
							<tr>
								<td align="right">
									<button id="dopass" class="btn btn-default"
										data-dismiss="modal">通过</button>
								</td>
								<td align="center">
									<button id="doreject" type="button" class="btn btn-default"
										data-dismiss="modal">拒绝</button>
								</td>
								<td align="left">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</td>
							</tr>
						</table>
					</div>

					<div id="section" style="width: 200px; float: left; padding:10px;">
						 <img id="newpic" src="/GuangJX/manage/device/showImage"  width="220" height="300" alt="Applied picture" />
					</div>


				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


</rapid:override>
<%@ include file="../home/base.jsp"%>
