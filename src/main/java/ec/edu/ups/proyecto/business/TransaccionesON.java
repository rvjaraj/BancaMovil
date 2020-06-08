/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.TransaccionesDAO;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import java.net.InetAddress;
import java.util.Date;
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
public class TransaccionesON {

    @Inject
    TransaccionesDAO trasacionesDAO;

    public TransaccionesON() {
    }

    public void guardarTransaciones(Transaciones transaciones) {
        try {
            trasacionesDAO.insert(transaciones);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void actualizarTransaciones(Transaciones transaciones) {
        try {
            trasacionesDAO.update(transaciones);
        } catch (Exception ex) {
            Logger.getLogger(TransaccionesON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Transaciones buscarTransaciones(String id) {
        try {
            return trasacionesDAO.findByID(id);
        } catch (Exception ex) {
            Logger.getLogger(TransaccionesON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Transaciones> listaTransacioneses() {
        try {
            return trasacionesDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(TransaccionesON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public List<Transaciones> listaTransacionesCedula(String cedula){
        try {
            return trasacionesDAO.findAllbyCedula(cedula);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Transaciones> listaTransacionesesCodigo(String codigo) {
        try {
            return trasacionesDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(TransaccionesON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
