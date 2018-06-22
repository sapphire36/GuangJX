<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<rapid:override name="title">
	<title>业务控制</title>
</rapid:override>
<rapid:override name="content">
	<div class="row">
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">请求开箱队列</div>
				<div class="panel-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
						lacus adipiscing, posuere lectus et, fringilla augue.</p>
				</div>
				<div class="panel-footer">Panel Footer</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-primary">
				<div class="panel-heading">请求关箱队列</div>
				<div class="panel-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
						lacus adipiscing, posuere lectus et, fringilla augue.</p>
				</div>
				<div class="panel-footer">Panel Footer</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-4">
			<div class="panel panel-success">
				<div class="panel-heading">待评价队列</div>
				<div class="panel-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
						lacus adipiscing, posuere lectus et, fringilla augue.</p>
				</div>
				<div class="panel-footer">Panel Footer</div>
			</div>
		</div>
	</div>
</rapid:override>
<%@ include file="../home/base.jsp"%>
