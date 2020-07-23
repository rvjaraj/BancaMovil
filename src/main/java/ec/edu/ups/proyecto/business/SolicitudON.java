/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.ClienteDAO;
import ec.edu.ups.proyecto.dao.SolicitudDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import ec.edu.ups.proyecto.emtitis.SolicitudSRV;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import java.util.ArrayList;
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
public class SolicitudON {

    @Inject
    ClienteDAO clienteDAO;

    @Inject
    SolicitudDAO solicitudDAO;

    public SolicitudON() {
    }

    public Solicitud datosIniciales(Cliente cliente) {
        Solicitud solicitud = new Solicitud();
        solicitud.setEstado("revision");
        solicitud.setTipocliente("por definir");
        solicitud.setElimado(false);
        solicitud.setCliente(cliente);
        solicitud.setTasadepagos(5);
        solicitud.setEstadocivil(cliente.getEstadocivil());
        Date fechaAct = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay());
        solicitud.setEdad(fechaAct.getYear() - cliente.getFechNac().getYear());
        solicitud.setSaldocuenta(cliente.getCuentaList().get(0).getSaldo() + "");

        //Calular valores 
        List<Solicitud> listSol = this.listarSalicitudesCliente(cliente.getCedula());
        if (listSol.isEmpty()) {
            solicitud.setNumerocreditos(0);
            solicitud.setHistorial("No cuenta con creditos");
        } else {
            solicitud.setNumerocreditos(listSol.size());
            solicitud.setHistorial("Validar como van los creditos");
        }
        return solicitud;
    }

    public void guardarSolicuitud(Solicitud solicitud) {
        try {
            solicitudDAO.insert(solicitud);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Solicitud> listarSalicitudes() {
        try {
            return solicitudDAO.findAll();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<Solicitud> listarSalicitudesCliente(String cedula) {
        try {
            return solicitudDAO.findByCliente(cedula);
        } catch (Exception e) {
            //System.out.println("Error: " + e.getMessage());
            //Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<SolicitudSRV> enviarDataSet() {
        List<Solicitud> listaSolicitudes = this.listarSalicitudes();
        List<SolicitudSRV> lista = new ArrayList<>();
        for (Solicitud solicitud : listaSolicitudes) {
            SolicitudSRV soliSrv = new SolicitudSRV();
            soliSrv.setDni(this.srvDNI(solicitud));
            soliSrv.setPlazomesescredito(this.srvPlazomesescredito(solicitud));
            soliSrv.setHistorialcredito(this.srvHistorialcredito(solicitud));
            soliSrv.setPropositocredito(this.srvPropositocredito(solicitud));
            soliSrv.setMontocredito(this.srvMontocredito(solicitud));
            soliSrv.setSaldocuentaahorros(this.srvSaldocuentaahorros(solicitud));
            soliSrv.setTiempoempleo(this.srvTiempoempleo(solicitud));
            soliSrv.setTasapago(this.srvTasapago(solicitud));
            soliSrv.setEstadocivilysexo(this.srvEstadocivilysexo(solicitud));
            soliSrv.setGarante(this.srvGarante(solicitud));
            soliSrv.setAvaluovivienda(this.srvAvaluovivienda(solicitud));
            soliSrv.setActivos(this.srvActivos(solicitud));
            soliSrv.setEdad(this.srvEdad(solicitud));
            soliSrv.setVivienda(this.srvVivienda(solicitud));
            soliSrv.setCantidadcreditosexistentes(this.srvCantidadcreditosexistentes(solicitud));
            soliSrv.setEmpleo(this.srvEmpleo(solicitud));
            soliSrv.setTrabajadorextranjero(this.srvTrabajadorextranjero(solicitud));
            soliSrv.setTipocliente("0");
            lista.add(soliSrv);

        }
        return lista;
    }

    //Todo los calculos para pasara a dataset*
    public String srvTrabajadorextranjero(Solicitud s) {
        switch (s.getTrebajadorextrangero()) {
            case "NO":
                return "A201";
            case "SI":
                return "A202";
        }
        return "A203";
    }

    public String srvEmpleo(Solicitud s) {
        switch (s.getEmpleo()) {
            case "Desempleado":
                return "A171";
            case "Jubilado":
                return "A172";
            case "Empleado":
                return "A173";
            case "Autonomo":
                return "A174";
        }
        return "A175";
    }

    public String srvCantidadcreditosexistentes(Solicitud s) {
        return (int) s.getNumerocreditos() + "";
    }

    public String srvVivienda(Solicitud s) {
        switch (s.getTipovivienda()) {
            case "Gratis":
                return "A151";
            case "Alquiler":
                return "A152";
            case "Propio":
                return "A153";
        }
        return "A154";
    }

    public String srvEdad(Solicitud s) {
        return (int) s.getEdad() + "";
    }

    public String srvActivos(Solicitud s) {
        switch (s.getActivos()) {
            case "Bienes inmuebles":
                return "A121";
            case "Seguro de vida":
                return "A122";
            case "Automovil":
                return "A123";
            case "Sin propiedad":
                return "A124";
            case "Plan de construccion":
                return "A125";
        }
        return "A35";
    }

    public String srvAvaluovivienda(Solicitud s) {
        return (int) s.getAvaluovivienda() + "";
    }

    public String srvDNI(Solicitud s) {
        return s.getCliente().getCedula();
    }

    public String srvPlazomesescredito(Solicitud s) {
        return s.getPlazo() + "";
    }

    public String srvHistorialcredito(Solicitud s) {
        switch (s.getHistorial()) {
            case "No se tomaron créditos":
                return "A30";
            case "Creditos pagados debidamente":
                return "A31";
            case "Creditos existentes pagados debidamente":
                return "A32";
            case "Retraso en pagos":
                return "A33";
            case "Cuenta critica":
                return "A34";

        }
        return "A35";
    }

    public String srvPropositocredito(Solicitud s) {
        switch (s.getProposito()) {
            case "Inmuebles | casa, finca, etc |":
                return "A40";
            case "Automovil":
                return "A41";
            case "Muebles | Equipamiento":
                return "A42";
            case "Tecnologia":
                return "A43";
            case "Electrodomesticos":
                return "A44";
            case "Reparaciones":
                return "A45";
            case "Educacion":
                return "A46";
            case "Vacaciones":
                return "A47";
            case "Capacitacion":
                return "A48";
            case "Negocios":
                return "A49";
            case "Otros":
                return "A410";
        }
        return "A411";
    }

    public String srvMontocredito(Solicitud s) {
        return ((int) s.getCantidad()) + "";
    }

    public String srvSaldocuentaahorros(Solicitud s) {

        Double saldo = Double.parseDouble(s.getSaldocuenta());
        if (saldo < 500) {
            return "A61";
        } else if (saldo < 1000) {
            return "A62";
        } else if (saldo < 1500) {
            return "A63";
        } else if (saldo >= 1500) {
            return "A64";
        }
        return "A65";
    }

    public String srvTiempoempleo(Solicitud s) {
        switch (s.getTiempoempleo()) {
            case "Desempleado":
                return "A71";
            case "Trabajando: Menos de un año":
                return "A72";
            case "Trabajando | 1 - 4 años":
                return "A73";
            case "Trabajando | 4- 7 años":
                return "A74";
            case "Trabajando | Mas de 7 años":
                return "A75";
        }
        return "A76";
    }

    public String srvTasapago(Solicitud s) {
        return s.getTasadepagos() + "";
    }

    public String srvEstadocivilysexo(Solicitud s) {
        switch (s.getEstadocivil()) {
            case "Divorciado":
                return "A91";
            case "Divorciada":
                return "A92";
            case "Soltero":
                return "A93";
            case "Soltera":
                return "A94";
            case "Casado":
                return "A95";
            case "Casada":
                return "A96";
        }
        return "A97";
    }

    public String srvGarante(Solicitud s) {
        Cliente c;
        try {
            c = clienteDAO.findByCedula(s.getGarante());
            if (c != null) {
                if (this.listarSalicitudesCliente(s.getGarante()).isEmpty()) {
                    return "102";
                } else {
                    return "103";
                }
            } else {
                return "A101";
            }
        } catch (Exception ex) {
            return "A101";
        }
    }
}