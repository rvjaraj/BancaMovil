/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.AloginON;
import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author fanny
 */
@ManagedBean
@ViewScoped
public class AloginBEAN {

    private String cedula;
    private String contra;

    @Inject
    private AloginON aloginON;

    public AloginBEAN() {
    }

    @PostConstruct
    public void init() {
        cedula = "123";
        contra = "0225";

    }

    public String guardarLogin() {
        System.out.println("cedula" + cedula + "contra" + contra);
        Trabajador trabajador = aloginON.loginTra(cedula, contra);

        if (trabajador != null) {
            System.out.println("si esxite"+trabajador.getRol());
            if(trabajador.getRol().toString().equals("Administrador")){
                System.out.println("aquiii");
               return "trabajador";
               
            }else{
                System.out.println("Es secre");
                return "trabajador2"; 
            } 
        }
        return null;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

}
