<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="requestUrl" value="/admin-guideline-listen-list.html" /> <!-- gọi lại url khi chọn bảng trang khác -->
<c:url var="listenGuideLineEditUrl" value="/admin-guideline-listen-edit.html"> <!-- link với parameter -->
	<c:param name="urlType" value="url_edit" />
</c:url>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
	<style>
		.error{
			color : red;
		}
	</style>
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
                	
                		<form action="${listenGuideLineEditUrl }" method="post" enctype="multipart/form-data" id="formEdit">
                			<div>
                				<label>Ảnh cho bài hd nghe	</label>
                				<input type="file" name="file" id="uploadImage"/>
                			</div>
                			<br/>
                			<div>
                				<c:if test="${not empty item.pojo.image}">
                                    <c:set var="image" value="/fileupload/listenguideline/${item.pojo.image}"/>
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150px">
                                <img src="/toeic-web/src/main/webapp/fileupload/listenguideline/slide1.png" width="150px" height="150px">
                			</div>
                			<br/>
                			<div>
                				<label>Tiêu đề bài hd nghe	</label>
                				<input type="text" name="pojo.title" id="title" value="${ item.pojo.title}" placeholder="<fmt:message key="label.guideline.title" bundle="${lang }" />"/>
                			</div>
                			<div>
                				<c:if test="${not empty item.pojo.content }">
                					<c:set var="content" value="${item.pojo.content }"></c:set>
                				</c:if>
                				
                				<label>Nội dung bài hd nghe</label>
                				<div>
                					<textarea name="pojo.content" cols="80" rows="10" id="listenGuidelineContent">${content }</textarea>
                				</div>
<%--                 				<input type="text" name="pojo.content" placeholder="<fmt:message key="label.guideline.content" bundle="${lang }" />"/> --%>                				
                			</div>
                			<div>
                				<input type="submit" value="<fmt:message key="label.done" bundle="${lang }" />"/>
                			</div>
                			<c:if test="${not empty item.pojo.listenGuideLineId}">
                            	<input type="hidden" name="pojo.listenGuideLineId" value="${item.pojo.listenGuideLineId}"/>
                        	</c:if>
                		</form>
                	
                	</div>
                	
                </div>
            </div>
        </div>
    </div>
</div>

<script>
	var listenGuideLineId = '';
	<c:if test="${not empty item.pojo.listenGuideLineId}">
	    listenGuideLineId = ${item.pojo.listenGuideLineId};
	</c:if>
	
	$(document).ready(function (){
		CKEDITOR.replace('listenGuidelineContent');
		validateData();
		$('#uploadImage').change(function (){
			 readURL(this, "viewImage");
		})
	});

	function validateData(){
		$('#formEdit').validate({
			ignore:[],
			rule:[],
			messages:[]
		});
		$('#title').rules("add",{
			required: true,
			messages: {
				 required: '<fmt:message key="label.empty" bundle="${lang}"/>'
			}
		})
		
		 if (listenGuidelineId == '') {// update thì có thể để ảnh trống
			$('#uploadImage').rules("add",{
				required: true,
				messages: {
					 required: '<fmt:message key="label.empty" bundle="${lang}"/>'
				}
			});
		}
		
		$('#listenGuidelineContent').rules("add",{
			required: function(){
				CKEDITOR.instances.listenGuidelineContent.updateElement();
			},
			messages: {
				 required: '<fmt:message key="label.empty" bundle="${lang}"/>'
			}
		});
	};

    function readURL(input, imageId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
     }
</script>
</body>
</html>