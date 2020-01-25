<%@page import="com.myclass.constants.UrlConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật thành viên</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<p style="color: red; text-align:center;">${ message }</p>
				
				<form class="form-horizontal form-material" action="<c:url value="/admin/user/edit"/>" method="post">
					
					<input type="hidden" name="id" value="${ user.id }"/>
				
					<div class="form-group">
						<label class="col-md-12"> FULL NAME </label>
						<div class="col-md-12">
							<input type="text" value="${ user.fullname }" name = "fullname"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">EMAIL</label>
						<div class="col-md-12">
							<input type="email" value="${ user.email }" name = "email"
								class="form-control form-control-line"
								id="example-email">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">PASSWORD</label>
						<div class="col-md-12">
							<input type="password" value="${ user.password }" name = "password"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Avatar</label>
 						<div class="col-md-12">
	 						<input type="file" placeholder="Avatar" name = "avatar"
								class="form-control form-control-line">
						</div>
					</div>
 					<div class="form-group">
						
						<label class="col-sm-12">Select Role</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name = "roleId">
							<c:forEach items = "${roles}" var = "item">	
								<option value = "${item.id}">"${item.name}"</option>
							</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Update User</button>
							<a href="<c:url value="${UrlConstants.USER}" />" class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>