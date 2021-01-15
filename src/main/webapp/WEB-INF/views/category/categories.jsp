<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="main.title"/></title>
    <spring:theme code="stylesheet" var="themeName"/>
    <link href='<spring:url value="/resources/css/${themeName}"/>' rel="stylesheet"/>
</head>

<%--Смена языка и темы ==> --%>
<span><spring:message code="lang.change"/> </span>
<span><a href="?lang=en"><img src='<spring:url value="/resources/images/en.png"/>'/></a>
<a href="?lang=ru"><img src='<spring:url value="/resources/images/ru.png"/>'/></a></span><br/>
<span><spring:message code="theme.change"/> </span>
<span>[</span>
<a href="?theme=light"><spring:message code="theme.light"/></a><span>|</span>
<a href="?theme=dark"><spring:message code="theme.dark"/> </a>
<span>]</span>
<%--  <=== Смена языка и темы--%>

<h1><spring:message code="main.greeting"/></h1></br>
<%--<img src='<spring:url value="/resources/images/pizzaP.png"/>'/><br/>--%>


<h2><spring:message code="p.categories"/>: </h2>
<c:forEach var="category" items="${categories}">
    <a href="/category/${category.name}">
        <p class="pizzaItem">${category.name}</p>
        <br/></a>
</c:forEach>

<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>--%>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>--%>
</body>
</html>



<%--
<span>[</span>
<a href="?lang=en"><spring:message code="lang.en"/></a><span>|</span>
<a href="?lang=ru"><spring:message code="lang.ru"/></a>
<span>]</span></br>
<h1><spring:message code="main.greeting"/></h1></br>--%>
