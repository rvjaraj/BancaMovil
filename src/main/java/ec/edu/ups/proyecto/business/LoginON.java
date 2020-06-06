/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.ClienteDAO;
import ec.edu.ups.proyecto.dao.TrabajadorDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
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
public class LoginON {
    
    @Inject
    TrabajadorDAO trabajadorDAO;
    
    @Inject
    ClienteDAO clienteDAO;
    
    
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
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public Cliente loginClie(String cedula, String contrasenia){
        try {
            Cliente cliente= clienteDAO.findByCedula(cedula);
            if(cliente!=null){
                
                if(cliente.getContracenia().equals(contrasenia)){
                    
                    //Enviar correo de login con ip
                    return cliente;
                }else{
                    //enviar correo de login erroneo, alguien intenta usar su cedula para entrar
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public void actualizarTrabajador(Trabajador trabajador){
        try {
            trabajadorDAO.update(trabajador);
            //Menseje de que la contra cambiada
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarCliente(Cliente cliente){
        try {
            clienteDAO.update(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
