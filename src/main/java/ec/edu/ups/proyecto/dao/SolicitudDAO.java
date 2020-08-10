/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Solicitud;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SolicitudDAO {

    @PersistenceContext(name = "BancaMovilPersistenceUnit")
    private EntityManager em;

    public SolicitudDAO() {
    }

    /**
     * Este metodo permite guadar la solicutud mediante el parametro solicitud
     *
     * @param solicitud
     * @return
     * @throws Exception
     */
    public boolean insert(Solicitud solicitud) throws Exception {
        boolean bandera = true;
        try {
            em.persist(solicitud);
            bandera = true;
        } catch (Exception e) {
            bandera = false;
            throw new Exception("Erro ingreso Solicitud " + e.getMessage());
        }

        return bandera;
    }

    /**
     * Este metodo permite eliminar la solicitu
     *
     * @param solicitud
     * @throws Exception
     */

    public void delete(Solicitud solicitud) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(solicitud.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Solicitud " + e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Solicitud " + e.getMessage());
        }
    }

    public void update(Solicitud solicitud) throws Exception {
        try {
            em.merge(solicitud);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Solicitud " + e.getMessage());
        }
    }

    public Solicitud read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Solicitud.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Solicitud " + e.getMessage());
        }
    }

    public List<Solicitud> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Solicitud.findAll");
            List<Solicitud> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Solicitud " + e.getMessage());
        }

    }

    public List<Solicitud> findByCliente(String cedula) throws Exception {
        try {
            Query q = em.createNamedQuery("Solicitud.findByCliente");
            q.setParameter("cedula", cedula);
            return q.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula clieente " + e.getMessage());
        }
    }

    public Solicitud findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Solicitud.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Solicitud) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " + e.getMessage());
        }

    }

}
