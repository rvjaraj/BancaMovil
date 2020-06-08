/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.AloginDAO;
import ec.edu.ups.proyecto.dao.ClienteDAO;
import ec.edu.ups.proyecto.dao.TrabajadorDAO;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

/**
 *
 * @author fanny
 */
@Stateless
public class LoginON {

    @Inject
    AloginDAO aloginDAO;

    @Inject
    TrabajadorDAO trabajadorDAO;

    @Inject
    ClienteDAO clienteDAO;
    
    /**
     * Se realiza las validaciones como trabajador, mediante la cedula y la contrasena
     * 
     * @param cedula
     * @param contrasenia
     * @return
     */
    public Trabajador loginTra(String cedula, String contrasenia) {
        try {
            Trabajador trabajador = trabajadorDAO.findByCedula(cedula);
            if (trabajador != null) {

                if (trabajador.getContracenia().equals(contrasenia)) {
                    enviarCorreoIngreso(trabajador.getCorreo(), true, obtenerIp());
                    //Aditoria de login de trabajdor >> true
                    return trabajador;
                } else {
                    //Aditoria de login de trabajdor >> false
                    enviarCorreoIngreso(trabajador.getCorreo(), true, obtenerIp());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    /**
     * Se valida al cliente desde la interfaz de login, se realiza un envio de correo 
     * con la direccion ip desde donde se accedio.
     * @param cedula
     * @param contrasenia
     * @return
     */
    public Cliente loginClie(String cedula, String contrasenia) {
        try {
            Cliente cliente = clienteDAO.findByCedula(cedula);
            if (cliente != null) {

                if (cliente.getContracenia().equals(contrasenia)) {
                    guardarLogin(cliente, true);
                    enviarCorreoIngreso(cliente.getCorreo(), true, obtenerIp());
                    return cliente;
                } else {
                    guardarLogin(cliente, false);
                    enviarCorreoIngreso(cliente.getCorreo(), false, obtenerIp());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    /**
     * Se actualiza datos del trabajador, se realiza un envio de correo 
     * indicando que fue actualizado.
     * @param cedula
     * @param contrasenia
     * @return
     */
    public void actualizarTrabajador(Trabajador trabajador) {
        try {
            trabajadorDAO.update(trabajador);
            enviarCorreoUpdate(trabajador.getCorreo());
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Se actualiza datos del cliente, se realiza un envio de correo 
     * indicando que fue actualizado.
     * @param cliente
     */
    public void actualizarCliente(Cliente cliente) {
        try {
            clienteDAO.update(cliente);
            enviarCorreoUpdate(cliente.getCorreo());
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Se registra el usuario y tambien el acceso con la ip , la fecha que trato de acceder
     * @param cliente
     * @param acceso
     */
    public void guardarLogin(Cliente cliente, boolean acceso) {
        Alogin login = new Alogin();
        try {
            InetAddress ip;
            login.setAcceso(acceso);
            login.setCliente(cliente);
            login.setFecha(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay()));
            login.setIp(obtenerIp());
            aloginDAO.insert(login);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Este metodo nos ayuda a obtener la ip para ser utilizada en 
     * otros metodos 
     * @return
     */
    public String obtenerIp() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
            return "";
        }
    }
    /**
     * Busca login mediante la cedula 
     * @param cedula
     * @return
     */
    public List<Alogin> listaLogin(String cedula){
        try {
            System.out.println("aqui");
            return aloginDAO.findAllbyCedula(cedula);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Permite enviar un correo cuando se accede a la cuenta, se adjunta al correo  la direccion ip
     * Permite enviar un correo cuando se intenta  ingresar a  la cuenta y falla, se adjunta al correo  la direccion ip
     * @param correo
     * @param acceso
     * @param ip
     */
    public void enviarCorreoIngreso( String correo, boolean acceso, String ip) {
           	try {
           		CorreoON c = new CorreoON();
           		if (acceso==true) {
           			
    				c.sendAsHtml(correo, "Inicio Sesion en SimonBank®", "Usted ha ingresado a su cuenta desde La direccion ip : <p>IP : </p>"+ip);
				} else {
					c.sendAsHtml(correo, "Registro Sesion Fallido en SimonBank®", "Desde La direccion ip : <p>IP : </p>"+ip);

				}
           		
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    	//El correo viene en la variable correo
        //(acceso = true ) >> enviar el correo con login correcto si es acceso es true
        //(acceso = false ) >> enviar el correo con: se registra un intento erroneo de acceso con su cuenta 
        // Ip >> lugar desde donde intentan usar
    }
    
    /**
     * Envia un correo indicando que la contrasena se actualizo satisfactoriamente
     * Este metodo es consumido en metodos anteriores.
     * @param correo
     */
    public void enviarCorreoUpdate(String correo){
    			CorreoON c = new CorreoON();
    			try {
					c.sendAsHtml(correo, "Constrasena Actualizada", "<p>Se actualizo su contrasena </p>");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        //enviar un correo diciendo se a autualizado la contra
        //No importa si es cliente o si es trabajdor
    }
    

}
