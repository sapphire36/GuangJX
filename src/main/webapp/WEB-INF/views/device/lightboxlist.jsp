<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1;
%>
<rapid:override name="title">
<title>光交箱信息管理</title>
<script type="text/javascript">
//script内容需要放在rapid override标签之间
$(document).ready(function(){
	//页面加载时自动执行该函数
	$("#doadd").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
		 doaddlightbox();//执行添加
	  }); 
	  
	 $("#doedit").click(function(){
		//绑定 id为doedit的控件事件处理
		 doeditlightbox();//执行编辑
	  });
	 
	$("#refresh").click(function(){
		//绑定事件  #xx代表以xx为id的控件
		//参考文档:http://www.w3school.com.cn/jquery/jquery_ref_selectors.asp
	     location.reload();//刷新界面
	  });
	
	$("#body button.btn-primary").click(function(){
		//根据class来选择 编辑
		//var emeielem= $(this).parent().prev().prev().prev().prev();
		//获取emei内容 this代表当前点击的控件
		//详见:https://www.runoob.com/jquery/jquery-traversing-siblings.html
		//var emeitext=emeielem.text();
		var id=$(this).prev().val();
		$.ajax({
			type : "POST",
			url : "<%=basePath1%>/manage/device/getlightbox",
			data:{"ID":id
			},
			success:function(data) {
                 if(data.data=="true"){
                	 $("#editlightboxname").attr("value",data.NAME);
                	 $("#editlockid").attr("value",data.LOCKID);                	
                	 $("#editspec").attr("value",data.SPEC);
                	 $("#editmadetype").attr("value",data.MADETYPE);
                	 $("#editlocation").attr("value",data.LOCATION);
                	 $("#editpeople").attr("value",data.PEOPLE);                	 
                	 $("#editid").attr("value",id);
                 }else{
                     toastr.error("数据库连接错误!");
                 }
           
			}
		});
	  });
	$("#body button.btn-danger").click(function(){
		//根据class来选择 删除
		//var emeielem= $(this).parent().prev().prev().prev().prev();
		//获取emei内容 this代表当前点击的控件
		//详见:https://www.runoob.com/jquery/jquery-traversing-siblings.html
		//var emeitext=emeielem.text();
		var id=$(this).prev().prev().val();
	    if(confirm("确定删除吗")){  
			$.ajax({
				type : "POST",
				url : "<%=basePath1%>/manage/device/deletelightbox",
				data:{"ID":id
				},
				success : function(data) {
					if(data.data=="true"){
						toastr.success("删除设备成功!");
						location.reload();//刷新界面
					}else{
						toastr.error("删除设备失败!");
					}
				}
			});
	       return true;  
	    }  
	});
	
	$("#body button.btn-info").click(function(){
		//根据class来选择 获取上报历史
		//var emeielem= $(this).parent().prev().prev().prev().prev();
		//获取emei内容 this代表当前点击的控件
		//详见:https://www.runoob.com/jquery/jquery-traversing-siblings.html
		//var emeitext=emeielem.text();
		var ieme=$(this).parent().prev().prev().prev().prev().prev().prev().text();
		$.ajax({
			type : "POST",
			url : "<%=basePath1%>/manage/device/getrephislist",
			data:{"IEME":ieme
			},
			success:function(data) {
	             if(data.data=="true"){
	            	 $("#reporthiscontent").html(data.hiscontent); 
	             }else{
	                 toastr.error("数据库连接错误!");
	             }
	       
			}
		});
	  });
	
		$("#body button.btn-link").click(function(){
			//根据class来获取设备详情
			//var emeielem= $(this).parent().prev().prev().prev().prev();
			//获取emei内容 this代表当前点击的控件
			//详见:https://www.runoob.com/jquery/jquery-traversing-siblings.html
			//var emeitext=emeielem.text();
			var id=$(this).prev().prev().prev().prev().val();
			$.ajax({
				type : "POST",
				url : "<%=basePath1%>/manage/device/getlightbox",
				data:{"ID":id
				},
				success:function(data) {
	                 if(data.data=="true"){
	                	 $("#detaillightboxname").attr("value",data.NAME);
	                	 $("#detaillockid").attr("value",data.LOCKID);                	
	                	 $("#detailspec").attr("value",data.SPEC);
	                	 $("#detailmadetype").attr("value",data.MADETYPE);
	                	 $("#detaillocation").attr("value",data.LOCATION);
	                	 $("#detailpeople").attr("value",data.PEOPLE);                	 
	                	 $("#detailid").attr("value",id);
	                 }else{
	                     toastr.error("数据库连接错误!");
	                 }
	           
				}
			});
		  });
});

function doaddlightbox(){
	//添加函数
	var name=$("#lightboxname").val();//获取id为lightboxname的值
	var lid=$("#lockid").val();//获取id为lockid的值
	var spe=$("#spec").val();//获取id为spec的值
	var type=$("#madetype").val();//获取id为madetype的值
	var loca=$("#location").val();//获取id为location的值
	var peo=$("#people").val();//获取id为people的值
	//根据选择器获取数据
	//参考文档:http://www.w3school.com.cn/jquery/attributes_attr.asp
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/device/addlightbox",
		data:{"NAME":name,
			  "LOCKID":lid,
			  "SPEC":spe,
			  "MADETYPE":type,
			  "LOCATION":loca,
			  "PEOPLE":peo
			  },
		success : function(data) {
			if(data.data=="true"){
				toastr.success("设备添加成功!");
				location.reload();//刷新界面
			}else{
				toastr.error("设备添加失败!");
			}
		}
	});
}

function doeditlightbox(){
	//编辑函数
	var name=$("#editlightboxname").val();//获取id为lightboxname的值
	var lid=$("#editlockid").val();//获取id为lockid的值
	var spe=$("#editspec").val();//获取id为spec的值
	var type=$("#editmadetype").val();//获取id为madetype的值
	var loca=$("#editlocation").val();//获取id为location的值
	var peo=$("#editpeople").val();//获取id为people的值
	var id=$("#editid").val();//获取id
	 
	//根据选择器获取数据
	//参考文档:http://www.w3school.com.cn/jquery/attributes_attr.asp
	$.ajax({
		type : "POST",
		url : "<%=basePath1%>/manage/device/editlightbox",
		data:{"ID":id,
			  "NAME":name,
			  "LOCKID":lid,
			  "SPEC":spe,
			  "MADETYPE":type,
			  "LOCATION":loca,
			  "PEOPLE":peo},
		success : function(data) {
			if(data.data=="true"){
				toastr.success("设备修改成功!");
				location.reload();//刷新界面
			}else{
				toastr.error("设备修改失败!");
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
			<button id="refresh" class="btn btn-default">
				<i class=" fa fa-refresh "></i>更新
			</button>
		</div>
		<div class="panel-body" id="body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th align="center">箱体编号</th>							
							<th align="center">箱体名称</th>
							<th align="center">IMEI编号</th>							
							<th align="center">门状态</th>
							<th align="center">锁状态</th>
							<th align="center">在线状态</th>
							<th align="center">添加时间</th>	
							<th align="center">注册状态</th>						
							<th align="center"></th>					
						</tr>
					</thead>
					<tbody>
						<c:forEach var="light" items="${lightlist}">
							<tr>
								<td align="center">${light.ID}</td>
								<td align="center">${light.NAME}</td>
								<td align="center">${light.IEME}</td>
								<td align="center">${light.DOORSTATUS}</td>
								<td align="center">${light.LOCKSTATUS}</td>
								<td align="center">${light.ISONLINE}</td>
								<td align="center">${light.ADDTIME}</td>	
								
								<c:if test="${light.ISREGIST==1}">
                                 <td align="center">已注册</td>
                                 </c:if>
                                 <c:if test="${light.ISREGIST==0}">
                                 <td align="center"><a>未注册</a></td>
                                 </c:if>
                                 													                                 
                                 <td align="center">
                                    <input type="hidden" name="field＿name" value="${light.ID}"> 
									<button class="btn btn-primary" data-toggle="modal" data-backdrop="static" data-target="#edit">
										<i class="fa fa-edit "></i> 编辑
									</button>
									<button class="btn btn-danger">
										<i class="fa fa-pencil"></i> 删除
									</button>
									<button class="btn btn-info" data-toggle="modal" data-backdrop="static" data-target="#reporthis">上报历史</button>
									<button class="btn btn-link" data-toggle="modal" data-backdrop="static" data-target="#detail">详情</button>
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
                    添加箱体信息
                </h4>
            </div>
            <div class="modal-body">
                    <table class="table table-striped">
                        <tr>
                            <td align="right">
                                箱体名称：
                            </td>
                            <td align="left">
                                <input id="lightboxname" type="text" name="NAME" placeholder=""/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                IMEI编号：
                            </td>
                            <td align="left">
                                <input id="lockid" type="text" name="LOCKID" placeholder=""/>
                            </td>
                        </tr>                          
                         <tr>
                            <td align="right">
                                规格：
                            </td>
                            <td align="left">
                                <input id="spec" type="text" name="SPEC" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                厂家型号：
                            </td>
                            <td align="left">
                                <input id="madetype" type="text" name="MADETYPE" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                安装位置：
                            </td>
                            <td align="left">
                                <input id="location" type="text" name="LOCATION" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                安装人员：
                            </td>
                            <td align="left">
                                <input id="people" type="text" name="PEOPLE" placeholder=""/>
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

<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel2">
                    编辑箱体信息
                </h4>
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
                            <td align="right">
                                <input id="editid" type="hidden" name="field＿name" value=""> 
                                <button id="doedit" class="btn btn-default" data-dismiss="modal">确认编辑</button>
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

<div class="modal fade text-center" id="reporthis" tabindex="-1" role="dialog" aria-labelledby="reporthis" aria-hidden="true">
    <div class="modal-dialog" style="display: inline-block; width: auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel2">
                                                          上报历史
                </h4>
            </div>
            <div class="modal-body">
   				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
						    <th align="center">箱体编号</th>
							<th align="center">设备IEMI编号</th>
							<th align="center">电池电压</th>
							<th align="center">机箱温度</th>
							<th align="center">门状态</th>
							<th align="center">锁状态</th>
							<th align="center">上报时间</th>													
						</tr>
					</thead>
					<tbody id="reporthiscontent">
				</table>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="detail" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel2">
                  详情
                </h4>
            </div>
            <div class="modal-body">
                    <table class="table table-striped">
                        <tr>
                            <td align="right">
                                箱体名称：
                            </td>
                            <td align="left">
                                <input id="detaillightboxname" type="text" readonly  unselectable="on"  name="NAME" placeholder=""/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
           IMEI编号：
                            </td>
                            <td align="left">
                                <input id="detaillockid" type="text" readonly  unselectable="on"  name="LOCKID" placeholder=""/>
                            </td>
                        </tr>                            
                         <tr>
                            <td align="right">
                                规格：
                            </td>
                            <td align="left">
                                <input id="detailspec" type="text" readonly  unselectable="on"  name="SPEC" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                厂家型号：
                            </td>
                            <td align="left">
                                <input id="detailmadetype" type="text" readonly  unselectable="on"  name="MADETYPE" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                安装位置：
                            </td>
                            <td align="left">
                                <input id="detaillocation" type="text" readonly  unselectable="on"  name="LOCATION" placeholder=""/>
                            </td>
                        </tr>
                         <tr>
                            <td align="right">
                                安装人员：
                            </td>
                            <td align="left">
                                <input id="detailpeople" type="text" readonly  unselectable="on"  name="PEOPLE" placeholder=""/>
                            </td>
                        </tr>                                                                                                            
                    </table>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



</rapid:override>

<%@ include file="../home/base.jsp"%>