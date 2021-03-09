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
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title><spring:message code="online.payment.title"/></title>
    <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' rel='stylesheet'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/onlinePayment.css"/>'>
    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script type='text/javascript'
            src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
    <script src="<c:url value="/resources/js/onlinePaymentScript.js" />"></script>
</head>
<body oncontextmenu='return false' class='snippet-body'>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class=" col-lg-6 col-md-8">
            <div class="card p-3">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <h2 class="heading text-center"><spring:message code="online.payment.details"/></h2>
                    </div>
                </div>
                <form class="form-card" method="post" action="${pageContext.request.contextPath}/onlinePayment">
                    <div class="row justify-content-center mb-4 radio-group">
                        <div class="col-sm-3 col-5">
                            <div class='radio selected mx-auto' data-value="dk"><img class="fit-image"
                                                                                     src="https://i.imgur.com/5TqiRQV.jpg"
                                                                                     width="105px" height="55px"></div>
                        </div>
                        <div class="col-sm-3 col-5">
                            <div class='radio mx-auto' data-value="visa"><img class="fit-image"
                                                                              src="https://i.imgur.com/OdxcctP.jpg"
                                                                              width="105px" height="55px"></div>
                        </div>
                        <div class="col-sm-3 col-5">
                            <div class='radio mx-auto' data-value="master"><img class="fit-image"
                                                                                src="https://i.imgur.com/WIAP9Ku.jpg"
                                                                                width="105px" height="55px"></div>
                        </div>
                        <div class="col-sm-3 col-5">
                            <div class='radio mx-auto' data-value="paypal"><img class="fit-image"
                                                                                src="https://i.imgur.com/cMk1MtK.jpg"
                                                                                width="105px" height="55px"></div>
                        </div>
                        <br>
                    </div>
                    <div class="row justify-content-center form-group">
                        <div class="col-12 px-auto">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input id="customRadioInline1" type="radio" name="customRadioInline1"
                                       class="custom-control-input" checked="true">
                                <label for="customRadioInline1" class="custom-control-label label-radio"><spring:message
                                        code="private"/></label></div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input id="customRadioInline2" type="radio" name="customRadioInline1"
                                       class="custom-control-input">
                                <label for="customRadioInline2" class="custom-control-label label-radio"><spring:message
                                        code="business"/></label></div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-12">
                            <div class="input-group"><input type="text" name="Name" placeholder="John Doe" required>
                                <label><spring:message code="card.name"/></label></div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-12">
                            <div class="input-group"><input type="text" id="cr_no" name="card-no"
                                                            placeholder="0000 0000 0000 0000" minlength="19"
                                                            maxlength="19" required>
                                <label><spring:message code="card.number"/></label></div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-6">
                                    <div class="input-group">
                                        <input type="text" id="exp" name="expdate" placeholder="MM/YY" minlength="5"
                                               maxlength="5" required>
                                        <label><spring:message code="expiry.date"/></label></div>
                                </div>
                                <div class="col-6">
                                    <div class="input-group">
                                        <input type="password" name="cvv" placeholder="&#9679;&#9679;&#9679;"
                                               minlength="3" maxlength="3" required>
                                        <label>CVV</label></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="orderId" value="${orderId}">
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <input type="submit" formmethod="post" value="<spring:message code="online.payment"/>
                             ${orderSum}.<spring:message code="currency"/>" class="btn btn-pay placeicon"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>