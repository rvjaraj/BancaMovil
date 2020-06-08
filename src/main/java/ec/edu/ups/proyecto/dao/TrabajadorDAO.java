/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
public class TrabajadorDAO {

    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public TrabajadorDAO() {
    }
     /**
     * Inserta el trabajador  mediante  el objeto de Trabajador.
     * @param trabajador
     * @return si inserto o no.
     */
    
    public boolean insert(Trabajador trabajador) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(trabajador);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Trabajador " + e.getMessage());
            
        }
        
        return bandera;
    }
      /**
     * Elimina el metodo mediante el numero el objeto de Alogin.
     * @param trabajador
     * @remove
     */
    public void delete(Trabajador trabajador) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(trabajador.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Trabajador " +e.getMessage());
        }
    }
    /**
     * Elimina el metodo mediante el id.
     * @param trabajador
     * @remove
     */
    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Trabajador " +e.getMessage());
        }
    }
          /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param trabajador
     * @merge
     */

    public void update(Trabajador trabajador) throws Exception {
        try {
            em.merge(trabajador);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Trabajador " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Trabajador read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Trabajador.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Trabajador " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los trabajadores
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Trabajador> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Trabajador.findAll");
            List<Trabajador> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Trabajador " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los trabajadores realizados mediante el codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
    public List<Trabajador> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Trabajador.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Trabajador> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Trabajador " +e.getMessage());
        }

    }
               /**
     * El metodo busca mediante el id
     * @param  listarid
     * @createNamedQuery crea un querry para poder encontrar el objeto buscado
     * @return
     */
    
    public Trabajador findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Trabajador.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Trabajador) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }
             /**
     * El metodo buscar mediante la cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder buscar la sentencia ante la clase
     * @return
     */
    public Trabajador findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Trabajador p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Trabajador.class);
            q.setParameter("cedula", cedula);

            return (Trabajador) q.getSingleResult();
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
            String jpql = "SELECT P FROM Trabajador p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Trabajador.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
