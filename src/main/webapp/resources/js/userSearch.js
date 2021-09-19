function mysearch() {
    let elem = document.getElementById('inputsearchquery');
    let url = document.getElementById('adminUrl').value + "/admin/users/" + encodeURIComponent(elem.value);
    let win = window.open(url, '_blank');
    win.focus();
}