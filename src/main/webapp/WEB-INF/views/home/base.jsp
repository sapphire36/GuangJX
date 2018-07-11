<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
     <!--设置图标 -->
    <link rel="shortcut icon" href="/GuangJX/img/logo.png" type="image/x-icon" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/GuangJX/css/bootstrap.css" rel="stylesheet" />
    <link href="/GuangJX/css/font-awesome.css" rel="stylesheet" />
    <link href="/GuangJX/css/custom-styles.css" rel="stylesheet" />
    <script src="/GuangJX/js/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="/GuangJX/js/bootstrap.min.js"></script>
    <script src="/GuangJX/js/jquery.metisMenu.js"></script>
    <script src="/GuangJX/js/custom-scripts.js"></script>
    <link href="/GuangJX/css/toastr.css" rel="stylesheet" />
    <script src="/GuangJX/js/toastr.js"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="/GuangJX/js/bootstrap.min.js"></script>
    
    <rapid:block name="title"> 
       <title>Home</title>
    </rapid:block>  
</head>

<body>
    <%  boolean isbegin = true ;%>
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><i class="fa fa-comments"></i> <strong>光交箱管理系统 </strong></a>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <!--通知消息 -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> 测试消息1
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 测试消息2
                                    <span class="pull-right text-muted small">12 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> 测试消息3
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>显示所有消息</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i>用户信息</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!--菜单 -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
	 			 <c:forEach var="item" items="${list}">
		            <c:if test="${item.ISLEAF == 0}">
		             <!--如果是不是叶子节点 -->
						<c:if test="${item.PARRENTCODE == 0}">
					        <c:set var="flag" value="<%= isbegin %>" scope="page"/>
					        <c:if test="${flag == false}">
						         </ul>
						         </li>
       				             <% isbegin = true ;%>
					        </c:if>
					        <li>
                              <a href="${item.URL}"><i class="fa ${item.IMAGE}"></i>${item.NAME}</a>
                            </li>
						</c:if>
						<c:if test="${item.PARRENTCODE != 0}">
					        <c:set var="flag" value="<%= isbegin %>" scope="page"/>
					        <c:if test="${flag == true}">
							     <li>
								 <a href="#"><i class="fa ${item.IMAGE}"></i>${item.NAME}<span class="fa arrow"></span></a>
								 <ul class="nav nav-second-level">
					        </c:if>
					        <c:if test="${flag == false}">
							      </ul>
							      </li>
							      <li>
								  <a href="#"><i class="fa ${item.IMAGE}"></i>${item.NAME}<span class="fa arrow"></span></a>
								  <ul class="nav nav-second-level">
								  <% isbegin = true ;%>
					        </c:if>
						</c:if>
                    </c:if>
                    <c:if test="${item.ISLEAF == 1}">
                    <!--如果是叶子节点 -->
                        <li>
                            <a href="${item.URL}">${item.NAME}</a>
                         </li>
                        <% isbegin = false ;%>
                    </c:if>
				 </c:forEach>
               </ul>
            </div>
        </nav>
        <div id="page-wrapper">
            <div id="page-inner">
                 <rapid:block name="content">
                 </rapid:block>  
                 <rapid:block name="footer">
                     <footer></footer>
                 </rapid:block>  
             </div>
        </div>
    </div>
    



 <div class="modal fade" id="warnnintModel">
            <div class="modal-dialog">  
              <div class="modal-content">  
                <div class="modal-header"> 报警信息 </div>  
                  <div class="modal-body">  
                       
                       <a href="https://www.baidu.com">查看报警
                       </a>
              
                  </div>  
                <div class="modal-footer">  
                  <button class="btn btn-success" type="submit" data-dismiss="modal">Save</button>  
                  <button class="btn btn-warning" type="reset" data-dismiss="modal">Reset</button>  
                  <button class="btn btn-danger" data-dismiss="modal">Cancel</button>  
                </div>  
              </div>  
            </div>  
</div>  

<script type="text/javascript">
$(document).ready(function(){		
	getwainning();
 
});
function getwainning(){
	//获取报警信息
	$.ajax({
		type : "POST",
		url : "<%=basePath%>/manage/home/getwainning",
		data :"test",
		success : function(data) {
			$("#warnnintModel").modal("show");
			if(data.data=="true"){
				if(data.IsFlush=="true"){
					
					toastr.error(data.content);
				}
			}else{
				
				toastr.error("获取报警信息异常!");
			}
		}
	});
	setTimeout(getwainning,20000);
}
</script>
</body>
</html>