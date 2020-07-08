/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.services;

import ec.edu.ups.proyecto.business.ServicesON;
import ec.edu.ups.proyecto.emtitis.Mensajes;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Vinicio
 */
@WebService
public class BancaMovilServicesSOAP {
    
    @Inject
    ServicesON servicesON;
    
    
    @WebMethod
    public String Deposito(String numeroCuenta, Double cantidad){
        return servicesON.DepositoSRV(numeroCuenta, cantidad).getNombre();
    }
    
    
    @WebMethod
    public String Retiro(String numeroCuenta, Double cantidad){
        return servicesON.RetiroSRV(numeroCuenta, cantidad).getNombre();
    }
    
    @WebMethod
    public String Transferencias(String numeroCuentaOrigen, String numeroCuentaDestino, Double cantidad, String concepto){
        return servicesON.TransferenciasInternaSRV(numeroCuentaOrigen, numeroCuentaDestino, cantidad, concepto).getNombre();
    }
}
