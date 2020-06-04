/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.ClienteDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vinic
 */
@Stateless
public class ClienteON {

    @Inject
    ClienteDAO clienteDAO;
    
    

    public ClienteON() {
    }
    
    public String numeroCuenta(){
        return "cuenta";
    }
    
    public void actualizarCliente(Cliente cliente){
        try {
            clienteDAO.update(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Cliente buscarCliente(String id){
        try {
            return clienteDAO.findByID(id);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Cliente> listaClientees(){
        try {
            return clienteDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public List<Cliente> listaClienteesCodigo(String codigo){
        try {
            return clienteDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean guardarCliente(Cliente cliente) throws Exception {
        if (validarCedula(cliente.getCedula())) {
            try {
            	CorreoON c = new CorreoON();
            	String contrasena=c.contrasenaAleatoria();
                cliente.setContracenia(contrasena);
                cliente.setActivo(true);
                cliente.setEliminado(false);
                boolean respuesta=clienteDAO.insert(cliente);
                
                String correo=cliente.getCorreo();
                if (respuesta==true) {
					c.sendAsHtml(correo, "Bienvenido a SimonBank®", "<h2>Estimado cliente usted puede ingresar con: </h2><p>Sus Datos son : </p>Su usuario es: "+cliente.getCedula()+" Su Contrasena: "+cliente.getContracenia()+""
							+ " <h4> RECUERDE ESTIMADO CLIENTE, CAMBIAR LA CONTRASENA POR SU SEGURIDAD.</h4><br> <h4>Cuenca-Ecuador</h4>");
				}else {
					c.sendAsHtml(correo, "No se registro", "Datos incompletos");
				}
                
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Cedula Incorrecta");
        }

        return true;
    }
    
    

    public boolean validarCedula(String ced) {
        boolean verdadera = false;
        int num = 0;
        int ope = 0;
        int suma = 0;
        for (int cont = 0; cont < ced.length(); cont++) {
            num = Integer.valueOf(ced.substring(cont, cont + 1));
            if (cont == ced.length() - 1) {
                break;
            }
            if (cont % 2 != 0 && cont > 0) {
                suma = suma + num;
            } else {
                ope = num * 2;
                if (ope > 9) {
                    ope = ope - 9;
                }
                suma = suma + ope;
            }
        }
        if (suma != 0) {
            suma = suma % 10;
            if (suma == 0) {
                if (suma == num) {
                    verdadera = true;
                }
            } else {
                ope = 10 - suma;
                if (ope == num) {
                    verdadera = true;
                }
            }
        } else {
            verdadera = false;
        }
        return verdadera;
    }

    public void enviarCorreo() {
    	
    }
}
