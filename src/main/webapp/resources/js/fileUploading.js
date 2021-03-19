$(function() {
    $('#mainFormButton').prop('disabled',true);

    $('button#fileUploadButton[type=submit]').click(function(e) {
        e.preventDefault();
        //Disable submit button
        $(this).prop('disabled',true);

        var form = document.forms['fileUploadForm'];
        var formData = new FormData(form);
        // Ajax call for file uploaling
        var ajaxReq = $.ajax({
            url : document.getElementById('uploadingUrl').value,
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
            $('#alertMsg').css({'background-color':'green'});
            $('#alertMsg').text(msg);

            $('#progressBar').removeClass('progress-bar progress-bar-striped bg-danger progress-bar-animated');
            $('#progressBar').addClass('progress-bar progress-bar-striped bg-success progress-bar-animated');
            $('#fileUploadButton').removeClass('btn btn-outline-success');
            $('#fileUploadButton').addClass('btn btn-outline-secondary');

            $('input[type=file]').val('');
            $('button#fileUploadButton[type=submit]').prop('disabled',false);
            $('#mainFormButton').prop('disabled',false);
        });

        // Called on failure of file upload
        ajaxReq.fail(function(jqXHR) {
            $('#progressBar').removeClass('progress-bar progress-bar-striped bg-success progress-bar-animated');
            $('#progressBar').addClass('progress-bar progress-bar-striped bg-danger progress-bar-animated');
            $('#progressBar').text('faled');
            $('#alertMsg').css({'background-color':'#ec4844'});
            $('#alertMsg').text(jqXHR.responseText);
            $('button#fileUploadButton[type=submit]').prop('disabled',false);
        });
    });
});