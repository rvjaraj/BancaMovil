function validarRetiro(retiro) {
    var saldo = $("#saldoR").val();
    if (parseFloat(retiro) > parseFloat(0)) {
        if (parseFloat(saldo) < parseFloat(retiro)) {
            console.log("Es cero");
            $("#divRetirar").html("CANTIDAD MENOR AL SALDO<input id='editarCuenta:btnRetirar' " +
                    "type='submit' name='editarCuenta:btnRetirar' " +
                    "value='Retirar Dinero' class=' btn btn-dark btn-block'" +
                    "onclick=\'mojarra.ab(this,event,\'action\',\'editarCuenta\',\'@all\');return false " +
                    "disabled='false'>");
        } else {
            console.log("false");
            $("#divRetirar").html("<input id='editarCuenta:btnRetirar' " +
                    "type='submit' name='editarCuenta:btnRetirar' " +
                    "value='Retirar Dinero' class=' btn btn-dark btn-block'" +
                    "onclick=\"mojarra.ab(this,event,\'action\',\'editarCuenta\',\'@all\') return false\" >");
        }
    } else {
        console.log("Es cero");
        $("#divRetirar").html("CANTIDAD MAYOR A CERO<input id='editarCuenta:btnRetirar' " +
                "type='submit' name='editarCuenta:btnRetirar' " +
                "value='Retirar Dinero' class=' btn btn-dark btn-block'" +
                "onclick=\'mojarra.ab(this,event,\'action\',\'editarCuenta\',\'@all\') return false " +
                "disabled='false'>");
    }

}
function comprueba(valor) {
    if (valor.value != "") {
        if (valor.value > 0) {
            valor.value = valor.value;
        } else {
            valor.value = Math.abs(valor.value);
        }
    }
}