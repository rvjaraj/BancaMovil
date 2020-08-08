package ec.edu.ups.proyecto.services;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.CreditosON;
import ec.edu.ups.proyecto.business.LoginON;
import ec.edu.ups.proyecto.business.TransaccionesON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.ClienteTemp;
import ec.edu.ups.proyecto.emtitis.Credito;
import ec.edu.ups.proyecto.emtitis.Transaciones;

@Path("/movil")
public class ServicesMovil {

	@Inject
	LoginON loginon;
	
	@Inject
	CreditosON creditoon;
	
	@Inject
	ClienteON clienteon;
	
	@Inject
	TransaccionesON transaccioneson;
	
	@Path("/usuario")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public String login(ClienteTemp clientetemp) {
		if (loginon.loginClie(clientetemp.getCedula(),clientetemp.getContrasenia())!=null) {
			return "ok";
		}else {
			return "error";
		}
	
	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Credito> getSaldoCreditos(@QueryParam("cedula") String cedula){
		
		return creditoon.listarCreditosCliente(cedula);
	}
	
	@GET
	@Path("/listatransaccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaciones> getTransacciones(@QueryParam("cedula") String cedula){
		if (cedula != null) {
			return transaccioneson.listaTransacionesCedula(cedula);
		}
		else  
		return null;
	}
	
	@Path("/actualizarContra")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public String actualizarContra(Cliente cliente) throws Exception {
		clienteon.actContraCliente(cliente);
		return "ok";
	}
	
}