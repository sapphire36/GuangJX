<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.kzcw.service.LightboxService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1;
%>
<rapid:override name="title">
<title>安装审核</title>
<script type="text/javascript">
// 测试引用是否成功
window.onbeforeunload=function(){
	return "是否要离开";
}
function doRefresh(){
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/control/dorefresh",
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
	getchecklist();
});
//var waittime=1000; //等待时间

function getchecklist(){
	//获取审核队列
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/device/getchecklist",
		data :"test",
		success : function(data) {
			if(data.IsFlush=="true"){
				$("#applylist").html(data.data); 
				//toastr.error("获取报警信息异常1!");
			}else{
				toastr.error("获取报警信息异常2!");
			}
		}
	});
	setTimeout(getchecklist,1000);
}

</script>
</rapid:override>
<rapid:override name="content">
	<div class="row">
		<div class="col-md-4 col-sm-4">
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
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">审核通过队列</div>
				<div class="panel-body">
					<div class="list-group" id="passlist"></div>
					<div class="text-right">
						<a href="#">更多<i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
			</div>
		</div>
				
	<!-- /.申请审核队列处理模态框 开始-->
	<div class="modal fade" id="apply" tabindex="-1" role="dialog"
		aria-labelledby="open" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">申请审核</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
					 <tr>
                            <td align="right">
                                箱体名称：
                            </td>
                            <td align="left">
                                <input id="editlightboxname" type="text" name="NAME" placeholder=""/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
           IMEI编号：
                            </td>
                            <td align="left">
                                <input id="editlockid" type="text" name="LOCKID" placeholder=""/>
                            </td>
                        </tr>                            
                         <tr>
                            <td align="right">
                                规格：
                            </td>
                            <td align="left">
                                <input id="editspec" type="text" name="SPEC" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                厂家型号：
                            </td>
                            <td align="left">
                                <input id="editmadetype" type="text" name="MADETYPE" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                安装位置：
                            </td>
                            <td align="left">
                                <input id="editlocation" type="text" name="LOCATION" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                安装人员：
                            </td>
                            <td align="left">
                                <input id="editpeople" type="text" name="PEOPLE" placeholder=""/>
                            </td>
                        </tr>                                                                                                           
						<tr>
							<td align="right">-
								<button id="doopen" class="btn btn-default" data-dismiss="modal"
									onclick="doopen(this)">通过</button>
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


</rapid:override>
<%@ include file="../home/base.jsp"%>
