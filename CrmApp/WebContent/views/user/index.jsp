<%@page import="com.myclass.constants.UrlConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">USER LIST</h4>
							<!-- HIỂN THỊ THÔNG BÁO -->
			<p style="color: red; text-align:center;">${ message }</p>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="${UrlConstants.ADMIN_USER_ADD}" />" class="btn btn-sm btn-success">ADD NEW USER</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>No.</th>
								<th>First Name</th>
								<th>Email</th>
<!-- 								<th>Hashed Password</th> -->
								<th>Role</th>
								<th>Role Description</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>							
							<c:forEach items = "${ users }" var = "user" varStatus = "loop">
							<tr>
								<td>${ loop.index + 1 }</td>
								<td>${ user.fullname }</td>
								<td>${ user.email }</td>
<%-- 								<td>${ user.password }</td> --%>
								<td>${ user.roleName }</td>
								<td>${ user.roleDescription }</td>
								<td><a href="<c:url value="/admin/user/edit?id=${user.id}" />" id="btn-edit" class="btn btn-sm btn-primary">EDIT</a> <a
									href="<c:url value="/admin/user/delete?id=${user.id}"/>" id="btn-delete" class="btn btn-sm btn-danger">DELETE</a> <a
									href="<c:url value="/admin/user/details?id=${user.id}" />" id="btn-detail" class="btn btn-sm btn-info">DETAILS</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>

<!-- <script>
	$('#btn-edit').on('click', function(){
		 .done(function(data){//Xu ly ket qua tra ve ajax thu hien thanh cong
	            console.log(data);
	            if(data){
	                swal("Cảnh báo!", "Bạn muốn xóa người dùng này?", "warning");
	                //Xoa thong tin da nhap o cac the trong form
	            }
	        })
	})
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 -->