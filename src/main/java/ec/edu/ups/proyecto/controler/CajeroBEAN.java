/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.CreditosON;
import ec.edu.ups.proyecto.emtitis.Amortizacion;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Credito;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private double valorapagar;
    
    private List<Credito> listaCreditos;
    private List<Amortizacion> listaAmortizacions;
    
    public CajeroBEAN() {
    }

    @Inject
    ClienteON clienteON;
    
    @Inject 
    CreditosON creditosON;

    @PostConstruct
    public void init() {
        newCliente = new Cliente();
        listaClientees = clienteON.listaClientees();
        textoBuscar = "";
        auxCliente = new Cliente();
        saldo = 0;
        listaCreditos= creditosON.listarCreditos();
        listaAmortizacions = new ArrayList<>();
        valorapagar = 0;
    }
    /**
     * Busca los clientes , consume la logica de negocio del cliente
     * Esta metodo se utiliza en la vista
     * @return
     */
    
    public String pagarAmortizacionCuota(Amortizacion amortizacion){
         if(amortizacion.getTotal() <= valorapagar){
             System.out.println(valorapagar +" >>>>>>>>>");
             amortizacion.setEstado(true);
             amortizacion.setTotal(0);
         }else{
             amortizacion.setTotal(amortizacion.getTotal() - valorapagar);
         }
        creditosON.actualizarCrediito(amortizacion.getCredito());
        //Acutalizamos datos
        cargarTabla(amortizacion.getCredito());
        listaCreditos= creditosON.listarCreditos();
        valorapagar = 0;
        return "";
    }
    
    public String pagarAmortizacion(Amortizacion amortizacion){
        amortizacion.setEstado(true);
        amortizacion.setTotal(0);
        creditosON.actualizarCrediito(amortizacion.getCredito());
        //Acutalizamos datos
        cargarTabla(amortizacion.getCredito());
        listaCreditos= creditosON.listarCreditos();
        return "";
    }
    
    public String cargarTabla(Credito credito){
        System.out.println(">>>>>>>");
        System.out.println(credito);
        listaAmortizacions = credito.getAmortizacionList();
        return "";
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
    /**
     * Busca el cliente por el id
     * Utiliza la logica de negocio 
     * @param id
     * @return
     */
    public String buscaClienteID(String id) {
        try {
            auxCliente = clienteON.buscarCliente(id);
        } catch (Exception ex) {
            Logger.getLogger(CajeroBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Actualiza al cliente 
     * Esta logica es consuimida desde el cliente 
     * @return
     */
    public String actualizarCliente() {
        System.out.println("Deposito");
        BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() + saldo);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
        
        clienteON.actualizarClienteTrasaccion(auxCliente, "Deposito", new BigDecimal(saldo).doubleValue());
        System.out.println(saldo +"esto quye paso");
        init();
        return null;
    }
    /**
     * Permite actualizar al cliente Cuando este realiza un 
     * retiro
     * @return
     */
    public String actualizarClienteR() {
        System.out.println("Retiro");
        BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() - saldo);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
        clienteON.actualizarClienteTrasaccion(auxCliente, "Retiro", new BigDecimal(saldo).doubleValue());
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

    public List<Credito> getListaCreditos() {
        return listaCreditos;
    }

    public void setListaCreditos(List<Credito> listaCreditos) {
        this.listaCreditos = listaCreditos;
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

    public double getValorapagar() {
        return valorapagar;
    }

    public void setValorapagar(double valorapagar) {
        this.valorapagar = valorapagar;
    }
    
    
    

}
