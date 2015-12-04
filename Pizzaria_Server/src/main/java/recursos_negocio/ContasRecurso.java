/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_negocio;

import beans.Duplicata;
import beans.Promissoria;
import beans.Status;
import daos.ContaDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.ReadableRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class ContasRecurso extends ServerResource {

    @Get
    public Representation listarContas() throws JSONException, IOException {
        String param = (String) getRequestAttributes().get("param");

        ClientResource resource = new ClientResource("http://localhost:80/pizzariaBasico/contasapagar/listar/" + param);
        ReadableRepresentation rp = (ReadableRepresentation) resource.get();

        System.out.println(rp.getText());
        return new StringRepresentation("Listando contas...");
    }
}
