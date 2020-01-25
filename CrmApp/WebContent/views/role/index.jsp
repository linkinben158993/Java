<%@page import="java.awt.event.ItemEvent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Role List</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="/admin/role/add" />" class="btn btn-sm btn-success">Add New Role</a>
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
								<th>Role Name</th>
								<th>Role Description</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roles}" var="item" varStatus="loop">
								<tr>
									<td>${ loop.index + 1 }</td>
									<td>${ item.name }</td>
									<td>${ item.description }</td>
									<td>
										<a href="<c:url value="/admin/role/edit?id=${ item.id }" />" class="btn btn-sm btn-primary">EDIT</a> 
										<a href="<c:url value= "/admin/role/delete?id=${item.id}" />" class="btn btn-sm btn-danger">DELETE</a>
									</td>
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