function mysearch() {
    var elem = document.getElementById('inputsearchquery');
    var url = document.getElementById('adminUrl').value + "/admin/users/"  + encodeURIComponent(elem.value);
    var win = window.open(url, '_blank');
    win.focus();
}