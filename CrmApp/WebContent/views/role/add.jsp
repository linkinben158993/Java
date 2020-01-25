<%@page import="com.myclass.constants.UrlConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Add New Role</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
			
				<!-- HIỂN THỊ LỖI -->
				<p style="color: red; text-align:center;">${ message }</p>
			
				<form class="form-horizontal form-material" action= '<c:url value="/admin/role/add"/>' method="post">
					<div class="form-group">
						<label class="col-md-12">ROLE NAME</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên quyền" name="name"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">ROLE DESCRIPTION</label>
						<div class="col-md-12">
							<input type="text" placeholder="Mô tả" name="description"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">ADD ROLE</button>
							<a href="<c:url value="${UrlConstants.USER_ROLE_LIST}" />" class="btn btn-primary"> BACK </a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>