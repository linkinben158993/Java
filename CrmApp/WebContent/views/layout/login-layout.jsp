<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><dec:title/></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href= "<c:url value = "https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>">
  <link href="<c:url value="/assets/css/login.css"/>" rel="stylesheet"/> 
<%--   <link type="image/jpg" sizes="full" href="<c:url value="/assets/plugins/images/large/img1.jpg"/>"> --%>
  <style>
		body, html {
		  height: 100%;
		  font-family: Arial, Helvetica, sans-serif;
		}
		* {
		  box-sizing: border-box;
		}
		
		.bg-img {
		  /* The image used */
/* 		  background-image: url("../../plugins/images/large/img1.jpg"); */
		
		  min-height: 380px;
		
		  /* Center and scale the image nicely */
		  background-position: center;
		  background-repeat: no-repeat;
		  background-size: cover;
		  position: relative;
		}
		
		/* Add styles to the form container */
		.container {
		  position: absolute;
		  right: 0;
		  margin: 20px;
		  max-width: 300px;
		  padding: 16px;
		  background-color: white;
		}
		
		/* Full-width input fields */
		input[type=text], input[type=password] {
		  width: 100%;
		  padding: 15px;
		  margin: 5px 0 22px 0;
		  border: none;
		  background: #f1f1f1;
		}
		
		input[type=text]:focus, input[type=password]:focus {
		  background-color: #ddd;
		  outline: none;
		}
		
		/* Set a style for the submit button */
		.btn_login {
		  background-color: #0e099e;
		  color: white;
		  padding: 16px 20px;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		  opacity: 0.9;
		}
		
		.btn_regis{
		  background-color: #b03005;
		  color: white;
		  padding: 16px 20px;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		  opacity: 0.9;
		}
		
		.btn:hover {
		  opacity: 1;
		}
  </style>
  <dec:head/>
</head>
<body>
	<dec:body />
	<!-- Scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
