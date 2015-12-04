/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_basicos;

import beans.Produto;
import daos.ProdutoDao;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class ProdutoRecurso extends ServerResource {

    private ProdutoDao produtoDao = new ProdutoDao();

    @Put
    public Representation baixarEstoque(Representation r) throws IOException, JSONException {
        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();
        String produto = json.getString("produto");
        int qtde = json.getInt("qtde");

        produtoDao.baixaNoEstoque(produto, qtde);
        return new StringRepresentation("Baixa no estoque!");

    }

    @Post
    public Representation cadastrarProduto(Representation r) throws IOException, JSONException {
        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();

        Produto p = new Produto();

        String tipo = json.getString("tipoProduto");
        int qtde = json.getInt("qtde");
        float preco = (float) json.getDouble("preco");

        p.setTipo(tipo);
        p.setPreco(preco);
        p.setQtdeEstoque(qtde);

        boolean retorno = produtoDao.cadastroNoEstoque(p);
        if (retorno == true) {
            return new StringRepresentation("true");
        } else {
            return new StringRepresentation("false");
        }
    }
}
