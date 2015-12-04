/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Jannayna
 */
@Entity
public class Duplicata extends Promissoria implements Serializable {

    @Enumerated(EnumType.STRING)
    private BancoDePagamento banco;

    public Duplicata() {
    }

    public Duplicata(BancoDePagamento banco) {
        this.banco = banco;
    }

    public Duplicata(BancoDePagamento banco, String numeroDocumento, String dataEmissao, String dataPagamento, String descricao, Status status, String fornecedor) {
        super(numeroDocumento, dataEmissao, dataPagamento, descricao, status, fornecedor);
        this.banco = banco;
    }

    public BancoDePagamento getBanco() {
        return banco;
    }

    public void setBanco(BancoDePagamento banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Duplicata{" + "banco=" + banco + '}';
    }

}
