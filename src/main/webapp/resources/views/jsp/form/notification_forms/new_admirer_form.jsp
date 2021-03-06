<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<div align="left" id="block">
	<table>
		<tr>
			<th rowspan="2"><c:choose>
					<c:when test="${not empty notification.sender.profilePhoto}">
						<img
							src="<c:out value="/resources/selfshots/${notification.sender.login}/${notification.sender.profilePhoto.title}" />"
							width="50" height="50" />
					</c:when>
					<c:otherwise>
						<img
							src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
							width="50" height="50" alt="" />
					</c:otherwise>
				</c:choose></th>
			<th><a href="/${notification.sender.login}"><c:out
						value="${notification.sender.name} ${notification.sender.lastname}" /></a></th>
		</tr>
		<tr>
			<td><spring:message code="notification.subscribed" /> <br>
			</td>
			<td><c:choose>
					<c:when test="${!notification.review}">
						<%@include file="set_review_form.jsp"%>
					</c:when>
				</c:choose></td>
		</tr>
	</table>
</div>