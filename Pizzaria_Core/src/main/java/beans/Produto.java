/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Jannayna
 */
@Entity
public class Produto implements Serializable{

    @Id
    @GeneratedValue
    private int cod;
    private String tipo;
    private float preco;
    private int qtdeEstoque;

    public Produto() {
    }

    public Produto(String tipo, float preco, int qtdeEstoque) {
        this.tipo = tipo;
        this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
    }

    public Produto(int cod, String tipo, float preco, int qtdeEstoque) {
        this.cod = cod;
        this.tipo = tipo;
        this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    
}
