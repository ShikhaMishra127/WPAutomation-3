<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>OCI Inbound Testing</title>
	</head>
	<body>
		<table width="100%" style="font-family:arial; border-bottom:1px solid black; border-top:1px solid black;">
			<c:forEach items="${paramValues}" var="parameter">
				<tr>
					<td width="20%" style="font-size:8pt;">
						<c:out value="${parameter.key}"/>
					</td>
					<td>
						<c:forEach items="${parameter.value}" var="parameterValue" varStatus="status">
							<c:out value="${parameterValue}"/>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>