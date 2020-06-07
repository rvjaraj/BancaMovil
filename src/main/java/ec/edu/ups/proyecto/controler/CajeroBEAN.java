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
public class CajeroBEAN {

    private Cliente newCliente;
    private Cliente auxCliente;
    private List<Cliente> listaClientees;
    private ArrayList<String> listaOpc;
    private String textoBuscar;
    private double saldo;
    public CajeroBEAN() {
    }
    
    

    @Inject
    private ClienteON clienteON;

    @PostConstruct
    public void init() {
        newCliente = new Cliente();
        listaClientees = clienteON.listaClientees();
        textoBuscar = "";
        auxCliente = new Cliente();
        saldo = 0;
    }

    public String buscaClientees() {
        System.out.println(textoBuscar);
        try {
            listaClientees = clienteON.listaClienteesCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(CajeroBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaClienteID(String id) {
        try {
             auxCliente = clienteON.buscarCliente(id);
        } catch (Exception ex) {
            Logger.getLogger(CajeroBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String actualizarCliente(){
        auxCliente.getCuentaList().get(0).setSaldo(auxCliente.getCuentaList().get(0).getSaldo()+saldo);
        clienteON.actualizarCliente(auxCliente);
        init();
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
