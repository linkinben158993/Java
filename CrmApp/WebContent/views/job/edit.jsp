<%@page import="com.myclass.constants.UrlConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật công việc</h4>
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
			
				<form class="form-horizontal form-material" action="<c:url value="/manager/job/edit"/>" method="post">
					
					<input type="hidden" name="id" value="${ job.id }"/>
				
					<div class="form-group">
						<label class="col-md-12">JOB NAME</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên công việc" name="name" value="${job.name}"
								class="form-control form-control-line" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-12">START DATE</label>
						<div class="col-md-12">
							<input type="date" placeholder="Ngày bắt đầu" name="start_date" value="${job.startDate}"
								class="form-control form-control-line" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-12">END DATE</label>
						<div class="col-md-12">
							<input type="date" placeholder="Ngày kết thúc" name="end_date" value="${job.endDate}"
								class="form-control form-control-line" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-12">EXECUTOR ID</label>
						<div class="col-md-12">
							<input type="text" placeholder="Người thực hiện" name="user_id" value=""
								class="form-control form-control-line" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-12">PROGRESS ID</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tiến độ" name="status_id" value=""
								class="form-control form-control-line" />
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Edit Job</button>
							<a href="<c:url value="${UrlConstants.USER_JOB_LIST}" />" class="btn btn-primary">BACK</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>