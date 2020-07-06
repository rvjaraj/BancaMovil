package ec.edu.ups.proyecto.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.controler.CajeroBEAN;
import ec.edu.ups.proyecto.emtitis.Cliente;

@WebService
public class BancaWebServicesSOAP {
	@Inject
	private ClienteON clienteON;

	@WebMethod
	public String transferencia() {
		return null;
	}
	
	Cliente auxCliente = new Cliente();
	@WebMethod
	public String deposito(String cedula, double cantidad) {
		try {
			auxCliente = buscaClienteID( cedula);
		        BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() + cantidad);
		        bd = bd.setScale(2, RoundingMode.HALF_UP);
		        auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
		        clienteON.actualizarClienteTrasaccion(auxCliente, "Deposito", new BigDecimal(cantidad).doubleValue());
			
			return "Deposito Satisfactorio, Saldo actual: " + bd;
		} catch (Exception e) {
			return "No fue posible realizar el deposito"+ e.getMessage();
		}
		
	}
	
	 public Cliente buscaClienteID(String cedula) {
	        try {
	            //auxCliente = clienteON.buscarCliente(id);
	        	auxCliente = clienteON.buscarClienteCedula(cedula);
	        } catch (Exception ex) {
	            Logger.getLogger(CajeroBEAN.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return auxCliente;
	    }
	
	
	
	@WebMethod
	public String retiro(String cedula, double cantidad) {
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
	
	@WebMethod
	public String transferenciaLocal (String clienteOrigen, String clienteDestino, double cantidad) {
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
