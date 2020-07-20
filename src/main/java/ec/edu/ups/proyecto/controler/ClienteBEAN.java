/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.ResumenCuentaON;
import ec.edu.ups.proyecto.emtitis.Cliente;
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
 * @author Vinicio
 */
;

@ManagedBean
@ViewScoped
public class ClienteBEAN {

    private Cliente newCliente;
    private Cliente auxCliente;
    private List<Cliente> listaClientees;
    private String textoBuscar;
    private String cuenta;
    private String cedulaPersona;
    private List<Transaciones> listaCuentasClientes = new ArrayList<Transaciones>();
    private List<Cliente> listaClientes = new ArrayList<Cliente>();
    private List<Transaciones> listaEstadosCta = new ArrayList<Transaciones>();
    private Date fechaInicio;
    private Date fechaFin;
    private String tipo;
    private List<String> estadocivilist;

    public ClienteBEAN() {
    }

    @Inject
    private ClienteON clienteON;

    @Inject
    private ResumenCuentaON resumenCuentaON;

    @PostConstruct
    public void init() {
        newCliente = new Cliente();
        cuenta = clienteON.numeroCuenta();
        listaClientees = clienteON.listaClientees();
        textoBuscar = "";
        estadocivilist = new ArrayList<>();
        estadocivilist.add("Divorciado");
        estadocivilist.add("Divorciada");
        estadocivilist.add("Soltero");
        estadocivilist.add("Soltera");
        estadocivilist.add("Casado");
        estadocivilist.add("Casada");

        auxCliente = new Cliente();
        loadResumenCliente();

        action();
    }

    /**
     * Permite obtener el dato que se pasa a traves de la url para poder manejar
     * en los distintos metodos.
     */
    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        String param = request.getParameter("tipo");
        tipo = param;

    }

    /**
     * Guarda el cliente desde la vista consumiendo la logica de negocio
     * existente.
     *
     * @return
     */
    public String guardarCliente() {
        System.out.println("aqui");
        System.out.println(newCliente.getFechNac().toString());
        System.out.println("hire");
        try {
            System.out.println(newCliente.toString() + ">>>>");
            clienteON.guardarCliente(newCliente, cuenta);
            init();
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Busca los clientes por el codigo que se le pase como parametro desde la
     * vista y se pasa al objeto de negocio.
     *
     * @return
     */
    public String buscaClientees() {
        System.out.println(textoBuscar);
        try {
            listaClientees = clienteON.listaClienteesCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Busca a los clientes por id y pasa el parametro a los objetos de negocio
     *
     * @param id
     * @return
     */
    public String buscaClienteID(String id) {
        try {
            auxCliente = clienteON.buscarCliente(id);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Actualiza el cliente hace uso de la logica de negocio
     *
     * @return
     */
    public String actualizarCliente() {
        clienteON.actualizarCliente(auxCliente);
        init();
        System.out.println("actualizado");
        return null;
    }

    /**
     * *
     * Elimina el cliente, recibe al objeto cliente para poder actualizar los
     * datos.
     *
     * @return
     */
    public String eliminarCliente() {
        auxCliente.setEliminado(true);
        auxCliente.getCuentaList().get(0).setEliminado(true);
        clienteON.actualizarCliente(auxCliente);
        init();
        System.out.println("Eliminado");
        return null;
    }

    /**
     * Metodo de prueba que carga el resumen del cliente, buscado por la cedula,
     * imprime lo que retorna.
     *
     * @return
     */
    public String loadResumenCliente() {
        listaClientes = resumenCuentaON.getClienteByCedelua("0106");
        for (Cliente cliente : listaClientes) {
            loadResumenCuenta(cliente.getCuentaList().get(0).getNumero());
        }
        return null;
    }

    /**
     * Metodo de prueba que carga el resumen del cliente recibe como parametro
     * al numero
     *
     * @param numero
     * @return
     */
    public String loadResumenCuenta(String numero) {
        listaCuentasClientes = resumenCuentaON.getResumenCuentaCliente(numero);
        return null;
    }

    /**
     * Metodo de prueba que carga el resumen del cliente Carga el estado de la
     * cuenta
     *
     * @return
     */
    public String loadEstadoCta() {
        listaEstadosCta = resumenCuentaON.getEstadoCtaByMes(tipo, fechaInicio, fechaFin);
        System.out.println("llega los paametros >>>>> " + tipo + " fehcIni: " + fechaInicio + " fechaFin: " + fechaFin);
        for (Transaciones t : listaEstadosCta) {
            System.out.println("fechas--" + t.getFecha());
        }
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

    public List<String> getEstadocivilist() {
        return estadocivilist;
    }

    public void setEstadocivilist(List<String> estadocivilist) {
        this.estadocivilist = estadocivilist;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public List<Transaciones> getListaCuentasClientes() {
        return listaCuentasClientes;
    }

    public void setListaCuentasClientes(List<Transaciones> listaCuentasClientes) {
        this.listaCuentasClientes = listaCuentasClientes;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Transaciones> getListaEstadosCta() {
        return listaEstadosCta;
    }

    public void setListaEstadosCta(List<Transaciones> listaEstadosCta) {
        this.listaEstadosCta = listaEstadosCta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
