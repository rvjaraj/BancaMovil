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
    /**
     * Se actualiza datos del trabajador, esta logica es consumida desde 
     * la interfaz web
     * @param trabajador
     */
    public void actualizarTrabajador(Trabajador trabajador){
        try {
            trabajadorDAO.update(trabajador);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Permite buscar el trabajador mediante el id 
     * @param id
     * @return
     */
    public Trabajador buscarTrabajador(String id){
        try {
            return trabajadorDAO.findByID(id);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Se manda a buscar la lista del trabajador 
     * @return
     */
    public List<Trabajador> listaTrabajadores(){
        try {
            return trabajadorDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Permite buscar por codigo y rel resultado se almacena en una lista de trabajador
     * @param codigo
     * @return
     */
    public List<Trabajador> listaTrabajadoresCodigo(String codigo){
        try {
            return trabajadorDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Permite resgistrar al trabajador y enviarle un usuario y la contrasena 
     * para que pueda acceder a su cuenta 
     * @param trabajador
     * @return
     * @throws Exception
     */
    public boolean guardarTrabajador(Trabajador trabajador) throws Exception {
        if (validarCedula(trabajador.getCedula())) {
            try {
            	CorreoON c = new CorreoON();
            	String contrasena=c.contrasenaAleatoria();
                trabajador.setContracenia(contrasena);
                trabajador.setActivo(true);
                trabajador.setEliminado(false);
                boolean respuesta=trabajadorDAO.insert(trabajador);
                
                String correo=trabajador.getCorreo();
                if (respuesta==true) {
					c.sendAsHtml(correo, "Bienvenido a SimonBank®", "<h2>Estimado cliente usted puede ingresar con: </h2><p>Sus Datos son : </p>Su usuario es: "+trabajador.getCedula()+" Su Contrasena: "+trabajador.getContracenia()+""
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
    
    /**
     * Este metodo nos permite validar la cedula e
     * en caso de ser valida devuelve un true
     * @param ced
     * @return
     */

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
