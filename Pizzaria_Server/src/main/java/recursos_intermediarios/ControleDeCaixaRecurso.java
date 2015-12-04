/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_intermediarios;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.ReadableRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class ControleDeCaixaRecurso extends ServerResource{
    
    @Put
    public Representation pagarComanda(Representation r) throws IOException, JSONException {
        String numeroComanda = (String) getRequestAttributes().get("numeroComanda");

        JsonRepresentation jsonRep = new JsonRepresentation(r);
        JSONObject json = jsonRep.getJsonObject();

        // atualizar caixa
        ClientResource resource = new ClientResource("http://localhost:80/pizzariaBasico/caixa/" + numeroComanda);
        JsonRepresentation repr = new JsonRepresentation(json);
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(repr);

        // pagar comanda
        ClientResource resource2 = new ClientResource("http://localhost:80/pizzariaBasico/pedido/pagar/" + numeroComanda);
        JsonRepresentation repr2 = new JsonRepresentation(json);
        ReadableRepresentation rp2 = (ReadableRepresentation) resource2.delete();

        return new StringRepresentation(rp2.getText());
    }
}
