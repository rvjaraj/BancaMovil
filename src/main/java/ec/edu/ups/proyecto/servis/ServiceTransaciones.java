/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.servis;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.TransaccionesON;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import javax.inject.Inject;
import javax.jws.WebService;

/**
 *
 * @author Vinicio
 */
@WebService
public class ServiceTransaciones {
    @Inject
    TransaccionesON transaccionesON;
    
    @Inject
    ClienteON clienteON;
    
    public boolean deposito(double valor, String cuenta){
        
       return false;
    }
        
}
