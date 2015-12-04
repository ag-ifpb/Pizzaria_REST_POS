/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Promissoria;
import beans.Status;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jannayna
 */
public class ContaDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();

    public boolean cadastroConta(Promissoria p) {
        em.getTransaction().begin();
        
        Query q = em.createQuery("select p from Promissoria p where p.numeroDocumento = :num");
        q.setParameter("num", p.getNumeroDocumento());
        List<Promissoria> lista = q.getResultList();
        
        if(!lista.isEmpty()){
            em.getTransaction().commit();
            return false;
        }
        
        p.setStatus(Status.A_VENCER);
        em.persist(p);
        em.getTransaction().commit();
        return true;
    }

    public List<Promissoria> listarContasPorFonecedor(String fornecedor) {
        List<Promissoria> lista = new ArrayList<>();

        em.getTransaction().begin();
        Query q = em.createQuery("select p from Promissoria p where p.fornecedor = :f");
        q.setParameter("f", fornecedor);
        lista = q.getResultList();
        em.getTransaction().commit();

        return lista;
    }

    public List<Promissoria> listarContasPorStatus(Status status) {
        List<Promissoria> lista = new ArrayList<>();

        em.getTransaction().begin();
        Query q = em.createQuery("select p from Promissoria p where p.status = :s");
        q.setParameter("s", status);
        lista = q.getResultList();
        em.getTransaction().commit();

        return lista;
    }
    
    public boolean baixarConta(String numero){
        em.getTransaction().begin();
        
        Query q = em.createQuery("select p from Promissoria p where p.numeroDocumento = :num");
        q.setParameter("num", numero);
        List<Promissoria> lista = q.getResultList();
        
        if(lista.isEmpty()){
            em.getTransaction().commit();
            return false;
        }
        
        Promissoria promissoria = em.find(Promissoria.class, numero);
        promissoria.setStatus(Status.PAGO);
        em.merge(promissoria);
        em.getTransaction().commit();
        return true;
    }

}
