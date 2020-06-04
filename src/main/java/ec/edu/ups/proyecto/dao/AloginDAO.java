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

  

     public boolean insert(Alogin trabajador) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(trabajador);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Alogin " + e.getMessage());
            
        }
        
        return bandera;
    }

    public void delete(Alogin trabajador) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(trabajador.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Alogin " +e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Alogin " +e.getMessage());
        }
    }
    
    public void update(Alogin trabajador) throws Exception {
        try {
            em.merge(trabajador);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Alogin " +e.getMessage());
        }
    }

    public Alogin read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Alogin.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Alogin " +e.getMessage());
        }
    }

    public List<Alogin> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Alogin.findAll");
            List<Alogin> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Alogin " +e.getMessage());
        }

    }
    
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
    
    public Alogin findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Alogin.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Alogin) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }

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
