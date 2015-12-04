package daos;

import beans.Caixa;
import beans.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jannayna
 */
public class CaixaDao {

    // incrementar o valor do caixa de acordo com o valor total da comanda
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();

    //persiste o objeto Caixa no BD
    public void novoCaixa(Caixa c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    //passa o valor de uma comanda e adiciona ao saldo do Caixa
    public void atualizaCaixa(int numeroComanda) {
        em.getTransaction().begin();
        List<Caixa> caixas = em.createQuery("select c from Caixa c").getResultList();

        Caixa cx = new Caixa();
        if (caixas.isEmpty()) {
            cx.setSaldo(0);
            em.persist(cx);
        } else {
            cx = caixas.get(0);
        }
        Caixa caixa = em.find(Caixa.class, cx.getId());
        float valor = getSaldo(numeroComanda);
        caixa.setSaldo(caixa.getSaldo() + valor);
        em.merge(caixa);

        em.getTransaction().commit();
    }

    public float getSaldo(int numeroComanda) {
        Query q = em.createQuery("select p from Pedido p where p.numeroComanda = :numeroComanda");
        q.setParameter("numeroComanda", numeroComanda);
        List<Pedido> lista = q.getResultList();

        if (lista.isEmpty()) {
            return 0;
        }

        float valor = 0;
        for (Pedido l : lista) {
            if (l.getProduto() == null) {
                valor += 0;
            } else {
                valor += l.getProduto().getPreco() * l.getQtde();
            }
        }
        return valor;
    }
}
