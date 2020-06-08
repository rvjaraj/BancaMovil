/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.TrabajadorDAO;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fanny
 */

@Stateless
public class AloginON {
    
    @Inject
    TrabajadorDAO trabajadorDAO;
    
    /**
     * Recibe como parametros cedula y la contrasena para el inicio de sesion
     * Si la contrasena es correcta, permitira el ingreso al sistema  
     * @param cedula
     * @param contrasenia
     * @return
     */
    public Trabajador loginTra(String cedula, String contrasenia){
        try {
            Trabajador trabajador= trabajadorDAO.findByCedula(cedula);
            if(trabajador!=null){
                
                if(trabajador.getContracenia().equals(contrasenia)){
                    
                    //Enviar correo de login con ip
                    return trabajador;
                }else{
                    //enviar correo de login erroneo, alguien intenta usar su cedula para entrar
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AloginON.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    
    
}
