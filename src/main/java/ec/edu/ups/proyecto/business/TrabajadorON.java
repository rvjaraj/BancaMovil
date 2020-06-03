/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.TrabajadorDAO;
import ec.edu.ups.proyecto.emtitis.Trabajador;
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
public class TrabajadorON {

    @Inject
    TrabajadorDAO trabajadorDAO;
    
    

    public TrabajadorON() {
    }
    
    public List<Trabajador> listaTrabajadores(){
        try {
            return trabajadorDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public List<Trabajador> listaTrabajadoresCodigo(String codigo){
        try {
            return trabajadorDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean guardarFactura(Trabajador trabajador) throws Exception {
        if (validarCedula(trabajador.getCedula())) {
            try {
                trabajador.setContracenia("1234");
                trabajador.setActivo(true);
                trabajador.setEliminado(false);
                trabajadorDAO.insert(trabajador);
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

}
