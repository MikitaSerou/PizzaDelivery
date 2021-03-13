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
    <title>EIDEEE</title>
    <spring:theme code="stylesheet" var="themeName"/>
    <link href='<spring:url value="/resources/css/${themeName}"/>' rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>





    <!-- JQuery -->
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>

    <script type="text/javascript">
        $(function() {
            $('button[type=submit]').click(function(e) {
                e.preventDefault();
                //Disable submit button
                $(this).prop('disabled',true);

                var form = document.forms[0];
                var formData = new FormData(form);

                // Ajax call for file uploaling
                var ajaxReq = $.ajax({
                    url : window.location.href,
                    type : 'POST',
                    data : formData,
                    cache : false,
                    contentType : false,
                    processData : false,
                    xhr: function(){
                        //Get XmlHttpRequest object
                        var xhr = $.ajaxSettings.xhr() ;

                        //Set onprogress event handler
                        xhr.upload.onprogress = function(event){
                            var perc = Math.round((event.loaded / event.total) * 100);
                            $('#progressBar').text(perc + '%');
                            $('#progressBar').css('width',perc + '%');
                        };
                        return xhr ;
                    },
                    beforeSend: function( xhr ) {
                        //Reset alert message and progress bar
                        $('#alertMsg').text('');
                        $('#progressBar').text('');
                        $('#progressBar').css('width','0%');
                    }
                });

                // Called on success of file upload
                ajaxReq.done(function(msg) {
                    $('#alertMsg').text(msg);
                    $('input[type=file]').val('');
                    $('button[type=submit]').prop('disabled',false);
                });

                // Called on failure of file upload
                ajaxReq.fail(function(jqXHR) {
                    $('#alertMsg').text(jqXHR.responseText+'('+jqXHR.status+
                        ' - '+jqXHR.statusText+')');
                    $('button[type=submit]').prop('disabled',false);
                });
            });
        });
    </script>

</head>
<body>
<div class="container">
    <h2>Spring MVC - File Upload Example With Progress Bar</h2>
    <hr>
    <!-- File Upload From -->
    <form action="${pageContext.request.contextPath}/user/editUser" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label>Select File</label>
            <input class="form-control" type="file" name="file">
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Upload</button>
        </div>
    </form>
    <br />

<%--    <!-- Bootstrap Progress bar -->
    <div class="progress">
        <div  class="progress-bar progress-bar-success" role="progressbar"
             aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
    </div>--%>


    <div class="progress">
        <div id="progressBar" class="progress-bar progress-bar-striped bg-success progress-bar-animated" role="progressbar"
             aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">0%</div>
    </div>

    <!-- Alert -->
    <div id="alertMsg" style="color: red;font-size: 18px;"></div>
</div>
</body>
</html>