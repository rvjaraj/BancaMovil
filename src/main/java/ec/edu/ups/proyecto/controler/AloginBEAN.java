/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.AloginON;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fanny
 */
public class AloginBEAN {
    
    private Alogin login;
    private Trabajador cliente;
  
 

    @Inject
    private AloginON loginON;

    @PostConstruct
    public void init() {
        cliente = new Trabajador();

    }
    
    public String guardarLogin(){
        //loginON.guardarLogin(login)
//        if(cliente.getRol() = "Administrador"){
//            
//        }
             
        return null;
    }

    
}
