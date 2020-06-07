/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.LoginON;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.util.List;
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
    private List<Alogin>  historial;

    @Inject
    private ClienteON clienteON;
    
    private LoginON loginON;

    public UsuarioBEAN() {
    }

    @PostConstruct
    public void init() {
        cedula = "";
        action();
    }

    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        String param = request.getParameter("cedula");
        cedula = param;
        if(cedula != null){
            cliente = clienteON.buscarClienteCedula(cedula);
            historial = loginON.listaLogin(cedula);
        }
        
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
    
     
   


}
