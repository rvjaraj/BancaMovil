/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.LoginON;
import ec.edu.ups.proyecto.business.TransaccionesON;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fanny
 */
@ManagedBean
@ViewScoped
public class SolicitudBEAN {

    private String cedula = "";
    private Cliente cliente;
    private Solicitud solicitud;

    @Inject
    private ClienteON clienteON;



    public SolicitudBEAN() {
    }
    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        cedula= request.getParameter("cedula");
                
        if (cedula != null) {
            cliente = clienteON.buscarClienteCedula(cedula);
        }
    }
    

    @PostConstruct
    public void init() {
        //Calcular datos preliminares
        cedula = "";
        action();
        solicitud = new Solicitud();
    }
    
    public String guardarSolicitud(){
        return null;
    }
    
   
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    
    
}
