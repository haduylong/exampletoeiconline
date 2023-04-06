<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<c:if test="${not empty item.pojo.userId }">
					<h4 class="modal-title">
						<fmt:message key="label.user.edit" bundle="${lang }" />
					</h4>
				</c:if>

				<c:if test="${empty item.pojo.userId }">
					<h4 class="modal-title">
						<fmt:message key="label.user.add" bundle="${lang }" />
					</h4>
				</c:if>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="md-form">
							<input type="text"
								placeholder="<fmt:message key='label.user.name' bundle='${lang}'/>"
								class="form-control" value="${item.pojo.name}" id="username" />
						</div>
					</div>
					<br /> <br />
					<div class="col-md-12">
						<div class="md-form">
							<input type="text"
								placeholder="<fmt:message key='label.user.fullname' bundle='${lang}'/>"
								class="form-control" value="${item.pojo.fullName}" />
						</div>
					</div>
					<br /> <br />
					<div class="col-md-12">
						<div class="md-form">
							<input type="password"
								placeholder="<fmt:message key='label.user.password' bundle='${lang}'/>"
								class="form-control" value="${item.pojo.passWord}" id="password" />
						</div>
					</div>
					<br /> <br />
					<div class="col-md-12">
						<div class="md-form">
							<%-- 							<select id="role">
								<option><fmt:message key="label.option.role"
										bundle="${lang}" /></option>
								<c:forEach items="${item.roles}" var="itemRole">
									<option value="${itemRole.roleId}">${itemRole.name}</option>
								</c:forEach>
							</select> --%>
							<c:choose>
								<c:when test="${not empty item.pojo.userId }">
									<select>
										<option value="${item.pojo.roleDTO.roleId }">${item.pojo.roleDTO.name }</option>
										<c:forEach items="${item.roles}" var="itemRole">
											<c:if test="${ itemRole.roleId != item.pojo.roleDTO.roleId}">
												<option value="${itemRole.roleId}">${itemRole.name}</option>
											</c:if>											
										</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
									<select id="role">
										<option><fmt:message key="label.option.role" bundle="${lang}" /></option>
										<c:forEach items="${item.roles}" var="itemRole">
											<option value="${itemRole.roleId}">${itemRole.name}</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="label.close" bundle="${lang}" />
				</button>
				<button type="button" class="btn btn-primary">
					<fmt:message key="label.save" bundle="${lang}" />
				</button>
			</div>
		</div>

	</div>

</body>
</html>