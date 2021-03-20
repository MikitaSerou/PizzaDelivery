<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/resources/images/favicon.ico"/>
    <title>500</title>
    <spring:theme code="stylesheet" var="themeName"/>
    <link href='<spring:url value="/resources/css/${themeName}"/>' rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div class="sticky-top">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">

        <a class="navbar-brand" href="${pageContext.request.contextPath}/" id="mainLogo"></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</div>
<div class="container" style="min-height: 80%">
    <div class="row justify-content-center align-items-center"></div>
    <div class="row justify-content-center align-items-center">
        <div class="col-sm"></div>
        <div class="col-sm-6" style="backdrop-filter: blur(7px); border-radius: 30px">
            <br/>
            <div class="row justify-content-md-center">
                <div class="alert alert-dismissible alert-danger">
                    <h1 align="center">500</h1>
                    <h2 align="center">${ex}</h2>
                </div>
                <img src='<spring:url value="https://gifki.info/uploads/posts/2017-06/1498693660_2955-sho-opyat.gif"/>'
                     width="384px"
                     height="344px" class="rounded" alt="kek">
                <br/>
            </div>
            <br/>
        </div>
        <div class="col-sm"></div>
    </div>
</div>
</body>
</html>