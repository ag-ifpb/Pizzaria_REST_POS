/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.ReadableRepresentation;
import org.restlet.resource.ClientResource;

/**
 *
 * @author Jannayna
 */
public class Cliente_Pizzaria {

    public static void main(String[] args) throws JSONException, IOException {
        //abrirComanda();
        //cadastrarProduto();
        //fazerPedido();
        //entregarItem();
        //cadastrarConta();
        //listarContasPorStatus();
        //listarContasPorFornecedor();    
        //baixarConta();
        //pagarComanda();
    }

    public static boolean abrirComanda(int numeroComanda) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("numeroComanda", numeroComanda);

        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/comanda");
        ReadableRepresentation rp = (ReadableRepresentation) resource.post(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }

    public static boolean fazerPedido(int numeroComanda) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("produto", "Refrigerante");
        json.put("qtde", 2);

        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/comanda/" + numeroComanda);
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }

    public static boolean entregarItem(String produto) throws JSONException, IOException {
        int numeroComanda = 1001;

        JSONObject json = new JSONObject();
        json.put("produto", produto);
        json.put("qtde", 2);
        json.put("status", true);

        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/estoque/" + numeroComanda);
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }

    public static boolean cadastrarConta(String numeroDocumento) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("numeroDocumento", numeroDocumento);
        json.put("dataEmissao", "10/11/2015");
        json.put("dataPagamento", "10/12/2015");
        json.put("descricao", "Cartão de credito");
        json.put("fornecedor", "Elo");
        //json.put("banco", "BB");

        String tipoConta = "";
        if (json.has("banco")) {
            tipoConta = "duplicata";
        } else {
            tipoConta = "promissoria";
        }

        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/conta/" + tipoConta);
        ReadableRepresentation rp = (ReadableRepresentation) resource.post(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }

    public static boolean listarContasPorStatus(String status) throws JSONException, IOException {
        if (!status.equalsIgnoreCase("PAGO")
                && !status.equalsIgnoreCase("VENCIDO")
                && !status.equalsIgnoreCase("A_VENCER")) {
            return false;
        } else {
            ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/contas/" + status);
            ReadableRepresentation rp = (ReadableRepresentation) resource.get();
            //System.out.println(rp.getText());
            return true;
        }
    }

    public static void listarContasPorFornecedor() throws JSONException, IOException {
        String fornecedor = "Elo";
        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/contas/" + fornecedor);
        ReadableRepresentation rp = (ReadableRepresentation) resource.get();

        System.out.println(rp.getText());
    }

    public static boolean cadastrarProduto(String tipoProduto, float preco, int qtde) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("tipoProduto", tipoProduto);
        json.put("preco", preco);
        json.put("qtde", qtde);

        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/estoque");
        ReadableRepresentation rp = (ReadableRepresentation) resource.post(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }

    public static boolean baixarConta(String numeroDocumento) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("numeroDocumento", numeroDocumento);
        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/conta");
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }

    public static boolean pagarComanda(int numeroComanda) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        JsonRepresentation jsonRep = new JsonRepresentation(json);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaNegocio/pagamento/" + numeroComanda);
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);

        return rp.getText().equalsIgnoreCase("true");
    }
}
