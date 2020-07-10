/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Cuenta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class CuentaDAO {

    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public CuentaDAO() {
    }
     /**
     * Inserta el login mediante el numero el objeto de cuenta.
     * @param cuenta
     * @return si inserto o no.
     */
    
    public boolean insert(Cuenta cuenta) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(cuenta);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Cuenta " + e.getMessage());
            
        }
        
        return bandera;
    }
      /**
     * Elimina el metodo mediante el numero el objeto de cuenta.
     * @param cuenta
     * @remove
     */
    public void delete(Cuenta cuenta) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(cuenta.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Cuenta " +e.getMessage());
        }
    }
        /**
     * Elimina el metodo mediante el id.
     * @param id
     * @remove
     */

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Cuenta " +e.getMessage());
        }
    }
          /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param alogin
     * @merge
     */

    public void update(Cuenta cuenta) throws Exception {
        try {
            em.merge(cuenta);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Cuenta " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Cuenta read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Cuenta.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Cuenta " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los cuenta existentes
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Cuenta> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Cuenta.findAll");
            List<Cuenta> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Cuenta " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los cuentas por su codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    public List<Cuenta> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Cuenta.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Cuenta> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Cuenta " +e.getMessage());
        }

    }
               /**
     * El metodo busca todos los cuentas mediante sus id
     * @param id
     * @createNamedQuery crea un querry para poder encontrar 
     * @return
     */
    
    public Cuenta findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Cuenta.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Cuenta) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " +e.getMessage());
        }

    }
    
    
    public Cuenta findByNuemor(String numero) throws Exception {
        try {
            Query q = em.createNamedQuery("Cuenta.findByNumero");
            q.setParameter("numero", numero);
            return (Cuenta) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  numero " + e.getMessage());
        }

    }
    
             /**
     * El metodo busca todos los cuentas mediante sus cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public Cuenta findByCedula(String cedula) throws Exception {
        try {
            Query q = em.createNamedQuery("Cuenta.findByCedula");
            q.setParameter("cedula", cedula);
            return (Cuenta) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }
 

}
