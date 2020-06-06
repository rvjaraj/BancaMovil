/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Vinicio
 */
;

@ManagedBean
@ViewScoped
public class ClienteBEAN {

    private Cliente newCliente;
    private Cliente auxCliente;
    private List<Cliente> listaClientees;
    private ArrayList<String> listaOpc;
    private String textoBuscar;
    private String cuenta;

    public ClienteBEAN() {
    }
    
    

    @Inject
    private ClienteON clienteON;

    @PostConstruct
    public void init() {
        newCliente = new Cliente();
        cuenta = clienteON.numeroCuenta();
        listaClientees = clienteON.listaClientees();
        textoBuscar = "";
        auxCliente = new Cliente();
    }

    public String guardarCliente() {
        try {
            System.out.println(newCliente.toString()+">>>>");
            clienteON.guardarCliente(newCliente, cuenta);
            init();
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaClientees() {
        System.out.println(textoBuscar);
        try {
            listaClientees = clienteON.listaClienteesCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaClienteID(String id) {
        try {
             auxCliente = clienteON.buscarCliente(id);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String actualizarCliente(){
        clienteON.actualizarCliente(auxCliente);
        init();
        System.out.println("actualizado");
        return null;
    }
    
    public String eliminarCliente(){
        auxCliente.setEliminado(true);
        auxCliente.getCuentaList().get(0).setEliminado(true);
        clienteON.actualizarCliente(auxCliente);
        init();
        System.out.println("Eliminado");
        return null;
    }
    
    // -------------------> 
    public Cliente getNewCliente() {
        return newCliente;
    }

    public void setNewCliente(Cliente newCliente) {
        this.newCliente = newCliente;
    }

    public ClienteON getClienteON() {
        return clienteON;
    }

    public void setClienteON(ClienteON clienteON) {
        this.clienteON = clienteON;
    }

    public ArrayList<String> getListaOpc() {
        return listaOpc;
    }

    public void setListaOpc(ArrayList<String> listaOpc) {
        this.listaOpc = listaOpc;
    }



    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Cliente getAuxCliente() {
        return auxCliente;
    }

    public void setAuxCliente(Cliente auxCliente) {
        this.auxCliente = auxCliente;
    }

    public List<Cliente> getListaClientees() {
        return listaClientees;
    }

    public void setListaClientees(List<Cliente> listaClientees) {
        this.listaClientees = listaClientees;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
