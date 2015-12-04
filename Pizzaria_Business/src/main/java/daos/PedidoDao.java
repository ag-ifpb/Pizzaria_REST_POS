/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Pedido;
import beans.Produto;
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
public class PedidoDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();

    Pedido pedido;

    public boolean novoPedido(Pedido p) {
        em.getTransaction().begin();

        Query q = em.createQuery("select p from Pedido p where p.numeroComanda = :num");
        q.setParameter("num", p.getNumeroComanda());

        List<Pedido> lista = q.getResultList();
        if (lista.isEmpty()) {
            em.persist(p);
            em.getTransaction().commit();
            return true;
        } else {
            em.getTransaction().commit();
            return false;
        }
    }

    public boolean fazerPedido(int numeroComanda, String produto, int qtde) {
        em.getTransaction().begin();

        Query q = em.createQuery("select p from Pedido p where p.numeroComanda = :num");
        q.setParameter("num", numeroComanda);

        List<Pedido> lista = q.getResultList();
        
        q = em.createQuery("select p from Produto p where p.tipo = :tipo");
        q.setParameter("tipo", produto);
        
        Produto prod = (Produto) q.getSingleResult();

        if (lista.isEmpty()) {
            em.getTransaction().commit();
            return false;
        }

        Pedido pedido = new Pedido(qtde, prod, false, numeroComanda);

        em.persist(pedido);
        em.getTransaction().commit();
        return true;
    }

    // entregar os itens pedidos ao cliente (alterar status de um pedido para TRUE)    
    public boolean entregarPedido(int numeroComanda, String produto) {
        em.getTransaction().begin();
        Query q = em.createQuery("select p from Produto p where p.tipo = :produto");
        q.setParameter("produto", produto);
        Produto p = (Produto) q.getSingleResult();
        int cod = p.getCod();

        q = em.createQuery("select p from Pedido p where p.produto.cod = :cod and p.numeroComanda = :numeroComanda");
        q.setParameter("cod", cod);
        q.setParameter("numeroComanda", numeroComanda);

        Pedido pedidoAux = (Pedido) q.getSingleResult();
        
        if(pedidoAux.getEntregue() == true){
            em.getTransaction().commit();
            return false;
        }
        
        Pedido pedido = new Pedido();

        pedido = em.find(Pedido.class, pedidoAux.getNumero());
        pedido.setEntregue(true);

        em.merge(pedido);
        em.getTransaction().commit();
        return true;
    }

    public boolean pagarComanda(int numeroComanda) {
        em.getTransaction().begin();
        Query q = em.createQuery("select p from Pedido p where p.numeroComanda = :numeroComanda");
        q.setParameter("numeroComanda", numeroComanda);
        List<Pedido> lista = q.getResultList();
        
        if(lista.isEmpty()){
            em.getTransaction().commit();
            return false;
        }

        for (Pedido p : lista) {
            Pedido pedido = em.find(Pedido.class, p.getNumero());
            em.remove(pedido);
        }

        em.getTransaction().commit();
        return true;
    }
}
