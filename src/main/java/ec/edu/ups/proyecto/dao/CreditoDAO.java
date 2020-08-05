/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Credito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class CreditoDAO {

    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public CreditoDAO() {
    }
     
    
    public boolean insert(Credito credito) throws Exception {
        boolean bandera = true;
    	try {
            em.persist(credito);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Credito " + e.getMessage());
        }
        
        return bandera;
    }
     
    public void delete(Credito credito) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(credito.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Credito " +e.getMessage());
        }
    }
        

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Credito " +e.getMessage());
        }
    }
       
    public void update(Credito credito) throws Exception {
        try {
            em.merge(credito);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Credito " +e.getMessage());
        }
    }
    
    public Credito read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Credito.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Credito " +e.getMessage());
        }
    }
   
    public List<Credito> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Credito.findAll");
            List<Credito> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Credito " +e.getMessage());
        }

    }
    
     public List<Credito>  findByCliente(String cedula ) throws Exception {
        try {
            Query q = em.createNamedQuery("Credito.findByCliente");
            q.setParameter("cedula", cedula);
            return  q.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula clieente " +e.getMessage());
        }
    }

    public Credito findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Credito.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Credito) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " +e.getMessage());
        }

    }
    
 

}
