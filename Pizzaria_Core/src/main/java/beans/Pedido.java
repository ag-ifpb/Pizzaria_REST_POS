/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Jannayna
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    private int numero;
    private int qtde;
    @OneToOne
    private Produto produto;
    private boolean entregue;
    private int numeroComanda;

    public Pedido() {
    }

    public Pedido(int numero, int qtde, Produto produto, boolean entregue, int numeroComanda) {
        this.numero = numero;
        this.qtde = qtde;
        this.produto = produto;
        this.entregue = entregue;
        this.numeroComanda = numeroComanda;
    }

    public Pedido(int qtde, Produto produto, boolean entregue, int numeroComanda) {
        this.qtde = qtde;
        this.produto = produto;
        this.entregue = entregue;
        this.numeroComanda = numeroComanda;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

}
