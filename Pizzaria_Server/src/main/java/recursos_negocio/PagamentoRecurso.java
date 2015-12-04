/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_negocio;

import java.io.IOException;
import org.json.JSONException;
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
public class PagamentoRecurso extends ServerResource {

    @Put
    public Representation pagarComanda(Representation r) throws IOException, JSONException {
        String numeroComanda = (String) getRequestAttributes().get("numeroComanda");

        JsonRepresentation jsonRep = new JsonRepresentation(r);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaIntermediario/controlecaixa/" + numeroComanda);
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);
        return new StringRepresentation(rp.getText());
    }
}
