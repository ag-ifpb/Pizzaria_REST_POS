/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_basicos;

import beans.Pedido;
import daos.PedidoDao;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.ReadableRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class PedidoRecurso extends ServerResource {

    PedidoDao pedidoDao = new PedidoDao();

    @Post
    public Representation criarPedidoEmBranco(Representation r) throws IOException, JSONException {
        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();

        int numeroComanda = json.getInt("numeroComanda");
        Pedido pedido = new Pedido();
        pedido.setNumeroComanda(numeroComanda);

        boolean result = pedidoDao.novoPedido(pedido);
        if (result == true) {
            return new StringRepresentation("true");
        } else {
            return new StringRepresentation("false");
        }
    }

    @Put
    public Representation fazerPedido_entregarPedido(Representation r) throws IOException, JSONException {
        String numeroComanda = (String) getRequestAttributes().get("numeroComanda");

        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();
        String produto = json.getString("produto");
        int qtde = json.getInt("qtde");

        if (!json.has("status")) {
            boolean result = pedidoDao.fazerPedido(Integer.parseInt(numeroComanda), produto, qtde);
            if (result == true) {
                return new StringRepresentation("true");
            } else {
                return new StringRepresentation("false");
            }
        } else {
            boolean result = pedidoDao.entregarPedido(Integer.parseInt(numeroComanda), produto);
            if (result == true) {
                return new StringRepresentation("true");
            } else {
                return new StringRepresentation("false");
            }
        }

    }

    @Delete
    public Representation pagarComanda() throws IOException, JSONException {
        String numeroComanda = (String) getRequestAttributes().get("numeroComanda");

        boolean result = pedidoDao.pagarComanda(Integer.parseInt(numeroComanda));

        if (result == true) {
            return new StringRepresentation("true");
        } else {
            return new StringRepresentation("false");
        }

    }
}
