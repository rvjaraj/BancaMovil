/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.ResumenCuentaON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Cuenta;
import ec.edu.ups.proyecto.emtitis.Transaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vinicio
 */
;

@ManagedBean
@ViewScoped
public class ClienteBEAN {

    private Cliente newCliente;
    private Cliente auxCliente;
    private List<Cliente> listaClientees;
    private ArrayList<String> listaOpc;
    private String textoBuscar;
    private String cuenta;
    private String cedulaPersona;
    private List<Transaciones> listaCuentasClientes =  new ArrayList<Transaciones>();
    private List<Cliente> listaClientes =  new ArrayList<Cliente>();
    private List<Transaciones> listaEstadosCta = new ArrayList<Transaciones>();
    private Date fechaInicio;
    private Date fechaFin;
    private String tipo;
    private Transaciones newTransaciones = new Transaciones();
    
    public ClienteBEAN() {
    }
    
    

    @Inject
    private ClienteON clienteON;
    
    @Inject
    private ResumenCuentaON resumenCuentaON;

    @PostConstruct
    public void init() {
        newCliente = new Cliente();
        cuenta = clienteON.numeroCuenta();
        listaClientees = clienteON.listaClientees();
        textoBuscar = "";
//        fechaInicio = null;
//        fechaFin = null;
//        tipo = "";
        auxCliente = new Cliente();
        loadResumenCliente();
        
        action();
    }
    
    public void action() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        String param = request.getParameter("tipo");
        tipo = param;
        
    }

    public String guardarCliente() {
        try {
            System.out.println(newCliente.toString()+">>>>");
            clienteON.guardarCliente(newCliente, cuenta);
            init();
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaClientees() {
        System.out.println(textoBuscar);
        try {
            listaClientees = clienteON.listaClienteesCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String buscaClienteID(String id) {
        try {
             auxCliente = clienteON.buscarCliente(id);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String actualizarCliente(){
        clienteON.actualizarCliente(auxCliente);
        init();
        System.out.println("actualizado");
        return null;
    }
    
    public String eliminarCliente(){
        auxCliente.setEliminado(true);
        auxCliente.getCuentaList().get(0).setEliminado(true);
        clienteON.actualizarCliente(auxCliente);
        init();
        System.out.println("Eliminado");
        return null;
    }
    
    public String loadResumenCliente() {
    	listaClientes = resumenCuentaON.getClienteByCedelua("0106");
    	for (Cliente cliente : listaClientes) {
    		loadResumenCuenta(cliente.getCuentaList().get(0).getNumero());
		}
    	return null;
    }
    
    public String loadResumenCuenta(String numero) {
    	listaCuentasClientes = resumenCuentaON.getResumenCuentaCliente(numero);
    	return null;
    }
    
    
    public String loadEstadoCta() {
    	listaEstadosCta = resumenCuentaON.getEstadoCtaByMes(tipo, fechaInicio, fechaFin);
    	System.out.println("llega los paametros >>>>> "+ tipo +" fehcIni: "+ fechaInicio+ " fechaFin: "+ fechaFin);
    	for (Transaciones t : listaEstadosCta) {
			System.out.println("fechas--"+t.getFecha());
		}
    	return null;
    }
    
    
    // -------------------> 
    public Cliente getNewCliente() {
        return newCliente;
    }

    public void setNewCliente(Cliente newCliente) {
        this.newCliente = newCliente;
    }

    public ClienteON getClienteON() {
        return clienteON;
    }

    public void setClienteON(ClienteON clienteON) {
        this.clienteON = clienteON;
    }

    public ArrayList<String> getListaOpc() {
        return listaOpc;
    }

    public void setListaOpc(ArrayList<String> listaOpc) {
        this.listaOpc = listaOpc;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Cliente getAuxCliente() {
        return auxCliente;
    }

    public void setAuxCliente(Cliente auxCliente) {
        this.auxCliente = auxCliente;
    }

    public List<Cliente> getListaClientees() {
        return listaClientees;
    }

    public void setListaClientees(List<Cliente> listaClientees) {
        this.listaClientees = listaClientees;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public List<Transaciones> getListaCuentasClientes() {
		return listaCuentasClientes;
	}

	public void setListaCuentasClientes(List<Transaciones> listaCuentasClientes) {
		this.listaCuentasClientes = listaCuentasClientes;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Transaciones> getListaEstadosCta() {
		return listaEstadosCta;
	}

	public void setListaEstadosCta(List<Transaciones> listaEstadosCta) {
		this.listaEstadosCta = listaEstadosCta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	

}
