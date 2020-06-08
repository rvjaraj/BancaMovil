package ec.edu.ups.proyecto.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.proyecto.dao.ResumenCuentaDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Cuenta;
import ec.edu.ups.proyecto.emtitis.Transaciones;

@Stateless
public class ResumenCuentaON {

    @Inject
    ResumenCuentaDAO resumenCuentaDAO;
    
public List<Transaciones> getResumenCuentaCliente(String numero){
		return resumenCuentaDAO.getResumenCuentaCliente(numero);
    }
	
public List<Cliente> getClienteByCedelua(String filtro){
	return resumenCuentaDAO.getClienteByCedelua(filtro);
}

public List<Transaciones> getEstadoCtaByMes(String filtro, Date fechaIni, Date FechaFin){
	return resumenCuentaDAO.getEstadoCtaByMes(filtro, fechaIni, FechaFin);
}


}
