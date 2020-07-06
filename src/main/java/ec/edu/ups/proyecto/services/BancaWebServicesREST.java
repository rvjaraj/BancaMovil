package ec.edu.ups.proyecto.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.controler.CajeroBEAN;
import ec.edu.ups.proyecto.emtitis.Cliente;

@Path("/banca")
public class BancaWebServicesREST {

	@Inject
	private ClienteON clienteON;


	public String transferencia() {
		return null;
	}
	
	Cliente auxCliente = new Cliente();
	
	@GET
	@Path("/deposito")
	@Produces("application/json")
	//@Consumes("application/json")
	public String deposito(@QueryParam("cedula") String cedula, @QueryParam("cantidad") double cantidad) {
		try {
			auxCliente = buscaClienteID(cedula);
		        BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() + cantidad);
		        bd = bd.setScale(2, RoundingMode.HALF_UP);
		        auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
		        clienteON.actualizarClienteTrasaccion(auxCliente, "Deposito", new BigDecimal(cantidad).doubleValue());
			
			return "Deposito Satisfactorio, Saldo actual: " + bd;
		} catch (Exception e) {
			return "No fue posible realizar el deposito"+ e.getMessage();
		}
		
	}
	
	//no
	 public Cliente buscaClienteID(String cedula) {
	        try {
	            //auxCliente = clienteON.buscarCliente(id);
	        	auxCliente = clienteON.buscarClienteCedula(cedula);
	        } catch (Exception ex) {
	            Logger.getLogger(CajeroBEAN.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return auxCliente;
	    }
	
	@GET
	@Path("/retiro")
	@Produces("application/json")
	public String retiro(@QueryParam("cedula") String cedula, @QueryParam("cantidad")double cantidad) {
		try {
			auxCliente = buscaClienteID( cedula);
		        BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() - cantidad);
		        bd = bd.setScale(2, RoundingMode.HALF_UP);
		        auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
		        clienteON.actualizarClienteTrasaccion(auxCliente, "Retiro", new BigDecimal(cantidad).doubleValue());
			
			return "Retiro Satisfactorio, Saldo actual: " + bd;
		} catch (Exception e) {
			return "No fue posible realizar el retiro"+ e.getMessage();
		}
		
	}
	
	@GET
	@Path("/transferencia")
	@Produces("application/json")
	public String transferenciaLocal(@QueryParam("cliorigen") String clienteOrigen,@QueryParam("clidestino") String clienteDestino, @QueryParam("cantidad")double cantidad) {
		try {
		//Rebjar saldo
			String aux=null;
			auxCliente = buscaClienteID( clienteOrigen);
	        BigDecimal bdO = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() - cantidad);
	        bdO = bdO.setScale(2, RoundingMode.HALF_UP);
	        auxCliente.getCuentaList().get(0).setSaldo(bdO.doubleValue());
	        clienteON.actualizarClienteTrasaccion(auxCliente, "Transferencia", new BigDecimal(cantidad).doubleValue());
			aux= "Cta Debito: "+ auxCliente.getCuentaList().get(0).getNumero() + " , Saldo Actual: "+bdO;
	      //Inbrementar saldo cta destino
			auxCliente = buscaClienteID( clienteDestino);
	        BigDecimal bdD = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() + cantidad);
	        bdD = bdD.setScale(2, RoundingMode.HALF_UP);
	        auxCliente.getCuentaList().get(0).setSaldo(bdD.doubleValue());
	        clienteON.actualizarClienteTrasaccion(auxCliente, "Transferencia", new BigDecimal(cantidad).doubleValue());
	        aux= aux+ "\n Cta Beneficiaria: "+ auxCliente.getCuentaList().get(0).getNumero() + " , Saldo Actual: "+bdD;
	        
	        
			return "Transferencia Exitosa, " + aux;
		} catch (Exception e) {
			return "No fue posible realizar Transferencia"+ e.getMessage();
		}
	}
}
