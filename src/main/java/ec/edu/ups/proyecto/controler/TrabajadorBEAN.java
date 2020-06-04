/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.TrabajadorON;
import ec.edu.ups.proyecto.emtitis.Trabajador;
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
public class TrabajadorBEAN {

    private Trabajador newTrabajador;
    private Trabajador auxTrabajador;
    private List<Trabajador> listaTrabajadores;
    private ArrayList<String> listaOpc;
    private String textoBuscar;

    @Inject
    private TrabajadorON trabajadorON;

    @PostConstruct
    public void init() {
        newTrabajador = new Trabajador();
        listaOpc = new ArrayList<>();
        listaOpc.add("Administrador");
        listaOpc.add("Secretaria");
        listaOpc.add("Cajero");
        listaOpc.add("Jefe Credito");
        listaTrabajadores = trabajadorON.listaTrabajadores();
    }

    public String guardarTrabajador() {
        try {
            trabajadorON.guardarFactura(newTrabajador);
            init();
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaTrabajadores() {
        System.out.println(textoBuscar);
        try {
            listaTrabajadores = trabajadorON.listaTrabajadoresCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaTrabajadorID(String id) {
        System.out.println(id+ ">>>>>>>>>>");
        try {
             auxTrabajador = trabajadorON.buscarTrabajador(id);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // -------------------> 
    public Trabajador getNewTrabajador() {
        return newTrabajador;
    }

    public void setNewTrabajador(Trabajador newTrabajador) {
        this.newTrabajador = newTrabajador;
    }

    public TrabajadorON getTrabajadorON() {
        return trabajadorON;
    }

    public void setTrabajadorON(TrabajadorON trabajadorON) {
        this.trabajadorON = trabajadorON;
    }

    public ArrayList<String> getListaOpc() {
        return listaOpc;
    }

    public void setListaOpc(ArrayList<String> listaOpc) {
        this.listaOpc = listaOpc;
    }

    public List<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Trabajador getAuxTrabajador() {
        return auxTrabajador;
    }

    public void setAuxTrabajador(Trabajador auxTrabajador) {
        this.auxTrabajador = auxTrabajador;
    }
    
    
}
