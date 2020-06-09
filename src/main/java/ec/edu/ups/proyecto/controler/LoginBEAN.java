/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.LoginON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fanny
 */
@ManagedBean
@ViewScoped
public class LoginBEAN {

    private String cedula = "";
    private String contra = "";
    private String tipo;
    private Trabajador trabajador;
    private Cliente cliente;
    private String contra1 = "";
    private String contra2 = "";
    private String mesaje ;
    private String tipe = "";

    @Inject
    private LoginON loginON;

    public LoginBEAN() {
    }

    @PostConstruct
    public void init() {
        cedula = "";
        contra = "";
        action();
        tipe = tipo;

    }

    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        String param = request.getParameter("tipo");
        tipo = param;
    }
    /**
     * Se utiliza el dato que traemos por la url 
     * para poder cambiar la contrasena
     * Metodo que valida si el usuario esta correcto o no
     * @return
     */
    public String loginUser() {
        System.out.println(" tipo: " + tipe);
        if (tipe != null) {
            if (tipe.equals("trabajador")) {
                trabajador = loginON.loginTra(cedula, contra);
                if (trabajador != null) {
                    if (trabajador.getActivo()) {
                        System.out.println("Cambiar Contra");
                        return null;
                    } else {
                        System.out.println("No necesita cambiar contr");
                        if (trabajador.getRol().equals("Administrador")) {
                            return "trabajador?faces-redirect=true&cedula=" + trabajador.getCedula();
                        } else if (trabajador.getRol().equals("Cajero")) {
                            return "cajero?faces-redirect=true&cedula=" + trabajador.getCedula();
                        }

                    }
                } else {
                    System.out.println("No ecuentra trabajador");
                    return "loginc?faces-redirect=true&tipo=trabajador&msj=cedula o contraseña incorrecta";
                }

            } else if (tipe.equals("cliente")) {
                cliente = loginON.loginClie(cedula, contra);
                if (cliente != null) {
                    if (cliente.getActivo()) {
                        System.out.println("Cambiar Contra");
                        return null;
                    } else {
                        System.out.println("No necesita cambiar contr");
                        return "usuario?faces-redirect=true&cedula=" + cliente.getCedula();
                    }
                } else {
                    System.out.println("No ecuentra cleinte");
                    return "loginc?faces-redirect=true&tipo=cliente&msj=cedula o contraseña incorrecta";
                }
            } else {
                System.out.println("no hay tipo");
                return "loginc?faces-redirect=true&tipo=trabajador&msj=Erro: Verificar Tipo";
            }
        }else{
            return "loginc?faces-redirect=true&tipo=trabajador&msj=Erro: Verificar Tipo";
        }
        return "loginc?faces-redirect=true&tipo=trabajador&msj=Erro: Verificar Tipo";

    }
    /**
     * Metodo que permite actualizar la contrasena 
     * recibe parametros mediante la url
     * @return
     */
    public String actualizarCotra() {
        if (tipe.equals("trabajador")) {
            if (contra1.equals(contra2)) {
                System.out.println("");
                trabajador.setContracenia(contra1);
                trabajador.setActivo(false);
                loginON.actualizarTrabajador(trabajador);
                System.out.println("Contra de trabajador actualizada");
                return "loginc?faces-redirect=true&tipo=trabajador&msj=contraseña actualizada";
            } else {
                mesaje = "las contraseñas no coinciden";
            }
        } else if (tipe.equals("cliente")) {
            if (contra1.equals(contra2)) {
                System.out.println("");
                cliente.setContracenia(contra1);
                cliente.setActivo(false);
                loginON.actualizarCliente(cliente);
                System.out.println("Contra de cliente actualizada");
                return "loginc?faces-redirect=true&tipo=cliente&msj=contraseña actualizada";
            } else {
                mesaje = "las contraseñas no coinciden";
            }
        }
        return "index";
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContra1() {
        return contra1;
    }

    public void setContra1(String contra1) {
        this.contra1 = contra1;
    }

    public String getContra2() {
        return contra2;
    }

    public void setContra2(String contra2) {
        this.contra2 = contra2;
    }

    public String getMesaje() {
        return mesaje;
    }

    public void setMesaje(String mesaje) {
        this.mesaje = mesaje;
    }

}
