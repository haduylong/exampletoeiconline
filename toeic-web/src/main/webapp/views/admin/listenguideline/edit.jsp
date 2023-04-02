<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="requestUrl" value="/admin-guideline-listen-list.html" /> <!-- gọi lại url khi chọn bảng trang khác -->
<c:url var="listenGuideLineEditUrl" value="/admin-guideline-listen-edit.html"> <!-- link với parameter -->
	<c:param name="urlType" value="url_edit" />
</c:url>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.guideline.listen.list" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                	<c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                	<div>
                	
                		<form action="${listenGuideLineEditUrl }" method="post" enctype="multipart/form-data">
                			<div>
                				<input type="file" name="file" />
                			</div>
                			<div>
                				<input type="text" name="pojo.title" placeholder="<fmt:message key="label.guideline.title" bundle="${lang }" />"/>
                			</div>
                			<div>
                				<input type="text" name="pojo.content" placeholder="<fmt:message key="label.guideline.content" bundle="${lang }" />"/>
                			</div>
                			<div>
                				<input type="submit" value="<fmt:message key="label.done" bundle="${lang }" />"/>
                			</div>
                		</form>
                	
                	</div>
                	
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>