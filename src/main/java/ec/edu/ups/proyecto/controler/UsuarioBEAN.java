/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.CreditosON;
import ec.edu.ups.proyecto.business.LoginON;
import ec.edu.ups.proyecto.business.TransaccionesON;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Amortizacion;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Credito;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import java.util.ArrayList;
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
 * @author Ricardo Jara
 */
@ManagedBean
@ViewScoped
public class UsuarioBEAN {

    private String cedula = "";
    private Cliente cliente;
    private List<Alogin> historial;
    private List<Transaciones> listaTransacioneses;
    private List<Credito> listaCreditos;
    private String iniio;
    private String fin;
    private List<Amortizacion> listaAmortizacions;
    
    @Inject
    private ClienteON clienteON;

    @Inject
    private LoginON loginON;

    @Inject
    private TransaccionesON transaccionesON;
    
    @Inject
    private CreditosON creditosON;

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
        cedula= request.getParameter("cedula");
                
        if (cedula != null) {
            cliente = clienteON.buscarClienteCedula(cedula);
            historial = loginON.listaLogin(cedula);
            listaTransacioneses = transaccionesON.listaTransacionesCedula(cedula);
            listaCreditos = creditosON.listarCreditosCliente(cedula);
            listaAmortizacions = new ArrayList<>();
        }

    }
    
    public String cargarTabla(Credito credito){
        System.out.println("aca");
        System.out.println(credito);
        listaAmortizacions = credito.getAmortizacionList();
        return "";
    }
    
    public String cambiarContrasenia(){
    	System.out.println("Llega hasta cambiar contra");
    	try {
			clienteON.actContraCliente(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "loginc?faces-redirect=true&tipo=cliente&msj=contrasena actualizada revise su correo";

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

    public List<Credito> getListaCreditos() {
        return listaCreditos;
    }

    public void setListaCreditos(List<Credito> listaCreditos) {
        this.listaCreditos = listaCreditos;
    }

    public ClienteON getClienteON() {
        return clienteON;
    }

    public void setClienteON(ClienteON clienteON) {
        this.clienteON = clienteON;
    }

    public LoginON getLoginON() {
        return loginON;
    }

    public void setLoginON(LoginON loginON) {
        this.loginON = loginON;
    }

    public TransaccionesON getTransaccionesON() {
        return transaccionesON;
    }

    public void setTransaccionesON(TransaccionesON transaccionesON) {
        this.transaccionesON = transaccionesON;
    }

    public CreditosON getCreditosON() {
        return creditosON;
    }

    public void setCreditosON(CreditosON creditosON) {
        this.creditosON = creditosON;
    }

    public List<Amortizacion> getListaAmortizacions() {
        return listaAmortizacions;
    }

    public void setListaAmortizacions(List<Amortizacion> listaAmortizacions) {
        this.listaAmortizacions = listaAmortizacions;
    }
    
    

}
