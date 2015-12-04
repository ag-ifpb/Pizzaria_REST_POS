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
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class ContaRecurso extends ServerResource {

    @Post
    public Representation cadastrarConta(Representation r) throws IOException, JSONException {
        String tipoConta = (String) getRequestAttributes().get("tipoConta");
        JsonRepresentation jsonRep = new JsonRepresentation(r);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaBasico/contasapagar/" + tipoConta);
        ReadableRepresentation rp = (ReadableRepresentation) resource.post(jsonRep);

        return new StringRepresentation(rp.getText());
    }

    @Put
    public Representation baixarConta(Representation r) throws IOException, JSONException {
        JsonRepresentation jsonRep = new JsonRepresentation(r);

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaBasico/contasapagar");
        ReadableRepresentation rp = (ReadableRepresentation) resource.put(jsonRep);
        return new StringRepresentation(rp.getText());
    }
}
