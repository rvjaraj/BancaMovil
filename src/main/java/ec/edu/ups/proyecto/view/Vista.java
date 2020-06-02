/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vinicio
 */
@WebServlet(name = "Vista", urlPatterns = {"/Vista"})
public class Vista extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().println("<h1>Ricardo Jara</h1>");
        } catch (Exception e) {
            response.getWriter().println(e.getMessage() +e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
    }

}
