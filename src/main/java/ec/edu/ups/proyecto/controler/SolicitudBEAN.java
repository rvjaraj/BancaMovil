/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.SolicitudON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import java.util.Arrays;
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
public class SolicitudBEAN {

    private String cedula = "";
    private Cliente cliente;
    private Solicitud solicitud;
    private List<String> listaProposito;
    private List<String> listaTiempoEmpleo;
    private List<String> listaActivos;
    private List<String> listaVivienda;
    private List<String> listaEmpleo;
    private List<String> listaExtrangero;
    @Inject
    private ClienteON clienteON;

    @Inject
    private SolicitudON solicitudON;

    public SolicitudBEAN() {
    }

    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        cedula = request.getParameter("cedula");

        if (cedula != null) {
            cliente = clienteON.buscarClienteCedula(cedula);
            solicitud = solicitudON.datosIniciales(cliente);
        }
    }

    @PostConstruct
    public void init() {
        listaProposito = Arrays.asList("Inmuebles | casa, finca, etc |", "Muebles | Equipamiento",
                "Automovil", "Electrodomésticos", "Tecnología", "Reparaciones", "Educación", "Capacitación", "Negocios", "Otros");
        listaTiempoEmpleo = Arrays.asList("Desempleado", "Trabajando: Menos de un año", "Trabajando | 1 - 4 años", "Trabajando | 4- 7 años", "Trabajando | Mas de 7 años");
        listaActivos = Arrays.asList("Bienes inmuebles", "Seguro de vida", "Automovil", "Plan de construcción", "Sin propiedad");
        listaVivienda = Arrays.asList("Gratis", "Alquiler", "Propio");
        listaEmpleo = Arrays.asList("Desempleado", "Jubilado", "Empleado", "Autónomo");
        listaExtrangero = Arrays.asList("SI", "NO");
        cedula = "";
        action();

    }

    public String guardarSolicitud() {
        System.out.println(solicitud.toString());
        solicitudON.guardarTransaciones(solicitud);
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

    public List<String> getListaProposito() {
        return listaProposito;
    }

    public void setListaProposito(List<String> listaProposito) {
        this.listaProposito = listaProposito;
    }

    public List<String> getListaTiempoEmpleo() {
        return listaTiempoEmpleo;
    }

    public void setListaTiempoEmpleo(List<String> listaEmpleo) {
        this.listaTiempoEmpleo = listaEmpleo;
    }

    public List<String> getListaActivos() {
        return listaActivos;
    }

    public void setListaActivos(List<String> listaActivos) {
        this.listaActivos = listaActivos;
    }

    public List<String> getListaVivienda() {
        return listaVivienda;
    }

    public void setListaVivienda(List<String> listaVivienda) {
        this.listaVivienda = listaVivienda;
    }

    public List<String> getListaEmpleo() {
        return listaEmpleo;
    }

    public void setListaEmpleo(List<String> listaEmpleo) {
        this.listaEmpleo = listaEmpleo;
    }

    public List<String> getListaExtrangero() {
        return listaExtrangero;
    }

    public void setListaExtrangero(List<String> listaExtrangero) {
        this.listaExtrangero = listaExtrangero;
    }
    
    
    
    
}
