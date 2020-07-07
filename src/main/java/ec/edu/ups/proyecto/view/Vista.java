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
import ec.edu.ups.proyecto.emtitis.Errores;

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
            response.getWriter().println("<h1>Hola mundo, si estan sobreviviendo al covid</h1>");
            
            Errores e = servicesON.DepositoSRV("CUHA06S3", 10.10);
            Errores ee = servicesON.RetiroSRV("CUHA06S3", 20.10);
            response.getWriter().println("<h1>Hjuan: " + e.getNombre() + " <> "  + ee.getNombre() +"</h1>");
        } catch (Exception e) {
            response.getWriter().println(e.getMessage() +e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
    }

}
