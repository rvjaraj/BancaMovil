package ec.edu.ups.proyecto.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Cuenta;
import ec.edu.ups.proyecto.emtitis.Transaciones;

@Stateless
public class ResumenCuentaDAO {
	
	@PersistenceContext(name = "BancaMovilPersistenceUnit")
	private EntityManager em;
	
	public ResumenCuentaDAO() {
		
	}
         /**
     * El metodo permite listar en un resumen de cuentas todas las transaciones realizadas mediante un filtro
     * @param filtro
     * se crea un sentencia en la cual seleciona el maximo de transaciones comparando ante el objeto
     * @return lista.
     */
	
	public List<Transaciones> getResumenCuentaCliente(String filtro){
		String jpql ="SELECT MAX(t) FROM Transaciones t, Cuenta c WHERE t.cuentaid = c.id and c.numero LIKE :filtro";
		Query q = em.createQuery(jpql, Transaciones.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}
	      /**
     * El metodo permite listar en un resumen de cuentas todas las transaciones realizadas en un mes
     * @param filtro,fechaIni,FechaFin
     * se crea un sentencia en la cual seleciona el el objeto y compara si el mes el que desea el usuario
     * @return lista.
     */
	
	public List<Transaciones> getEstadoCtaByMes(String filtro, Date fechaIni, Date FechaFin){
		String jpql ="SELECT t FROM Transaciones t, Cuenta c WHERE t.cuentaid = c.id and c.numero LIKE :filtro and t.fecha between :fechaI and :FechaF";
		Query q = em.createQuery(jpql, Transaciones.class);
		q.setParameter("filtro", filtro);
		q.setParameter("fechaI", fechaIni);
		q.setParameter("FechaF", FechaFin);
		return q.getResultList();
	}
	
	      /**
     * El metodo permite listar en un resumen de cuentas todas las transaciones realizadas mediante la cedula
     * @param filtro
     * se crea un sentencia en la cual seleciona el cliente  comparando ante el objeto
     * @return lista.
     */
	public List<Cliente> getClienteByCedelua(String filtro){
		String jpql ="SELECT c FROM Cliente c WHERE c.cedula LIKE :filtro";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}
	
}
