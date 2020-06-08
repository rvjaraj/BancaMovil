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

    public void actualizarTrabajador(Trabajador trabajador) {
        try {
            trabajadorDAO.update(trabajador);
            enviarCorreoUpdate(trabajador.getCorreo());
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarCliente(Cliente cliente) {
        try {
            clienteDAO.update(cliente);
            enviarCorreoUpdate(cliente.getCorreo());
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    
    public List<Alogin> listaLogin(String cedula){
        try {
            System.out.println("aqui");
            return aloginDAO.findAllbyCedula(cedula);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void enviarCorreoIngreso( String correo, boolean acceso, String ip) {
        //El correo viene en la variable correo
        //(acceso = true ) >> enviar el correo con login correcto si es acceso es true
        //(acceso = false ) >> enviar el correo con: se registra un intento erroneo de acceso con su cuenta 
        // Ip >> lugar desde donde intentan usar
    }
    
    public void enviarCorreoUpdate(String correo){
        //enviar un correo diciendo se a autualizado la contra
        //No importa si es cliente o si es trabajdor
    }
    

}
