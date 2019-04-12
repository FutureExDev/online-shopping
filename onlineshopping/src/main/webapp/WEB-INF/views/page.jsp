<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/resources/css" var="css" />
<spring:url value="/resources/js" var="js" />
<spring:url value="/resources/images" var="images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping -${title}</title>

<script>
	window.menu = '${title}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet" />
<link href="${css}/bootstrap.flaty.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">
<script src="${js}/jquery.js"></script>
<script src="${js}/bootstrap.min.js"></script>
<script src="${js}/myapp.js"></script>
</head>

<body>


	<div class="wrapper">

		<!-- Navigation -->
		<%@ include file="./shared/navbar.jsp"%>
		<!-- Page Content -->

		<div class="content">

			<c:if test="${userClickHome == true}">
				<%@ include file="home.jsp"%>
			</c:if>

			<c:if test="${userClickAbout == true}">
				<%@ include file="about.jsp"%>
			</c:if>

			<c:if test="${userClickContact == true}">
				<%@ include file="contact.jsp"%>
			</c:if>

			<c:if test="${userClickAllProducts == true}">
				<%@ include file="allproducts.jsp"%>
			</c:if>

		</div>
		<!-- Footer -->
		<%@ include file="./shared/footer.jsp"%>
		<!-- Bootstrap core JavaScript -->

	</div>
</body>

</html>
