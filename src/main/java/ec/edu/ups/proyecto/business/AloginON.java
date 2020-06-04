/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.AloginDAO;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Trabajador;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fanny
 */

@Stateless
public class AloginON {
    @Inject
    AloginDAO loginDAO;
    
        

    public AloginON() {
    }
  

    public boolean guardarLogin(String correo, String contrasenia) throws Exception {
       Trabajador t = new Trabajador();
        try {
            loginDAO.insert(t);
        } catch (Exception e) {
            
        }
        
   return true;

}
}
