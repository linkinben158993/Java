<%@page import="com.myclass.constants.UrlConstants"%>
<%@page import="java.awt.event.ItemEvent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    

            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">JOB LIST</h4>
                       	<!-- HIỂN THỊ LỖI -->
						<p style="color: red; text-align:center;">${ message }</p>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <a href="<c:url value="${UrlConstants.MANAGER_JOB_ADD}" />" class="btn btn-sm btn-success">ADD NEW</a>
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
                                            <th>JOB NAME</th>
                                            <th>START DATE</th>
                                            <th>END DATE</th>
                                            <th>ACTION</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items = "${jobs}" var = "job" varStatus="loop">
                                       	<tr>
                                            <td>${ loop.index + 1 }</td>
                                            <td>${ job.name }</td>
                                            <td>
                                            	<p>
                                            	<fmt:formatDate pattern = "dd/MM/yyyy" value = "${job.startDate}" />
                                            	</p>
                                            </td>
                                            <td>
                                                <p>
                                            	<fmt:formatDate pattern = "dd/MM/yyyy" value = "${job.endDate}" />
                                            	</p>
                                            </td>
                                            <td>
                                                <a href='<c:url value= "/manager/job/edit?id=${ job.id }"/>' class="btn btn-sm btn-primary">EDIT</a>
                                                <a href='<c:url value= "/manager/job/delete?id=${ job.id }"/>' class="btn btn-sm btn-danger">DELETE</a>
                                                <a href="<c:url value = "/manager/job/details"/>" class="btn btn-sm btn-info">DETAILS</a>
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
            <!-- /.container-fluid -->
