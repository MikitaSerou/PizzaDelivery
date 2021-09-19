<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/resources/images/favicon.ico"/>
    <title><spring:message code="userOffice.title"/></title>
    <spring:theme code="stylesheet" var="themeName"/>
    <link href='<spring:url value="/resources/css/${themeName}"/>' rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/scrollButtons.css"/>"
          media="screen"/>
    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/scroll-startstop.events.jquery.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/scrollButtons.js"/>"></script>
</head>
<body id="bodyDefault">
<div class="sticky-top">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/" id="mainLogo"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="dropdown">
            <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-mask"
                     viewBox="0 0 16 16">
                    <path d="M6.225 1.227A7.5 7.5 0 0 1 10.5 8a7.5 7.5 0 0 1-4.275 6.773 7 7 0 1 0 0-13.546zM4.187.966a8 8 0 1 1 7.627 14.069A8 8 0 0 1 4.186.964z"></path>
                </svg>
                <spring:message code="theme.change"/>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="?theme=dark">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-moon"
                         viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                              d="M14.53 10.53a7 7 0 0 1-9.058-9.058A7.003 7.003 0 0 0 8 15a7.002 7.002 0 0 0 6.53-4.47z"></path>
                    </svg>
                    <spring:message code="theme.dark"/></a>
                <a class="dropdown-item" href="?theme=light">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-sun"
                         viewBox="0 0 16 16">
                        <path d="M3.5 8a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0z"></path>
                        <path d="M8.202.28a.25.25 0 0 0-.404 0l-.91 1.255a.25.25 0 0 1-.334.067L5.232.79a.25.25 0 0 0-.374.154l-.36 1.51a.25.25 0 0 1-.282.188l-1.532-.244a.25.25 0 0 0-.286.286l.244 1.532a.25.25 0 0 1-.189.282l-1.509.36a.25.25 0 0 0-.154.374l.812 1.322a.25.25 0 0 1-.067.333l-1.256.91a.25.25 0 0 0 0 .405l1.256.91a.25.25 0 0 1 .067.334L.79 10.768a.25.25 0 0 0 .154.374l1.51.36a.25.25 0 0 1 .188.282l-.244 1.532a.25.25 0 0 0 .286.286l1.532-.244a.25.25 0 0 1 .282.189l.36 1.508a.25.25 0 0 0 .374.155l1.322-.812a.25.25 0 0 1 .333.067l.91 1.256a.25.25 0 0 0 .405 0l.91-1.256a.25.25 0 0 1 .334-.067l1.322.812a.25.25 0 0 0 .374-.155l.36-1.508a.25.25 0 0 1 .282-.19l1.532.245a.25.25 0 0 0 .286-.286l-.244-1.532a.25.25 0 0 1 .189-.282l1.508-.36a.25.25 0 0 0 .155-.374l-.812-1.322a.25.25 0 0 1 .067-.333l1.256-.91a.25.25 0 0 0 0-.405l-1.256-.91a.25.25 0 0 1-.067-.334l.812-1.322a.25.25 0 0 0-.155-.374l-1.508-.36a.25.25 0 0 1-.19-.282l.245-1.532a.25.25 0 0 0-.286-.286l-1.532.244a.25.25 0 0 1-.282-.189l-.36-1.509a.25.25 0 0 0-.374-.154l-1.322.812a.25.25 0 0 1-.333-.067L8.203.28zM8 2.5a5.5 5.5 0 1 1 0 11 5.5 5.5 0 0 1 0-11z"></path>
                    </svg>
                    <spring:message code="theme.light"/></a>
            </div>
        </div>
        <div class="dropdown">
            <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton2"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img src='${pageContext.request.contextPath}/resources/images/<spring:message code="img.lang.now"/>.png'
                     width="28" height="20" alt="lang.now"/><spring:message code="lang.now"/>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="?lang=en">
                    <img src='<spring:url value="/resources/images/en.png"/>' alt="en"/>
                    <spring:message code="lang.en"/></a>
                <a class="dropdown-item" href="?lang=ru">
                    <img src='<spring:url value="/resources/images/ru.png"/>' alt="ru"/>
                    <spring:message code="lang.ru"/></a>
            </div>
        </div>
        <div class="btn-group btn-group-lg" role="group" aria-label="...">
            <button type="button" class="btn btn-secondary"><a href="${pageContext.request.contextPath}/category">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trophy"
                     viewBox="0 0 16 16">
                    <path d="M2.5.5A.5.5 0 0 1 3 0h10a.5.5 0 0 1 .5.5c0 .538-.012 1.05-.034 1.536a3 3 0 1 1-1.133 5.89c-.79 1.865-1.878 2.777-2.833 3.011v2.173l1.425.356c.194.048.377.135.537.255L13.3 15.1a.5.5 0 0 1-.3.9H3a.5.5 0 0 1-.3-.9l1.838-1.379c.16-.12.343-.207.537-.255L6.5 13.11v-2.173c-.955-.234-2.043-1.146-2.833-3.012a3 3 0 1 1-1.132-5.89A33.076 33.076 0 0 1 2.5.5zm.099 2.54a2 2 0 0 0 .72 3.935c-.333-1.05-.588-2.346-.72-3.935zm10.083 3.935a2 2 0 0 0 .72-3.935c-.133 1.59-.388 2.885-.72 3.935zM3.504 1c.007.517.026 1.006.056 1.469.13 2.028.457 3.546.87 4.667C5.294 9.48 6.484 10 7 10a.5.5 0 0 1 .5.5v2.61a1 1 0 0 1-.757.97l-1.426.356a.5.5 0 0 0-.179.085L4.5 15h7l-.638-.479a.501.501 0 0 0-.18-.085l-1.425-.356a1 1 0 0 1-.757-.97V10.5A.5.5 0 0 1 9 10c.516 0 1.706-.52 2.57-2.864.413-1.12.74-2.64.87-4.667.03-.463.049-.952.056-1.469H3.504z"></path>
                </svg>
                <spring:message code="category.title"/></a></button>
            <button type="button" class="btn btn-secondary"><a href="${pageContext.request.contextPath}/constructor">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-pie-chart" viewBox="0 0 16 16">
                    <path d="M7.5 1.018a7 7 0 0 0-4.79 11.566L7.5 7.793V1.018zm1 0V7.5h6.482A7.001 7.001 0 0 0 8.5 1.018zM14.982 8.5H8.207l-4.79 4.79A7 7 0 0 0 14.982 8.5zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"></path>
                </svg>
                <spring:message code="custom.title"/></a>
            </button>
        </div>
    </nav>
</div>

<div id="content" class="container" style="min-height: 80%">
    <div class="row">
        <div class="col-sm-9">
            <div class="card text-white bg-primary mb-3" style="max-width: 540px;">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <img src='<spring:url value="/resources/images/userAvatar.png"/>'
                             alt="User Avatar" class="img-thumbnail" style="height: 100%; width: 100%;">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h2>ID: ${user.id}</h2>
                            <h2 class="card-title">${user.username}</h2>
                            <p class="card-text">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-envelope" viewBox="0 0 16 16">
                                    <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2zm13 2.383l-4.758 2.855L15 11.114v-5.73zm-.034 6.878L9.271 8.82 8 9.583 6.728 8.82l-5.694 3.44A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.739zM1 11.114l4.758-2.876L1 5.383v5.73z"></path>
                                </svg>
                                <spring:message code="eMail"/>: ${user.mail}</p>
                            <p class="card-text">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-telephone" viewBox="0 0 16 16">
                                    <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"></path>
                                </svg>
                                <spring:message code="phoneNumber"/>: ${user.phoneNumber}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="alert alert-primary">
                <h2 class="display-2" align="left"><spring:message code="ordersHistory"/>:</h2>
                <table class="table table-hover table-dark" border="1" style="width: 100%;">
                    <thead>
                    <th>ID</th>
                    <th><spring:message code="items"/></th>
                    <th><spring:message code="price"/></th>
                    <th><spring:message code="comment"/></th>
                    <th><spring:message code="time"/></th>
                    <th><spring:message code="status"/></th>
                    </thead>
                    <c:forEach items="${userOrders}" var="order">
                        <c:if test="${order.status.toString().equals('During')}">
                            <tr class="table-warning">
                        </c:if>
                        <c:if test="${order.status.toString().equals('Paid')}">
                            <tr class="table-success">
                        </c:if>
                        <c:if test="${order.status.toString().equals('Canceled')}">
                            <tr class="table-danger">
                        </c:if>
                        <td>${order.id}</td>
                        <td>

                            <c:forEach items="${order.orderItems}"
                                       var="item"><p style="font-size: 12px;">${item.product.name}&nbsp;
                                <c:if test="${!item.description.equals('')}">
                                    [${item.description}]&nbsp;</c:if>
                                - <fmt:formatNumber type="number" maxFractionDigits="2" value="${item.price}"/>
                                .<spring:message code="currency"/></p>
                            </c:forEach>
                        </td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${order.price}"/></td>
                        <td>${order.comment}</td>
                        <td>
                            <fmt:parseDate value="${order.time}" pattern="yyyy-MM-dd'T'HH:mm"
                                           var="parsedDateTime" type="both"/>
                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></td>
                        <td><spring:message code="${order.status.toString()}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="col-sm-3">
            <section class="sticky-top" style="padding-top: 90px; text-align: center">
                <div class="card text-white bg-dark mb-4">
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <h4 class="display-5">
                            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
                                 class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"></path>
                            </svg>
                                ${user.getUsername()}
                        </h4>
                        <div class="list-group">
                            <a href="${pageContext.request.contextPath}/user/basket"
                               class="list-group-item list-group-item-action">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-cart3" viewBox="0 0 16 16">
                                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                                </svg>
                                <spring:message code="basket.title"/></a>
                            <a href="${pageContext.request.contextPath}/logout"
                               class="list-group-item list-group-item-action list-group-item-danger">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"></path>
                                    <path fill-rule="evenodd"
                                          d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"></path>
                                </svg>
                                <spring:message code="logout.ref"/></a>
                        </div>
                    </sec:authorize>
                </div>
            </section>
        </div>
    </div>
</div>

<div style="display:none;" class="nav_up" id="nav_up">
    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#CCC" class="bi bi-chevron-up"
         viewBox="0 0 16 16">
        <path fill-rule="evenodd"
              d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
    </svg>
</div>
<div style="display:none;" class="nav_down" id="nav_down">
    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#CCC" class="bi bi-chevron-down"
         viewBox="0 0 16 16">
        <path fill-rule="evenodd"
              d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
    </svg>
</div>

<div id="myFooter">
    <span><spring:message code="name.full"/></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#0080ff" class="bi bi-telegram"
             viewBox="0 0 16 16">
        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.287 5.906c-.778.324-2.334.994-4.666 2.01-.378.15-.577.298-.595.442-.03.243.275.339.69.47l.175.055c.408.133.958.288 1.243.294.26.006.549-.1.868-.32 2.179-1.471 3.304-2.214 3.374-2.23.05-.012.12-.026.166.016.047.041.042.12.037.141-.03.129-1.227 1.241-1.846 1.817-.193.18-.33.307-.358.336a8.154 8.154 0 0 1-.188.186c-.38.366-.664.64.015 1.088.327.216.589.393.85.571.284.194.568.387.936.629.093.06.183.125.27.187.331.236.63.448.997.414.214-.02.435-.22.547-.82.265-1.417.786-4.486.906-5.751a1.426 1.426 0 0 0-.013-.315.337.337 0 0 0-.114-.217.526.526 0 0 0-.31-.093c-.3.005-.763.166-2.984 1.09z"></path>
    </svg>
      <spring:message code="telegram"/></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span>
           <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#ed1c24" class="bi bi-envelope"
                viewBox="0 0 16 16">
  <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2zm13 2.383l-4.758 2.855L15 11.114v-5.73zm-.034 6.878L9.271 8.82 8 9.583 6.728 8.82l-5.694 3.44A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.739zM1 11.114l4.758-2.876L1 5.383v5.73z"></path>
</svg>
        <spring:message code="mail"/></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span>
             <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#0067ce" class="bi bi-linkedin"
                  viewBox="0 0 16 16"><path
                     d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z"></path>
        </svg>
    <a href="https://www.linkedin.com/in/nikita-serov-77945a1b9/">
        <spring:message code="name.br"/>
    </a></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-github"
               viewBox="0 0 16 16">
  <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path>
</svg>
    <a href="https://github.com/MikitaSerou?tab=repositories"><spring:message code="github"/>
    </a></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#008700" class="bi bi-telephone"
             viewBox="0 0 16 16">
  <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"></path>
</svg>
    <spring:message code="phone"/></span>
</div>
</body>
</html>