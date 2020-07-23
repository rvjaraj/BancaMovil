/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.services;

import ec.edu.ups.proyecto.business.ServicesON;
import ec.edu.ups.proyecto.emtitis.DepositoSRV;
import ec.edu.ups.proyecto.emtitis.Mensajes;
import ec.edu.ups.proyecto.emtitis.RetiroSRV;
import ec.edu.ups.proyecto.emtitis.SolicitudSRV;
import ec.edu.ups.proyecto.emtitis.TransferenciaSRV;
import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vinicio
 */
@Path("/Servises")
public class BancaMovilServicesREST {

    @Inject
    ServicesON servicesON;

    @Path("/deposito")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensajes Deposito(DepositoSRV depositoSRV) {
        return servicesON.DepositoSRV(depositoSRV);
    }

    @Path("/retiro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensajes Retiro(RetiroSRV retiroSRV) {
        return servicesON.RetiroSRV(retiroSRV);
    }

    @Path("/transferencia")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensajes Transferencias(TransferenciaSRV transferenciaSRV) {
        transferenciaSRV.toString();
        return servicesON.TransferenciasInternaSRV(transferenciaSRV);
    }
    
    @Path("/listaClientes")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SolicitudSRV> listaClientes(){
        return servicesON.enviarDataSet();
    }

}
