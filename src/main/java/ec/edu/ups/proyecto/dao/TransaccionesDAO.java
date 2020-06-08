/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import ec.edu.ups.proyecto.emtitis.Transaciones;
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
public class TransaccionesDAO {

    @PersistenceContext(name = "BancaMovilPersistenceUnit")
    private EntityManager em;

    public TransaccionesDAO() {
    }

    public boolean insert(Transaciones transaciones) throws Exception {
        boolean bandera = true;
        try {
            System.out.println("si creo que llega aca");
            em.persist(transaciones);
            bandera = true;
        } catch (Exception e) {
            bandera = false;
            throw new Exception("Erro ingreso Transaciones " + e.getMessage());

        }

        return bandera;
    }

    public void delete(Transaciones transaciones) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(transaciones.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Transaciones " + e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Transaciones " + e.getMessage());
        }
    }

    public void update(Transaciones transaciones) throws Exception {
        try {
            em.merge(transaciones);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Transaciones " + e.getMessage());
        }
    }

    public Transaciones read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Transaciones.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Transaciones " + e.getMessage());
        }
    }

    public List<Transaciones> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Transaciones.findAll");
            List<Transaciones> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Transaciones " + e.getMessage());
        }

    }

    public List<Transaciones> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Transaciones.findAllCodigo");
            q.setParameter("codigo", "%" + codigo + "%");
            List<Transaciones> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Transaciones " + e.getMessage());
        }

    }

    public Transaciones findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Transaciones.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Transaciones) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }

    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Transaciones p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Transaciones.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

    public List<Transaciones> findAllbyCedula(String cedula) throws Exception {
        try {
            Query q = em.createNamedQuery("Transaciones.findByCedula");
            q.setParameter("cedula", cedula);
            List<Transaciones> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Transaciones " + e.getMessage());
        }
    }

}
