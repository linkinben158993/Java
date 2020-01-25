<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">USER DETAIL</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-4 col-xs-12">
			<div class="white-box">
				<div class="user-bg">
					<img width="100%" alt="user" src="<c:url value="/assets/plugins/images/large/img1.jpg" />">
					<div class="overlay-box">
						<div class="user-content">
							<a href="javascript:void(0)"><img
								src="<c:url value="/assets/plugins/images/users/genu.jpg"/>" class="thumb-lg img-circle"
								alt="img"></a>
							<h4 class="text-white">User Name</h4>
							<h5 class="text-white">info@myadmin.com</h5>
						</div>
					</div>
				</div>
				<div class="user-btm-box">
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-purple">
							<i class="ti-facebook"></i>
						</p>
						<h4>20%</h4>
						<h6>UNACCOMPLISHED</h6>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-blue">
							<i class="ti-twitter"></i>
						</p>
						<h4>50%</h4>
						<h6>IN PROGRESS</h6>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-danger">
							<i class="ti-dribbble"></i>
						</p>
						<h4>30%</h4>
						<h6>ACCOMPLISHED</h6>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form class="form-horizontal form-material">
 					<div class="form-group">
					
 					<label class="col-md-12">Full Name: ${user.fullname}</label>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Email: ${user.email}</label>
					</div>
					<div class="form-group">
						<label class="col-md-12">Role Description: ${user.roleDescription}</label>
					</div>
					<div class="form-group">
						<label class="col-md-12">${user.avatar}</label>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Country</label>
					</div>
					<div>
						<a href="javascript:void(0)" class="dropdown-toggle profile-pic" data-toggle="dropdown">
						  	<img src='<c:url value="/assets/plugins/images/users/varun.jpg"/>' alt="user-img"
                                width="36" class="img-circle">
                                <b class="hidden-xs">
						  			USER INFO
						  		</b>
						  	<span class="caret"></span>
						</a>
					
						<ul class="dropdown-menu">
							<li><a href="<c:url value ="/admin/user/edit?id=${ user.id }"/>">EDIT USER INFO</a></li>
							<li><a href="<c:url value ="${UrlConstants.LOGOUT}"/>">LOGOUT</a></li>
						</ul>					
					</div>
				</form>
			</div>
		</div>
	</div>
	<br />
                <!-- /.row -->
                <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
                <h4>DANH SÁCH CÔNG VIỆC</h4>
                <div class="row">
                    <div class="col-md-4">
                      <div class="white-box">
                       <h3 class="box-title">Chưa Thực Hiện</h3>
                    	<c:forEach items="${ user_jobs }" var="userJob">
                    	<c:choose>
	                    	<c:when test="${userJob.statusId == 1 }">                        
	                            <div class="message-center">
	                                <a href="<c:url value = "#"/>"></a>
	                                    <div class="mail-contnet">
	                                        <h5>Full Name: ${ userJob.fullname }</h5> 
	                                        <h5>Contact Email: ${ userJob.email }</h5> 
	                                        <span class="mail-desc">${ userJob.jobName }</span> 
	                                        <span class="date">From: ${ userJob.startDate }</span> <br>
	                                        <span class="date">To: ${ userJob.endDate }</span>
	                                    </div>
	                            </div>
	                        </c:when>
	                        <c:otherwise>
	                            <div class="message-center">
	                                <a href="<c:url value = "#"/>"></a>
	                                    <div class="mail-contnet">
											<h3>
												NOT AVAILABLE
											</h3>
										</div>
	                            </div>                        	
	                        </c:otherwise>
                        </c:choose>
                        </c:forEach>
                       </div> 
                    </div>
                    <div class="col-md-4">
                     <div class="white-box">
	                   <h3 class="box-title">Đang Thực Hiện</h3>
                    	<c:forEach items="${ user_jobs }" var="userJob">
                    	<c:choose>
	                    	<c:when test="${userJob.statusId == 2 }">
		                            <div class="message-center">
		                                <a href="<c:url value = "#"/>"></a>
		                                    <div class="mail-contnet">
		                                        <h5>Full Name: ${ userJob.fullname }</h5> 
		                                        <h5>Contact Email: ${ userJob.email }</h5> 
		                                        <span class="mail-desc">${ userJob.jobName }</span> 
		                                        <span class="date">From: ${ userJob.startDate }</span> <br>
		                                        <span class="date">To: ${ userJob.endDate }</span>
		                                    </div>
		                            </div>
	                        </c:when>
	                        <c:otherwise>
	                            <div class="message-center">
	                                <a href="<c:url value = "#"/>"></a>
	                                    <div class="mail-contnet">
											<h3>
												NOT AVAILABLE
											</h3>
	                                    </div>
	                            </div>                        	
	                        </c:otherwise>
                        </c:choose>
                        </c:forEach>
                     </div>
                    </div>
                    <div class="col-md-4">
                   	 <div class="white-box">
	                   <h3 class="box-title">Đã Hoàn Thành</h3>
                    	<c:forEach items="${ user_jobs }" var="userJob">
                    	<c:choose>
	                    	<c:when test="${userJob.statusId == 3 }">	
		                            <div class="message-center">
		                                <a href="<c:url value = "#"/>"></a>
		                                    <div class="mail-contnet">
		                                        <h5>Full Name: ${ userJob.fullname }</h5> 
		                                        <h5>Contact Email: ${ userJob.email }</h5> 
		                                        <span class="mail-desc">${ userJob.jobName }</span> 
		                                        <span class="date">From: ${ userJob.startDate }</span> <br>
		                                        <span class="date">To: ${ userJob.endDate }</span>
		                                    </div>
		                            </div>
		                    </c:when>
	                        <c:otherwise>
	                            <div class="message-center">
	                                <a href="<c:url value = "#"/>"></a>
	                                    <div class="mail-contnet">
											<h3>
												NOT AVAILABLE
											</h3>
										</div>
	                            </div>                        	
	                        </c:otherwise>
                        </c:choose>	                    
                        </c:forEach>
                     </div>
                    </div>
                </div>           
	<!-- END DANH SÁCH CÔNG VIỆC -->
</div>