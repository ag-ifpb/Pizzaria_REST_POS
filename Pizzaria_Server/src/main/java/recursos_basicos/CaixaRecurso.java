/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_basicos;

import daos.CaixaDao;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Jannayna
 */
public class CaixaRecurso extends ServerResource {

    private CaixaDao caixaDao = new CaixaDao();

    @Put
    public Representation atualizarCaixa(Representation r) throws IOException, JSONException {
        String numeroComanda = (String) getRequestAttributes().get("numeroComanda");

        caixaDao.atualizaCaixa(Integer.parseInt(numeroComanda));
        return new StringRepresentation("Conta baixa!");

    }
}
