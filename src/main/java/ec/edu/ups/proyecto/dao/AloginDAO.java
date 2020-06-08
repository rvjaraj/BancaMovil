/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Alogin;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fanny
 */

@Stateless
public class AloginDAO {
    
    
    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public AloginDAO() {
    }

      /**
     * Inserta el login mediante el numero el objeto de Alogin.
     * @param alogin
     * @return si inserto o no.
     */

     public boolean insert(Alogin alogin) throws Exception {
        boolean bandera = true;
    	try {
            em.persist(alogin);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Alogin " + e.getMessage());
            
        }
        
        return bandera;
    }
     
        /**
     * Elimina el metodo mediante el numero el objeto de Alogin.
     * @param alogin
     * @remove
     */
    public void delete(Alogin alogin) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(alogin.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Alogin " +e.getMessage());
        }
    }
     /**
     * Elimina el metodo mediante el id.
     * @param alogin
     * @remove
     */

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Alogin " +e.getMessage());
        }
    }
      /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param alogin
     * @merge
     */

    public void update(Alogin alogin) throws Exception {
        try {
            em.merge(alogin);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Alogin " +e.getMessage());
        }
    }
    
       /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */


    public Alogin read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Alogin.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Alogin " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los logines realizados
     * @createNamedQuery crea un querry para poder listar
     * @return
     */

    public List<Alogin> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Alogin.findAll");
            List<Alogin> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Alogin " +e.getMessage());
        }

    }
         /**
     * El metodo lista todos los logines realizados mediante la cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    public List<Alogin> findAllbyCedula(String cedula) throws Exception {
        System.out.println("Cedula ++: "+cedula);
        try {
            System.out.println("Cedula ++: "+cedula);
            Query q = em.createNamedQuery("Alogin.findByCedula");
            q.setParameter("cedula", cedula);
            List<Alogin> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Alogin " +e.getMessage());
        }

    }
           /**
     * El metodo lista todos los logines realizados mediante la codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    public List<Alogin> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Alogin.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Alogin> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Alogin " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los logines realizados mediante id
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    
    public Alogin findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Alogin.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Alogin) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }
               /**
     * El metodo busca mediante la cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    

    public Alogin findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Alogin p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Alogin.class);
            q.setParameter("cedula", cedula);

            return (Alogin) q.getSingleResult();
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
            String jpql = "SELECT P FROM Alogin p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Alogin.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
