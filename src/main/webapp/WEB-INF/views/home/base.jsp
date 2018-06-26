<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/GuangJX/css/bootstrap.css" rel="stylesheet" />
    <link href="/GuangJX/css/font-awesome.css" rel="stylesheet" />
    <link href="/GuangJX/css/custom-styles.css" rel="stylesheet" />
    <script src="/GuangJX/js/jquery.min.js"></script>
    <script src="/GuangJX/js/bootstrap.min.js"></script>
    <script src="/GuangJX/js/jquery.metisMenu.js"></script>
    <script src="/GuangJX/js/custom-scripts.js"></script>
    <link href="/GuangJX/css/toastr.css" rel="stylesheet" />
    <script src="/GuangJX/js/toastr.js"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    
    <rapid:block name="title"> 
       <title>Home</title>
    </rapid:block>  
    
</head>

<body>
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

                    <li>
                        <a href="/GuangJX/home/index"><i class="fa fa-qrcode"></i> 首页</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-edit"></i>监控中心<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/GuangJX/monitor/getmap">地图</a>
                            </li>
                            <li>
                                <a href="/GuangJX/control/getcontrolview">数据</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-table"></i>设备管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/GuangJX/device/lightboxlist">光交箱信息管理</a>
                            </li>
                            <li>
                                <a href="/GuangJX/device/statuslist">光交箱状态管理</a>
                            </li>
                            <li>
                                <a href="/GuangJX/device/lockdevicelist">NB-IoT锁管理</a>
                            </li>
                        </ul>
                    </li>
	 
                   <li>
                        <a href="#"><i class="fa fa-sitemap"></i>施工方管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/GuangJX/constructor/constructorlist">施工方管理</a>
                            </li>
                            <li>
                                <a href="/GuangJX/constructor/constructorlist">施工方角色</a>
                            </li>
                            <li>
                                <a href="/GuangJX/constructor/constructorlist">押金管理</a>
                            </li>
                            <li>
                                <a href="/GuangJX/constructor/constructorlist">施工方账号</a>
                            </li>
                            <li>
                                <a href="/GuangJX/constructor/operahistorylist">施工操作历史</a>
                            </li>
                            <li>
                                <a href="/GuangJX/constructor/operahistorylist">安装信息审核</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o"></i>用户中心<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">角色管理</a>
                            </li>
                            <li>
                                <a href="/GuangJX//user/userlist">用户管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-desktop"></i>系统设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">施工方资格审核设置</a>
                            </li>
                            <li>
                                <a href="#">系统参数设置</a>
                            </li>
                        </ul>
                    </li>

                     <li>
                        <a href="#"><i class="fa fa-fw fa-file"></i>修改密码</a>
                    </li>
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
</body>
</html>