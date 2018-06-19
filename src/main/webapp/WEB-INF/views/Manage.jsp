
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="/GuangJX/css/manage/window.css">
<script src="/GuangJX/js/manage/adapter.js"></script>
<!--rem适配js-->
<link rel="stylesheet" href="/GuangJX/css/manage/base.css">
<!--初始化文件-->
<link rel="stylesheet" href="/GuangJX/css/manage/menu.css">
<!-- 开箱队列css -->
<link rel="stylesheet" href="/GuangJX/css/manage/XT.css">

<!--主样式-->
<script src="/js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="/js/demo.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=IDvNBsejl9oqMbPF316iKsXR"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
</head>
<body>
	<div id="header">
		<h1>欢迎进入光交箱管控平台</h1>
	</div>

	<div id="nav">
		<div id="menu">

			<!--显示菜单-->
			<div id="open">

				<div class="navBox">
					<ul>
						<li>
							<h2 class="obtain">
								首页<i></i>
							</h2>
						</li>
						<li>
							<h2 class="obtain">
								监控中心<i></i>
							</h2>
							<div class="secondary">
								<h3>
									<a style='color: white' href="/LightBox/views/Map.jsp">
										地图显示 </a>
								</h3>
							</div>
						</li>
						<li>
							<h2 class="obtain">
								设备管理<i></i>
							</h2>
							<div class="secondary">
								<h3>
									<a style='color: white'
										href="/LightBox/views/XTinfo/CXTinfo.jsp"> 箱体信息 </a>
								</h3>
								<h3>
									<a style='color: white'
										href="/LightBox/views/XTcommunicate/CXTcommunicate.jsp">
										箱体通讯 </a>
								</h3>
								<h3>
									<a style='color: white' href="/LightBox/views/NewXT/CNewXT.jsp">
										新建设备 </a>
								</h3>
							</div>
						</li>
						<li>
							<h2 class="obtain">
								施工方管理<i></i>
							</h2>
							<div class="secondary">
								<h3>
									<a style='color: white'
										href="/LightBox/views/Secondinfo/Csecondinfo.jsp"> 施工方基本信息
									</a>
								</h3>
								<h3>
									<a style='color: white'
										href="/LightBox/views/OperateHistory/Coperatehis.jsp">
										操作历史 </a>
								</h3>
							</div>
						</li>
						<li>
							<h2 class="obtain">
								用户中心<i></i>
							</h2>
							<div class="secondary">
								<h3>
									<a style='color: white'
										href="/LightBox/views/Firstinfo/CFirstinfo.jsp"> 员工信息 </a>
								</h3>
								<h3>
									<a style='color: white' href="/LightBox/views/Duty/CDuty.jsp">
										值班记录 </a>
								</h3>
							</div>
						</li>
						<li>
							<h2 class="obtain">
								远程开锁<i></i>
							</h2>
						</li>
						<li>
							<h2 class="obtain">
								押金管理<i></i>
							</h2>
							<div class="secondary">
								<h3>
									<a style='color: white' href="/LightBox/views/Cash/CCash.jsp">
										押金表 </a>
								</h3>
							</div>
						</li>
						<li>
							<h2 class="obtain">
								手机端<i></i>
							</h2>
						</li>
					</ul>
				</div>
			</div>
		</div>


		<script src="../js/manage/menu.js"></script>
		<!--控制js-->

	</div>




	<div id="section">
		<div>
			<div style="min-height: 400px; width: 101%; margin: 0px" id="map"></div>
			<script type="text/javascript">
				var markerArr = [ {
					title : "名称：小寨",
					point : "108.953196,34.229055",
					address : "陕西省西安市小寨",
					tel : "029"
				}, {
					title : "名称：西安建筑科技大学",
					point : "108.972648,34.245344",
					address : "陕西省西安建筑科技大学",
					tel : "029"
				}, {
					title : "名称：西安科技大学研究生院",
					point : "108.969414,34.240478",
					address : "陕西省西安科技大学研究生院",
					tel : "029"
				}, {
					title : "名称：省图书馆",
					point : "108.951994,34.23783",
					address : "陕西省图书馆",
					tel : "029"
				} ];
				function map_init() {
					var map = new BMap.Map("map"); // 创建Map实例
					var point = new BMap.Point(108.953196, 34.229055); //地图中心点，西安市
					map.centerAndZoom(point, 14); // 初始化地图,设置中心点坐标和地图级别。
					map.enableScrollWheelZoom(true); //启用滚轮放大缩小
					// 添加带有定位的导航控件
					var navigationControl = new BMap.NavigationControl({
						// 靠左上角位置
						anchor : BMAP_ANCHOR_TOP_LEFT,
						// LARGE类型
						type : BMAP_NAVIGATION_CONTROL_LARGE,
						// 启用显示定位
						enableGeolocation : true
					});
					map.addControl(navigationControl);
					// 添加定位控件
					var geolocationControl = new BMap.GeolocationControl();
					geolocationControl.addEventListener("locationSuccess",
							function(e) {
								// 定位成功事件
								var address = '';
								address += e.addressComponent.province;
								address += e.addressComponent.city;
								address += e.addressComponent.district;
								address += e.addressComponent.street;
								address += e.addressComponent.streetNumber;
								alert("当前定位地址为：" + address);
							});
					geolocationControl.addEventListener("locationError",
							function(e) {
								// 定位失败事件
								alert(e.message);
							});

					map.addControl(geolocationControl);

					var point = new Array(); //存放标注点经纬信息的数组
					var marker = new Array(); //存放标注点对象的数组
					var info = new Array(); //存放提示信息窗口对象的数组
					var searchInfoWindow = new Array();//存放检索信息窗口对象的数组
					for (var i = 0; i < markerArr.length; i++) {
						var p0 = markerArr[i].point.split(",")[0]; //
						var p1 = markerArr[i].point.split(",")[1]; //按照原数组的point格式将地图点坐标的经纬度分别提出来
						point[i] = new window.BMap.Point(p0, p1); //循环生成新的地图点
						marker[i] = new window.BMap.Marker(point[i]); //按照地图点坐标生成标记
						map.addOverlay(marker[i]);
						//marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						//显示marker的title，marker多的话可以注释掉
						var label = new window.BMap.Label(markerArr[i].title, {
							offset : new window.BMap.Size(20, -10)
						});
						marker[i].setLabel(label);
						// 创建信息窗口对象
						info[i] = "<p style=’font-size:12px;lineheight:1.8em;’>"
								+ "</br>地址："
								+ markerArr[i].address
								+ "</br> 电话：" + markerArr[i].tel + "</br></p>";
						//创建百度样式检索信息窗口对象                       
						searchInfoWindow[i] = new BMapLib.SearchInfoWindow(map,
								info[i], {
									title : markerArr[i].title, //标题
									width : 290, //宽度
									height : 55, //高度
									panel : "panel", //检索结果面板
									enableAutoPan : true, //自动平移
									searchTypes : [ BMAPLIB_TAB_TO_HERE, //到这里去
									BMAPLIB_TAB_FROM_HERE //从这里出发
									]
								});
						//添加点击事件
						marker[i].addEventListener("click", (function(k) {
							// js 闭包
							return function() {
								//将被点击marker置为中心
								//map.centerAndZoom(point[k], 18);
								//在marker上打开检索信息窗口
								searchInfoWindow[k].open(marker[k]);
							}
						})(i));
					}
				}
				//异步调用百度js
				function map_load() {
					var load = document.createElement("script");
					load.src = "http://api.map.baidu.com/api?v=2.0&ak=IDvNBsejl9oqMbPF316iKsXR&callback=map_init";
					document.body.appendChild(load);
				}
				//window.onload = map_load;
			</script>
		</div>


		<div id="openXT" class="city">

			<!--弹出窗口，默认其不显示，即display:none-->

	<div id="xtpop_frame" style="display: none">
		<!--弹出窗的头部-->
		<div class="xtframe_top">
			<span class="tip" ><font color="white">XXX箱体</font></span>
			<div class="close" onClick="close_pop()">
				<span><font color="white">关闭</font></span>
			</div>
		</div>
		<!--弹出框内容区-->
		<div class="xtframe_body">
			<table class="xttable" width="100%" >
			<tr class="hang">
				<td >电池电压</td>
				<td ><input type="text" ></td>
			</tr>
			<tr class="hang">
				<td >CPU温度</td>
				<td ><input type="text" ></td>
			</tr>
			<tr class="hang">
				<td >箱门状态</td>
				<td ><input type="text" ></td>
			</tr>
			<tr class="hang">
				<td >施工单位名称</td>
				<td ><input type="text"></td>
			</tr>
		</table>
		</div>


		<!--弹出框底部用来放置按钮-->
		<div class="xtbottom">
			<input type="button" class="xtbutton_left"  value="开">
			<input type="button" class="xtbutton_right"  value="关">
		</div>

	</div>

	<!--父窗口，添加了文字和按钮-->

	<div id="dialog">
		
		<input type="button" class="xtbutton" onClick="showDialog()"
			value="箱体">

	</div>



	<!--script代码-->

	<script type="text/javascript">

	function showDialog(){
	var dialog = document.getElementById("xtpop_frame").style.display;
	if(dialog == 'none'){
	document.getElementById("xtpop_frame").style.display = 'block';
	
	
	var middiag = document.getElementById("mid");
	middiag.style.display = "block";
	middiag.style.background = "rgba(128,128,128,0.3)";
	}
	if(dialog == "block"){
	document.getElementById("xtpop_frame").style.display = 'none';
	}
	}
	
	
	//关闭小窗口，使中间的那层页面和小窗口隐藏，父页面呈现
	function close_pop(){
	var main = document.getElementById("xtpop_frame");
	main.style.display = "none";
	document.getElementById("mid").style.display = "none";
	}
	
	</script>

		</div>
		
		
		<div id="inXT" class="city">
		
		<button onClick="myFunction()">点我</button>
		<p id="demo"></p>
		<script>
		function myFunction(){
			var x;
			var person=prompt("请输入你的名字","Harry Potter");
			if (person!=null && person!=""){
			    //x="你好 " + person + "! 今天感觉如何?";
			    document.getElementById("demo").innerHTML=x;
			}
		}
		</script>
		
		</div>
		
		<div id="closeXT" class="city"  style="overflow-x: auto; overflow-y: auto; height: 105px; width:322px;">
		
		<script LANGUAGE="JavaScript">
			function openwin() {
				window.open(
								"XT.jsp",
								"newwindow",
								"height=300, width=500, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		
				//写成一行  
		
			}
		</script>
		
		<input type="text" value="关箱队列">
		
  
 </table>
	
	<table width="300px" heigit="200px" cellspacing="0" cellpadding="0" border="1px" class="tong">
			<tbody>
			<tr>
				<td align="center" id="bortop1">
					<input type="button" onclick="openwin()" >
				</td>
			</tbody>
			
		</table>
		
		</div>
	</div>
	

</body>

</html>