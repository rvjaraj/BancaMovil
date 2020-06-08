package ec.edu.ups.proyecto.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.proyecto.dao.ResumenCuentaDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Cuenta;
import ec.edu.ups.proyecto.emtitis.Transaciones;

/**
 * Este metodo es para cuando se inicie sesion, nos presente el resumen de nuestras cuentas.
 * @author juan
 *
 */
@Stateless
public class ResumenCuentaON {

    @Inject
    ResumenCuentaDAO resumenCuentaDAO;
    /**
     * Obtiene el resumen de todas las cuentas mediante el numero de cuenta.
     * @param numero
     * @return
     */
public List<Transaciones> getResumenCuentaCliente(String numero){
		return resumenCuentaDAO.getResumenCuentaCliente(numero);
    }
	/**
	 * Obtnemos el cliente medinte la cedula
	 * @param filtro
	 * @return
	 */
public List<Cliente> getClienteByCedelua(String filtro){
	return resumenCuentaDAO.getClienteByCedelua(filtro);
}
	/**
	 * Este metod indica los transacciones durante 30 dias.
	 * @param filtro
	 * @param fechaIni
	 * @param FechaFin
	 * @return
	 */
public List<Transaciones> getEstadoCtaByMes(String filtro, Date fechaIni, Date FechaFin){
	return resumenCuentaDAO.getEstadoCtaByMes(filtro, fechaIni, FechaFin);
}


}
