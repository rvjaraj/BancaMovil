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
import ec.edu.ups.proyecto.emtitis.Trabajador;
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
public class UsuarioBEAN {

    private String cedula = "";
    private Cliente cliente;
    private List<Alogin> historial;
    private List<Transaciones> listaTransacioneses;
    private String iniio;
    private String fin;

    @Inject
    private ClienteON clienteON;

    @Inject
    private LoginON loginON;

    @Inject
    private TransaccionesON transaccionesON;

    public UsuarioBEAN() {
    }

    @PostConstruct
    public void init() {
        cedula = "";
        action();
    }

    /**
     * Captura el parametro enviado desde la vista y mediante ese parametro se
     * puede llamar a las distintas interfaces
     */
    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        cedula = request.getParameter("cedula");
        
        if (cedula != null) {
            cliente = clienteON.buscarClienteCedula(cedula);
            historial = loginON.listaLogin(cedula);
            listaTransacioneses = transaccionesON.listaTransacionesCedula(cedula);
        }

    }
    
    public String verTodo(){
        listaTransacioneses = transaccionesON.listaTransacionesCedula(cedula);
        return null;
    }
    
    public String listFechas() {
        System.out.println(iniio + ">><<<" + fin);
        listaTransacioneses =transaccionesON.listaTransacionesesFecha(iniio, fin, cedula);
        return null;
    }
    
    public String cambiarContrasenia(){
        System.out.println("haces lo que yo quiero" );
        try {
            clienteON.actContraCliente(cliente);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "loginc?faces-redirect=true&tipo=cliente&msj=Contraseña actualizada, Rebice su corre con su nueva clave";
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

    public List<Alogin> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Alogin> historial) {
        this.historial = historial;
    }

    public List<Transaciones> getListaTransacioneses() {
        return listaTransacioneses;
    }

    public void setListaTransacioneses(List<Transaciones> listaTransacioneses) {
        this.listaTransacioneses = listaTransacioneses;
    }

    public String getIniio() {
        return iniio;
    }

    public void setIniio(String iniio) {
        this.iniio = iniio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

}
