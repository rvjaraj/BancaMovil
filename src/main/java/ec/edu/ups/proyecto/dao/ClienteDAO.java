/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ClienteDAO {

    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public ClienteDAO() {
    }
     /**
     * Inserta el login mediante el numero el objeto de cliente.
     * @param cliente
     * @return si inserto o no.
     */
    
    public boolean insert(Cliente cliente) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(cliente);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Cliente " + e.getMessage());
            
        }
        
        return bandera;
    }
      /**
     * Elimina el metodo mediante el numero el objeto de cliente.
     * @param cliente
     * @remove
     */
    public void delete(Cliente cliente) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(cliente.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Cliente " +e.getMessage());
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
            throw new Exception("oErro Eliminar Cliente " +e.getMessage());
        }
    }
          /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param alogin
     * @merge
     */

    public void update(Cliente cliente) throws Exception {
        try {
            em.merge(cliente);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Cliente " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Cliente read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Cliente " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los cliente existentes
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Cliente> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Cliente.findAll");
            List<Cliente> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Cliente " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los clientes por su codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    public List<Cliente> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Cliente.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Cliente> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Cliente " +e.getMessage());
        }

    }
               /**
     * El metodo busca todos los clientes mediante sus id
     * @param id
     * @createNamedQuery crea un querry para poder encontrar 
     * @return
     */
    
    public Cliente findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Cliente.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Cliente) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " +e.getMessage());
        }

    }
             /**
     * El metodo busca todos los clientes mediante sus cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public Cliente findByCedula(String cedula) throws Exception {
        try {
            Query q = em.createNamedQuery("Cliente.findByCedula");
            q.setParameter("cedula", cedula);
            return (Cliente) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }
            /**
     * El metodo selecciona el maximo de un id
     * @createQuery esta sentencia recibe el querry y la clase
     * @return
     */
    
    public int maxId() throws Exception {
        try {
            Query q = em.createNamedQuery("Cliente.maxId");
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
