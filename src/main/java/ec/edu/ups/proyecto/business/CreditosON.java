/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.CreditoDAO;
import ec.edu.ups.proyecto.emtitis.Amortizacion;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Credito;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

/**
 *
 * @author Vinicio
 */
@Stateless
public class CreditosON {

    @Inject
    CreditoDAO creditoDAO;

    @Inject
    CorreoON correoON;
    
    public List<Credito> listarCreditosCliente(String cedula){
        try {
            return creditoDAO.findByCliente(cedula);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(CreditosON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void generarCredito(Solicitud solicitud) {
        try {
            Credito c = new Credito();
            c.setPlazo(solicitud.getPlazo());
            c.setProposito(solicitud.getProposito());
            c.setCantidad(solicitud.getCantidad());
            c.setEstado("POR PAGAR");
            c.setCuenta(solicitud.getCliente().getCuentaList().get(0));
            c.setElimado(false);
            //Tabla amortizacion
            List<Amortizacion> lista = new ArrayList<>();
            double cuotas = c.getCantidad() / c.getPlazo();

            for (int i = 1; i < solicitud.getPlazo() + 1; i++) {
                Amortizacion a = new Amortizacion();
                a.setPeriodo(i);
                a.setCouta(cuotas);

                double porpagar = c.getCantidad() - (cuotas * i);
                double interes = porpagar * (solicitud.getTasadepagos() / 100);

                a.setInteres(interes);
                a.setTotal(cuotas + interes);
                a.setDeuda(porpagar);
                a.setEstado(false);
                a.setFecha(new Date(new Date().getYear(), new Date().getMonth() + i, new Date().getDay()));
                a.setCredito(c);
                lista.add(a);
            }
            c.setAmortizacionList(lista);
            creditoDAO.insert(c);
            //Envio de correo
            enviarCorreoIngreso(c);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(CreditosON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarCorreoIngreso(Credito credito) {
        try {
            String respesta = "<h1> Tabla amortizacion </h1><br><bbr>";
            respesta = respesta + "<table>\n"
                    + "  <tr>\n"
                    + "    <th>CUOTA</th>\n"
                    + "    <th>INTERES</th>\n"
                    + "    <th>TOTAL</th>\n"
                    + "    <th>FECHA</th>\n"
                    + "  </tr>\n";
            for (Amortizacion a : credito.getAmortizacionList()) {
                respesta = respesta + "<tr>\n";
                respesta = respesta + "<td>" + a.getPeriodo() + "</td>\n"
                        + "<td>" + a.getInteres() + "</td>\n"
                        + "<td>" + a.getTotal() + "</td>\n"
                        + "<td>" + a.getFecha() + "</td>\n";
                respesta = respesta + " </tr>\n";
                        

            }
            respesta = respesta + "</table>";
            
            correoON.sendAsHtml(credito.getCuenta().getCliente().getCorreo(), "Credito Aprobado", respesta);

        } catch (MessagingException ex) {
            Logger.getLogger(SolicitudON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
