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

/**
 *
 * @author Ricardo
 */
@Stateless
public class TrabajadorDAO {

    @PersistenceContext
    private EntityManager em;

    public TrabajadorDAO() {
    }
    
    
    public void insert(Trabajador trabajador) throws Exception {
        try {
            System.out.println("si creo que llega aca");
            em.persist(trabajador);
        } catch (Exception e) {
            throw new Exception("Erro ingreso Trabajador " + e.getMessage());
        }
    }

    public void delete(Trabajador trabajador) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(trabajador.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Trabajador " +e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Trabajador " +e.getMessage());
        }
    }
    
    public void update(Trabajador trabajador) throws Exception {
        try {
            em.merge(trabajador);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Trabajador " +e.getMessage());
        }
    }

    public Trabajador read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Trabajador.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Trabajador " +e.getMessage());
        }
    }

    public List<Trabajador> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Trabajador.findAll");
            List<Trabajador> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Trabajador " +e.getMessage());
        }

    }
    
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
    
    public Trabajador findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Trabajador.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Trabajador) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }

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
