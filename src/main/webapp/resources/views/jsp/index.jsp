<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div align="center">
		<!-- Head -->
		<%@include file="form/head.jsp"%>
		<!-- end head -->

		<table width="80%">
			<tr>
				<td width="20%" valign="top">
					<!-- following --> <%@include file="form/following.jsp"%>
					<!-- end following -->
				</td>
				<td width="60%" valign="top">
					<!-- Posts -->
					<div align="left" id="block">
						<c:choose>
							<c:when test="${not empty posts}">
								<c:forEach var="post" items="${posts}">
									<c:choose>
										<c:when test="${post.action eq 'SUBSCRIBE'}">
											<%@include file="form/post_forms/subscribe_to.jsp"%>
										</c:when>
										<c:when test="${post.action eq 'UPLOAD_PHOTO'}">
											<%@include file="form/post_forms/upload_photo.jsp"%>
										</c:when>
									</c:choose>
									<br />
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div align="center">
									<spring:message code="lable.empty" />
								</div>
							</c:otherwise>
						</c:choose>
						<!--  -->
					</div>
				</td>
				<td width="20%" valign="top"><div align="center" id="block">
						<c:out value="something" />
					</div></td>
			</tr>
		</table>




	</div>
</body>
</html>