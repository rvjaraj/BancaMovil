/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.SolicitudON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Solicitud;
import java.util.Arrays;
import java.util.List;
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
public class SolicitudBEAN {
    @Inject
    private ClienteON clienteON;

    @Inject
    private SolicitudON solicitudON;
    
    private String cedula = "";
    private Cliente cliente;
    private Solicitud solicitud;
    private List<String> listaProposito;
    private List<String> listaTiempoEmpleo;
    private List<String> listaActivos;
    private List<String> listaVivienda;
    private List<String> listaEmpleo;
    private List<String> listaExtrangero;
    
    
    private String folder = "C:\\Users\\Vinicio\\Documents\\wildfly-18.0.1.Final\\bin\\";

    private Part uploadedFile;

   
    
    

    public SolicitudBEAN() {
    }

    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        cedula = request.getParameter("cedula");

        if (cedula != null) {
            cliente = clienteON.buscarClienteCedula(cedula);
            solicitud = solicitudON.datosIniciales(cliente);
        }
    }

    @PostConstruct
    public void init() {
        listaProposito = Arrays.asList("Inmuebles | casa, finca, etc |", "Muebles | Equipamiento",
                "Automovil", "Electrodomesticos", "Tecnologia", "Reparaciones", "Educacion", "Capacitacion", "Negocios", "Otros");
        listaTiempoEmpleo = Arrays.asList("Desempleado", "Trabajando: Menos de un año", "Trabajando | 1 - 4 años", "Trabajando | 4- 7 años", "Trabajando | Mas de 7 años");
        listaActivos = Arrays.asList("Bienes inmuebles", "Seguro de vida", "Automovil", "Plan de construccion", "Sin propiedad");
        listaVivienda = Arrays.asList("Gratis", "Alquiler", "Propio");
        listaEmpleo = Arrays.asList("Desempleado", "Jubilado", "Empleado", "Autonomo");
        listaExtrangero = Arrays.asList("SI", "NO");
        cedula = "";
        action();

    }

    public String guardarSolicitud() {
        System.out.println(solicitud.toString());
        solicitudON.guardarSolicuitud(solicitud, saveFile(solicitud));
        return null;
    }
    
     public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String saveFile(Solicitud solicitud) {

        System.out.println("saveFile method invoked..");
        System.out.println("content-type:{0}" + uploadedFile.getContentType());
        System.out.println("filename:{0}" + uploadedFile.getName());
        System.out.println("submitted filename:{0}" + uploadedFile.getSubmittedFileName());
        System.out.println("size:{0}" + uploadedFile.getSize());
        String fileName = "";

        try {
            fileName = getFilename(uploadedFile);
            System.out.println("fileName  " + solicitud.getCliente().getCedula()+".pdf");
            uploadedFile.write(folder  + solicitud.getCliente().getCedula()+".pdf");
            
        } catch (IOException ex) {
            System.out.println("aca");
            System.out.println(ex);

        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File " + fileName + " Uploaded!"));
        return fileName;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
    
    
    //>>>>>>>>>>>>>>>>>
    
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public List<String> getListaProposito() {
        return listaProposito;
    }

    public void setListaProposito(List<String> listaProposito) {
        this.listaProposito = listaProposito;
    }

    public List<String> getListaTiempoEmpleo() {
        return listaTiempoEmpleo;
    }

    public void setListaTiempoEmpleo(List<String> listaEmpleo) {
        this.listaTiempoEmpleo = listaEmpleo;
    }

    public List<String> getListaActivos() {
        return listaActivos;
    }

    public void setListaActivos(List<String> listaActivos) {
        this.listaActivos = listaActivos;
    }

    public List<String> getListaVivienda() {
        return listaVivienda;
    }

    public void setListaVivienda(List<String> listaVivienda) {
        this.listaVivienda = listaVivienda;
    }

    public List<String> getListaEmpleo() {
        return listaEmpleo;
    }

    public void setListaEmpleo(List<String> listaEmpleo) {
        this.listaEmpleo = listaEmpleo;
    }

    public List<String> getListaExtrangero() {
        return listaExtrangero;
    }

    public void setListaExtrangero(List<String> listaExtrangero) {
        this.listaExtrangero = listaExtrangero;
    }
    
    
    
    
}
