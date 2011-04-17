package fr.arpinum.agilecup;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONTokener;
import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.ext.json.JsonRepresentation;

public class AgileCup {

	public Labyrinthe premierLabyrinthe() throws JSONException {
		return labyrinthe("01");
	}

	public Labyrinthe labyrinthe(final String numéro) throws JSONException {
		final Request request = new Request(Method.GET,
				"http://beta.agilecup.org/problem/" + numéro + "?key="
						+ Reference.encode(KEY));
		final Response response = traiteRequête(request);

		return new Labyrinthe(numéro, new JSONTokener(
				response.getEntityAsText()).nextValue().toString());

	}

	public JsonRepresentation envoieRéponse(final Labyrinthe labyrinthe)
			throws IOException {
		final Form form = formulaireRéponse(labyrinthe);
		return envoieRéponse(labyrinthe, form);
	}

	public JsonRepresentation envoieRéponse(final Labyrinthe labyrinthe,
			final String expiry) throws IOException {
		final Form form = formulaireRéponse(labyrinthe);
		form.add("expiry", Reference.decode(expiry));
		return envoieRéponse(labyrinthe, form);
	}

	private Form formulaireRéponse(final Labyrinthe labyrinthe) {
		final Form form = new Form();
		form.add("key", KEY);
		form.add("solution", labyrinthe.résoud());
		return form;
	}

	private JsonRepresentation envoieRéponse(final Labyrinthe labyrinthe,
			final Form form) throws IOException {
		final Request request = new Request(Method.POST,
				"http://beta.agilecup.org/solution/" + labyrinthe.getNuméro());
		request.setEntity(form.getWebRepresentation());
		final Response response = traiteRequête(request);
		return new JsonRepresentation(response.getEntity());
	}

	private Response traiteRequête(final Request request) {
		return new Client(Protocol.HTTP).handle(request);
	}

	private static final String KEY = Reference
			.decode("vCYVFJtCSzAbL9xcqPIcdQ%3D%3D");

}
