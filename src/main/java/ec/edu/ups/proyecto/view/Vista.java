/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.view;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.ServicesON;
import ec.edu.ups.proyecto.emtitis.DepositoSRV;
import ec.edu.ups.proyecto.emtitis.Mensajes;
import ec.edu.ups.proyecto.emtitis.RetiroSRV;
import ec.edu.ups.proyecto.emtitis.TransferenciaSRV;

/**
 *
 * @author Vinicio
 */
@WebServlet(name = "Vista", urlPatterns = {"/Vista"})
public class Vista extends HttpServlet {
    
	@Inject
	ClienteON cOn;
        
        @Inject 
        ServicesON servicesON;
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().println("<h1>Hola mundo, si estan sobreviviendo al covid as </h1>");
            
//             Mensajes e = servicesON.DepositoSRV(new DepositoSRV("CUHA06S3", 100));
//            Mensajes ee = servicesON.RetiroSRV(new RetiroSRV("CUHA06S3", 50));
//            response.getWriter().println("<h1>Hjuan: " + e.getNombre() + " <> "  + ee.getNombre() +"</h1>");
//            
//            Mensajes eee = servicesON.TransferenciasInternaSRV(new TransferenciaSRV("CUHA06S3", "CUHA16S10", 100.10, "Transferenacis internas"));
//            response.getWriter().println("<h1>Trans: " + eee.getNombre() +"</h1>");
             
            response.getWriter().println(servicesON.enviarDataSet().get(2).toString());
            
        } catch (Exception e) {
            response.getWriter().println(e.getMessage() +e.getLocalizedMessage() + " >>>");
            System.out.println(e.getMessage() +" acaca");
        }
    }

}
