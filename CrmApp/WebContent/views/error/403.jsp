<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Preloader -->
<div class="preloader">
	<div class="cssload-speeding-wheel"></div>
</div>
<section id="wrapper" class="error-page">
	<%-- 		style="background:url('<c:url value="/assets/plugins/images/error-bg.jpg" />') center center no-repeat !important" --%>
	<div class="error-box"
		style="background:url('<c:url value="/assets/plugins/images/error-bg.jpg" />') center center no-repeat !important">
		<div class="error-body text-center">
			<h1>404</h1>
			<h3 class="text-uppercase">YOU CAN'T ACCESS THIS SECTION!</h3>
			<p class="text-muted m-t-30 m-b-30">YOU SEEM TO BE TRYING TO FIND
				YOUR WAY HOME</p>
			<a href="<c:url value = "/home"/>"
				class="btn btn-info btn-rounded waves-effect waves-light m-b-40">RETURN
				TO HOME PAGE.</a>
		</div>
		<footer class="footer text-center">2018 © Pixel Admin.</footer>
	</div>
</section>
<%--     <!-- jQuery -->
    
    <script src="<c:url value="/assets/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/assets/bootstrap/dist/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript">
    $(function() {
        $(".preloader").fadeOut();
    });
    </script> --%>
