<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/resources/images/favicon.ico"/>
    <title>EIDEEE</title><!-- TODO title -->
    <spring:theme code="stylesheet" var="themeName"/>
    <link href='<spring:url value="/resources/css/${themeName}"/>' rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/fileUploading.js"/>"></script>

</head>
<body>
<div class="container">
    <h2>Spring MVC - File Upload Example With Progress Bar</h2>
    <hr>
    <!-- File Upload From -->
    <form name="fileUploadForm" action="${pageContext.request.contextPath}/user/editUser/uploadFile" method="post" enctype="multipart/form-data">
        <label for="uploadingUrl"></label>
        <input id="uploadingUrl" hidden name="url" value="${pageContext.request.contextPath}/user/editUser/uploadFile">
        <div class="form-group">
            <label>Select File</label>
            <input class="form-control" type="file" name="file">
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Upload</button>
        </div>
    </form>
    <br />
    <div class="progress">
        <div id="progressBar" class="progress-bar progress-bar-striped bg-success progress-bar-animated" role="progressbar"
             aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%; color: white; font-size: 14px;">0%</div>
    </div>
    <!-- Alert -->
    <div id="alertMsg" style="color: red;font-size: 18px;"></div>
</div>
</body>
</html>