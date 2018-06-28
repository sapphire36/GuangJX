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
	<title>系统设置</title>
	restart
<script type="text/javascript">
//script内容需要放在rapid override标签之间
var type="true";
$(document).ready(function(){
	//页面加载时自动执行该函数
    $("#stop").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
	    if(confirm("确定关闭监听吗?")){  
			$.ajax({
				type : "POST",
				url : "<%=basePath%>/system/stop",
				data:{"ISALL":"tt" //测试,没有意义
				},
				success : function(data) {
					if(data.data=="true"){
						toastr.success("关闭成功!");
						//location.reload();//刷新界面
					}else{
						toastr.error("关闭失败!");
					}
				}
			});
	       return true;  
	    }  
	}); 
	
	$("#restart").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
	    if(confirm("确定重启吗")){  
			$.ajax({
				type : "POST",
				url : "<%=basePath%>/system/restart",
				data:{"ISALL":type //测试,没有意义
				},
				success : function(data) {
					if(data.data=="true"){
						toastr.success("重启成功!");
						//location.reload();//刷新界面
					}else{
						toastr.error("重启失败!");
					}
				}
			});
	       return true;  
	    }  
	}); 
	$("#NOTALL").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
		type="false"
	    toastr.success("监听系统登记的设备!");
	}); 
	$("#ALL").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
		type="true"
	    toastr.success("监听用户下所有设备!");
	}); 
	
}); 
</script>
</rapid:override>
<rapid:override name="content">
	<div class="panel panel-default">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					系统设置 <small>系统参数设置</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">监听设置</div>
					<div class="panel-body">
						<div class="row">
							<div class="form-group">
								<label>     监听项:  </label> <label class="radio-inline">
									<input type="radio" name="optionsRadiosInline"
									id="ALL" value="1" checked="">监听用户下所有设备
								</label> <label class="radio-inline"> 
								    <input type="radio"
									name="optionsRadiosInline" id="NOTALL"
									value="0">监听系统登记的设备
								</label>  
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<button id="restart" class="btn btn-default">启动监听</button>
							</div>
							<div class="col-lg-6">
								<button id="stop" class="btn btn-default">关闭监听</button>
							</div>
						</div>
						<!-- /.row (nested) -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
</rapid:override>
<%@ include file="../home/base.jsp"%>
