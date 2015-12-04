/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_basicos;

import beans.BancoDePagamento;
import beans.Duplicata;
import beans.Promissoria;
import beans.Status;
import daos.ContaDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.ReadableRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class ContasAPagarRecurso extends ServerResource {

    private ContaDao contaDao = new ContaDao();

    @Post
    public Representation criarContaAPagar(Representation r) throws IOException, JSONException {
        String tipoConta = (String) getRequestAttributes().get("tipoConta");
        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();

        Promissoria p = new Promissoria();

        String numero = json.getString("numeroDocumento");
        String dataEmissao = json.getString("dataEmissao");
        String dataPagamento = json.getString("dataPagamento");
        String descricao = json.getString("descricao");
        String fornecedor = json.getString("fornecedor");

        if (tipoConta.equals("promissoria")) {
            p = new Promissoria();
        } else {
            String banco = json.getString("banco");
            Duplicata d = new Duplicata();
            d.setBanco(BancoDePagamento.valueOf(banco));
            p = d;
        }

        p.setNumeroDocumento(numero);
        p.setDataEmissao(dataEmissao);
        p.setDataPagamento(dataPagamento);
        p.setDescricao(descricao);
        p.setFornecedor(fornecedor);
        //p.setStatus(Status.valueOf(status));

        boolean result = contaDao.cadastroConta(p);
        if (result == true) {
            return new StringRepresentation("true");
        } else {
            return new StringRepresentation("false");
        }
    }

    @Get
    public Representation listarContas() throws JSONException {
        String param = (String) getRequestAttributes().get("param");

        List<Promissoria> contas = new ArrayList<>();

        if (param.equals("PAGO") || (param.equals("VENCIDO")) || (param.equals("A_VENCER"))) {
            contas = contaDao.listarContasPorStatus(Status.valueOf(param));
        } else {
            contas = contaDao.listarContasPorFonecedor(param);
        }

        JSONArray jsonLista = new JSONArray();

        for (Promissoria c : contas) {
            JSONObject json = new JSONObject();
            json.put("numeroDocumento", c.getNumeroDocumento());
            json.put("dataEmissao", c.getDataEmissao());
            json.put("dataPagamento", c.getDataPagamento());
            json.put("descricao", c.getDescricao());
            json.put("status", c.getStatus());
            json.put("fornecedor", c.getFornecedor());
            if (c.getClass().equals(Duplicata.class)) {
                Duplicata d = (Duplicata) c;
                json.put("banco", d.getBanco());
            }
            jsonLista.put(json);
        }

        return new JsonRepresentation(jsonLista);
    }

    @Put
    public Representation baixarConta(Representation r) throws IOException, JSONException {
        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();
        String numeroDocumento = json.getString("numeroDocumento");

        boolean result = contaDao.baixarConta(numeroDocumento);
        if(result == true){
            return new StringRepresentation("true");
        } else {
            return new StringRepresentation("false");
        }        
    }
}
