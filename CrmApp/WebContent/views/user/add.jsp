<%@page import="com.myclass.constants.UrlConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">ADD NEW USER</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
<!-- 			enctype="multipart/form-data" sẽ ảnh hưởng qua bên controller-->
				<form class="form-horizontal form-material" action = '<c:url value="/admin/user/add"/>' method="POST">
					<div class="form-group">
						<label class="col-md-12">Full Name</label>
						<div class="col-md-12">
							<input type="text" placeholder="Johnathan Doe" name = "fullname"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Email</label>
						<div class="col-md-12">
							<input type="email" placeholder="johnathan@admin.com"
								class="form-control form-control-line" name="email"
								id="example-email">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Password</label>
						<div class="col-md-12">
							<input type="password" value="password" name = "password"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Phone No</label>
						<div class="col-md-12">
							<input type="text" placeholder="123 456 7890" name = "phonenumber"
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
						<label class="col-sm-12">USER ROLE</label>
						<div class="col-sm-12">
						
							<select class="form-control form-control-line" name = "roleId">
							<c:forEach items = "${roles}" var = "item" >
								<option value ="${item.id}">"${ item.name }"</option>
								<%-- <option value ="${item.id}">"${ item.description }"</option> --%>							
							</c:forEach>
							</select>

						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Add User</button>
							<a href="<c:url value="${UrlConstants.USER}" />" class="btn btn-primary">Back</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>