/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Alogin;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Trabajador;
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


    public void insert(Trabajador login) throws Exception {
        try {
            System.out.println("si creo que llega aca");
            em.persist(login);
        } catch (Exception e) {
            throw new Exception("Erro ingreso Trabajador " + e.getMessage());
        }
    }
    

    public List<Alogin> findAll() throws Exception{
        
        try {
            Query q = em.createNamedQuery("Alogin.findAll");
             List<Alogin> lista =q.getResultList();
             return lista;
        } catch (Exception e) {
            throw new Exception("Erro buscar al loguearse");
        }
    }
    
      public void cancel() throws Exception {
        try {
            System.out.println("si creo que llega aca");
            em.close();
        } catch (Exception e) {
            throw new Exception("Erro ingreso Trabajador " + e.getMessage());
        }
    }

}
