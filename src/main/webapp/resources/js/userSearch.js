function mysearch() {
    var elem = document.getElementById('inputsearchquery');
/*    var url = "admin/users/" + encodeURIComponent(elem.value);
    var win = window.open(url, '_blank');*/

    var url="http://localhost:8080/admin/users/"+encodeURIComponent(elem.value);
    var win=window.open(url, '_blank');
    win.focus();
}