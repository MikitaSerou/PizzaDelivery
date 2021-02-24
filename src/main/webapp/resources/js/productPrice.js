$(document).ready(function () {
    $('.box').hide();
    $('#HiddenInput').empty();
    $('#HiddenInput').val($('#dropdown').val());
    var value = $('#HiddenInput').val();
    $('#dropdown').val(value);
    $('#div' + value).show();
    $('#dropdown').change(function () {
        $('.box').hide();
        $('#HiddenInput').val($(this).val());
        $('#div' + $(this).val()).show();
    });
});