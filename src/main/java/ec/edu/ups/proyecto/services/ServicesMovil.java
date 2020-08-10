package ec.edu.ups.proyecto.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import ec.edu.ups.proyecto.business.ClienteON;
import ec.edu.ups.proyecto.business.CreditosON;
import ec.edu.ups.proyecto.business.LoginON;
import ec.edu.ups.proyecto.business.ServicesON;
import ec.edu.ups.proyecto.business.TransaccionesON;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.ClienteTemp;
import ec.edu.ups.proyecto.emtitis.Credito;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import ec.edu.ups.proyecto.emtitis.TransferenciaSRV;

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
	
	@Inject
	ServicesON servicioson;
	
	@Path("/usuario")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response login(ClienteTemp clientetemp) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		
		if (loginon.loginClie(clientetemp.getCedula(),clientetemp.getContrasenia())!=null) {
			data.put("code","1");
			data.put("message","si");
			builder = Response.status(Response.Status.OK).entity(data);
			
		}else {
			data.put("code","2");
			data.put("message","NO");
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);//Response.Status.BAD_REQUEST
			
		}//System.out.println("--"+builder.build());
		return  builder.build();
		
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
	public Response actualizarContrasenia(ClienteTemp clientetemp) throws Exception {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		if(clienteon.findByEmail(clientetemp)== true) {
			data.put("code","1");
			data.put("message","SI");
			builder = Response.status(Response.Status.OK).entity(data);
		}
		else {
			data.put("code","2");
			data.put("message","NO");
			builder = Response.status(Response.Status.OK).entity(data);
		}
		return builder.build();
	}
	
	@Path("/transferenciamovil")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response transferencia(TransferenciaSRV transferenciaSrv) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		servicioson.TransferenciasInternaSRV(transferenciaSrv);
		
		return builder.build();
	}
}