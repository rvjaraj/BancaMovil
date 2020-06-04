/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.emtitis.Alogin;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fanny
 */

@Stateless
public class AloginON {
    @Inject
    AloginON loginDAO;
    
    

    public AloginON() {
    }
   

    public boolean guardarLogin(Alogin login) throws Exception {
        

        return true;
    }

  
    
}
