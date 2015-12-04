/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_negocio;

import beans.Pedido;
import beans.Produto;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.ReadableRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class ComandaRecurso extends ServerResource {

    @Post
    public Representation abrirComanda(Representation r) throws IOException, JSONException {
        JsonRepresentation jsonRep = new JsonRepresentation(r);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaBasico/pedido");
        ReadableRepresentation rp = (ReadableRepresentation) resource.post(jsonRep);

        return new StringRepresentation(rp.getText());
    }

    @Put
    public Representation fazerPedido(Representation r) throws IOException, JSONException {
        String numeroComanda = (String) getRequestAttributes().get("numeroComanda");

        JsonRepresentation jsonRep = new JsonRepresentation(r);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaBasico/pedido/" + numeroComanda);
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);
        return new StringRepresentation(rp.getText());
    }

}
