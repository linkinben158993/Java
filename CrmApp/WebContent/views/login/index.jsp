<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="bg-img" 
style="background:url('<c:url value="/assets/plugins/images/blackwoods.jpg" />') center center no-repeat !important">
  <form action='<c:url value="/login"/>' class="container" method="post">
  	<!-- HIỂN THỊ LỖI -->
	<p style="color: red; text-align:center;">${ message }</p>
    <h1>Login</h1>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <button type="submit" class="btn_login">Login</button>
    <button type="button" class="btn_regis">New Here? Register</button>
  </form>
</div>
