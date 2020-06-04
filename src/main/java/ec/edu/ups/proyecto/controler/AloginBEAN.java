/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.AloginON;
import ec.edu.ups.proyecto.emtitis.Alogin;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fanny
 */
public class AloginBEAN {
    
    private Alogin login;
    private List<Alogin> listaTrabajadores;
 

    @Inject
    private AloginON loginON;

    @PostConstruct
    public void init() {
//        newTrabajador = new Trabajador();
//        newTrabajador.setCedula("0105452171");
//        newTrabajador.setNombres("Ricaro Vinicio");
//        newTrabajador.setApellido("Jara Jara");
//        newTrabajador.setTelefono("0990550717");
//        newTrabajador.setDireccion("Cuenca");
//        newTrabajador.setCorreo("vinicio1004@hotmailc.com");
//        newTrabajador.setSueldo(1200);
//        newTrabajador.setRol("Administrador");
//
//        listaOpc = new ArrayList<>();
//        listaOpc.add("Administrador");
//        listaOpc.add("Secretaria");
//        listaOpc.add("Cajero");
        
//        listaTrabajadores = trabajadorON.listaTrabajadores();
    }

//    public String guardarLogin() {
//        try {
//            loginON.guardarFactura(newTrabajador);
//        } catch (Exception ex) {
//            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    // -------------------> 
//    public Trabajador getNewTrabajador() {
//        return newTrabajador;
//    }
//
//    public void setNewTrabajador(Trabajador newTrabajador) {
//        this.newTrabajador = newTrabajador;
//    }
//
//    public TrabajadorON getTrabajadorON() {
//        return trabajadorON;
//    }
//
//    public void setTrabajadorON(TrabajadorON trabajadorON) {
//        this.trabajadorON = trabajadorON;
//    }
//
//    public ArrayList<String> getListaOpc() {
//        return listaOpc;
//    }
//
//    public void setListaOpc(ArrayList<String> listaOpc) {
//        this.listaOpc = listaOpc;
//    }
//
//    public List<Trabajador> getListaTrabajadores() {
//        return listaTrabajadores;
//    }
//
//    public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
//        this.listaTrabajadores = listaTrabajadores;
//    }
    
}
