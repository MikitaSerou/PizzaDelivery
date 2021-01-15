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
    <a href="?lang=ru"><img src='<spring:url value="/resources/images/ru.png"/>'/></a></span>
<span><spring:message code="theme.change"/>:</span>
<span>[</span>
<a href="?theme=light"><spring:message code="theme.light"/></a><span>|</span>
<a href="?theme=dark"><spring:message code="theme.dark"/> </a>
<span>]</span>
<%--  <=== Смена языка и темы--%>

<h1><spring:message code="category.pizza"/>: ${categoryName}</h1></br>
<span class="returnToMain"><a href="/category"><spring:message code="button.mainPage"/></a></span>
<%--<h2><spring:message code="category.pizza"/>:${categoryName}</h2>--%>
<c:forEach var="pizza" items="${pizzaList}">
    <fieldset>
        <p class="pizzaName">${pizza.name}</p><span><img src='<spring:url value="/resources/images/pizzaP.png"/>'/><br/></span>
        <p><spring:message code="p.describe"/>: ${pizza.describe}</p>
        <p><spring:message code="p.dough"/>: ${pizza.dough.name}</p>
        <p><spring:message code="p.price"/>: ${pizza.price}</p>
        <br/></fieldset>
</c:forEach>
<br/>
<a href="/category"><spring:message code="button.mainPage"/></a>
</body>
</html>