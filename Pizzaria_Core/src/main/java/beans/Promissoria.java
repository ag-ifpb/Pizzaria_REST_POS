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
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jannayna
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Promissoria implements Serializable {

    @Id
    private String numeroDocumento;
    private String dataEmissao;
    private String dataPagamento;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String fornecedor;

    public Promissoria() {
    }

    public Promissoria(String numeroDocumento, String dataEmissao, String dataPagamento, String descricao, Status status, String fornecedor) {
        this.numeroDocumento = numeroDocumento;
        this.dataEmissao = dataEmissao;
        this.dataPagamento = dataPagamento;
        this.descricao = descricao;
        this.status = status;
        this.fornecedor = fornecedor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Conta{" + "numeroDocumento=" + numeroDocumento + ", dataEmissao=" + dataEmissao + ", dataPagamento=" + dataPagamento + ", descricao=" + descricao + ", status=" + status + ", fornecedor=" + fornecedor + '}';
    }

}
