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
public class Caixa implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private float saldo;

    public Caixa() {
    }

    public Caixa(int id, float saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public Caixa(float saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Caixa{" + "id=" + id + ", saldo=" + saldo + '}';
    }

}
