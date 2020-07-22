/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.ClienteDAO;
import ec.edu.ups.proyecto.dao.SolicitudDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vinic
 */
@Stateless
public class SolicitudON {

    @Inject
    ClienteDAO clienteDAO;

    @Inject
    SolicitudDAO solicitudDAO;
    


    public SolicitudON() {
    }

    public Solicitud datosIniciales(Cliente cliente) {
        Solicitud solicitud = new Solicitud();
        solicitud.setEstado("revision");
        solicitud.setTipocliente("por definir");
        solicitud.setElimado(false);
        solicitud.setCliente(cliente);
        solicitud.setTasadepagos(5);
        solicitud.setEstadocivil(cliente.getEstadocivil());
        Date fechaAct = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay());
        solicitud.setEdad(fechaAct.getYear() - cliente.getFechNac().getYear());
        solicitud.setSaldocuenta(cliente.getCuentaList().get(0).getSaldo() + "");
        
        //Calular valores 
        List<Solicitud> listSol = this.listarSalicitudesCliente(cliente.getCedula());
        if(listSol.isEmpty()){
            solicitud.setNumerocreditos(0);
            solicitud.setHistorial("No cuenta con creditos");
        }else{
            solicitud.setNumerocreditos(listSol.size());
            solicitud.setHistorial("Validar como van los creditos");
        }
        

        return solicitud;
    }
    
    public void guardarSolicuitud(Solicitud solicitud) {
        try {
            solicitudDAO.insert(solicitud);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public List<Solicitud>  listarSalicitudes() {
        try {
             return solicitudDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public List<Solicitud>  listarSalicitudesCliente(String cedula) {
        try {
            System.out.println(cedula +" >>>>>>>>>>");
             return solicitudDAO.findByCliente(cedula);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
