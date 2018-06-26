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
<title>NB-IoT锁管理</title>
<script type="text/javascript">
$(document).ready(function(){
	//页面加载时访问
	$("#doadd").click(function(){
		//绑定时间  #xx代表以xx为id的控件
		toastr.success("ok");
	  });
});

function doaddlockdevice(){
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/control/dograde",
		data:{"ID":0},
		success : function(data) {
			if(data.data=="true"){
				toastr.success("评价成功!");
			}else{
				toastr.error("评价失败!");
			}
		}
	});
}
</script>
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
							<th>设备名称</th>
							<th>IMEI编号</th>
							<th>是否在线</th>
							<th>设备状态</th>
							<th>注册状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${list}">
							<tr>
								<td align="center">${p.NAME}</td>
								<td align="center">${p.IMEI}</td>
								<td align="center">${p.ISONLINE}</td>
								<td align="center">${p.STATUS}</td>
								<td align="center">${p.ISREGIST}</td>
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
                    添加NB-IoT锁
                </h4>
            </div>
            <div class="modal-body">
                    <table class="table table-striped">
                        <tr>
                            <td align="right">
                                设备名称：
                            </td>
                            <td align="left">
                                <input type="text" name="NAME" placeholder=""/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                IMEI编号：
                            </td>
                            <td align="left">
                                <input type="text" name="LOCKID" placeholder=""/>
                            </td>
                        </tr>                                                                                       
                        <tr>
                            <td align="right">
                                <button id="doadd" class="btn btn-default" data-dismiss="modal">添加</button>
                            </td>
                            <td align="left">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            </td>
                        </tr>
                    </table>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</rapid:override>

<%@ include file="../home/base.jsp"%>