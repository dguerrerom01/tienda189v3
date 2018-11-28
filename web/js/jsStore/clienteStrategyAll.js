STORE.namespace('STORE.list_input');

(function(g){
    'use strict';

    var form = $("client_register");

    STORE.list_input = form.querySelectorAll("[data-functioncallback]");

    STORE.Error.off();

    STORE.Submit.on();

    for (var i = 0; i < STORE.list_input.length; i++) {

        STORE.list_input[i].addEventListener("input",eval("STORE." + STORE.list_input[i].dataset.functioncallback),false);

        STORE.list_input[i].style.backgroundColor = STORE.Color.colorValido;
    }

}) (window,undefined);

var lista = document.getElementById("clientSexo");
lista.options[lista.selectedIndex].defaultSelected = true;
for (var i = 1; i < lista.options.length; i++) {
    lista.options[i].defaultSelected = i == lista.selectedIndex;
}

