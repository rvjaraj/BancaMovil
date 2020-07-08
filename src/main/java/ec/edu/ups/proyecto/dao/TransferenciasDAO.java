/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Transferencias;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class TransferenciasDAO {

    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public TransferenciasDAO() {
    }
     /**
     * Inserta el login mediante el numero el objeto de transferencias.
     * @param transferencias
     * @return si inserto o no.
     */
    
    public boolean insert(Transferencias transferencias) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(transferencias);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Transferencias " + e.getMessage());
            
        }
        
        return bandera;
    }
      /**
     * Elimina el metodo mediante el numero el objeto de transferencias.
     * @param transferencias
     * @remove
     */
    public void delete(Transferencias transferencias) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(transferencias.getId()));
        } catch (Exception e) {
            throw new Exception("Erro Eliminar Transferencias " +e.getMessage());
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
            throw new Exception("oErro Eliminar Transferencias " +e.getMessage());
        }
    }
          /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param alogin
     * @merge
     */

    public void update(Transferencias transferencias) throws Exception {
        try {
            em.merge(transferencias);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Transferencias " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Transferencias read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Transferencias.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Transferencias " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los transferencias existentes
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Transferencias> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Transferencias.findAll");
            List<Transferencias> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Transferencias " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los transferenciass por su codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    public List<Transferencias> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Transferencias.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Transferencias> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Transferencias " +e.getMessage());
        }

    }
               /**
     * El metodo busca todos los transferenciass mediante sus id
     * @param id
     * @createNamedQuery crea un querry para poder encontrar 
     * @return
     */
    
    public Transferencias findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Transferencias.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Transferencias) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " +e.getMessage());
        }

    }
    
    
    public Transferencias findByNuemor(String numero) throws Exception {
        try {
            Query q = em.createNamedQuery("Transferencias.findByNumero");
            q.setParameter("numero", numero);
            return (Transferencias) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " +e.getMessage());
        }

    }
    
             /**
     * El metodo busca todos los transferenciass mediante sus cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public Transferencias findByCedula(String cedula) throws Exception {
        try {
            Query q = em.createNamedQuery("Transferencias.findByCedula");
            q.setParameter("cedula", cedula);
            return (Transferencias) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }
 

}
