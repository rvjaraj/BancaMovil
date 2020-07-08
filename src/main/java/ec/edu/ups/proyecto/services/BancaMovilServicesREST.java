/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.services;

import ec.edu.ups.proyecto.business.ServicesON;
import ec.edu.ups.proyecto.emtitis.Mensajes;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Vinicio
 */
@Path("/Servises")
public class BancaMovilServicesREST {

    @Inject
    ServicesON servicesON;

    @WebMethod
    @GET
    @Path("/deposito")
    @Produces("application/json")
    public Mensajes Deposito(@QueryParam("numerocuenta") String numeroCuenta,@QueryParam("cantidad")  Double cantidad) {
        return servicesON.DepositoSRV(numeroCuenta, cantidad);
    }

    @GET
    @Path("/retiro")
    @Produces("application/json")
    public Mensajes Retiro(@QueryParam("numerocuenta") String numeroCuenta,@QueryParam("cantidad")  Double cantidad) {
        return servicesON.RetiroSRV(numeroCuenta, cantidad);
    }

    @GET
    @Path("/transferenciainterna")
    @Produces("application/json")
    public Mensajes Transferencias(@QueryParam("numeroCuentaOrigen") String numeroCuentaOrigen,@QueryParam("numeroCuentaDestino") String numeroCuentaDestino, 
            @QueryParam("cantidad") Double cantidad, @QueryParam("concepto") String concepto) {
        return servicesON.TransferenciasInternaSRV(numeroCuentaOrigen, numeroCuentaDestino, cantidad, concepto);
    }
    
    
    @GET
    @Produces("application/json")
    public String Hola() { 
        return "HOLA";
    }
}
