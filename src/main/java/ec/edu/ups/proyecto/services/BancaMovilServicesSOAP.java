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
    public Mensajes Deposito(DepositoSRV depositoSRV){
        return servicesON.DepositoSRV(depositoSRV);
    }
    
    
    @WebMethod
    public Mensajes Retiro(RetiroSRV retiroSRV){
        return servicesON.RetiroSRV(retiroSRV);
    }
    
    @WebMethod
    public Mensajes Transferencias(TransferenciaSRV transferenciaSRV){
        return servicesON.TransferenciasInternaSRV(transferenciaSRV);
    }
    
    @WebMethod
    public List<SolicitudSRV> listaClientes(){
        return servicesON.enviarDataSet();
    }
}
