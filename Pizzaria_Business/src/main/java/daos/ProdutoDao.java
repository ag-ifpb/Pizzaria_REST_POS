/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Produto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jannayna
 */
public class ProdutoDao implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    private EntityManager em = emf.createEntityManager();

    public void baixaNoEstoque(String produto, int qtde) {
        em.getTransaction().begin();

        Query q = em.createQuery("select p from Produto p where p.tipo = :produto");
        q.setParameter("produto", produto);
        Produto p = (Produto) q.getSingleResult();
        int cod = p.getCod();

        Produto prod = em.find(Produto.class, cod);

        if (qtde <= prod.getQtdeEstoque()) {
            prod.setQtdeEstoque(prod.getQtdeEstoque() - qtde);
        } else {
            prod.setQtdeEstoque(0);
        }

        em.merge(prod);
        em.getTransaction().commit();
    }

    public boolean cadastroNoEstoque(Produto p) {
        if (!p.getTipo().equals("Refrigerante")
                && !p.getTipo().equals("Pizza 1 sabor")
                && !p.getTipo().equals("Pizza 2 sabores")
                && !p.getTipo().equals("Salgado")) {
            return false;
        }

        em.getTransaction().begin();
        Query q = em.createQuery("select p from Produto p where p.tipo = :produto");
        q.setParameter("produto", p.getTipo());

        List<Produto> pAux = q.getResultList();

        if (pAux.size() > 0) {
            pAux.get(0).setQtdeEstoque(pAux.get(0).getQtdeEstoque() + p.getQtdeEstoque());
            em.merge(pAux.get(0));
        } else {
            em.persist(p);
        }
        em.getTransaction().commit();
        return true;
    }
}
