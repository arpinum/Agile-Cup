package fr.arpinum.agilecup;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;

public class TestsAgileCup {

	@Test
	public void peutRécupérerLePremierLabyrinthe() throws JSONException {
		final AgileCup agileCup = new AgileCup();

		final Labyrinthe laby = agileCup.premierLabyrinthe();

		assertThat(laby.toString(), is("#I#\n#O#"));
	}

	@Test
	public void peutEnvoyerLeRésultat() throws JSONException, IOException {
		final AgileCup agileCup = new AgileCup();
		final Labyrinthe laby = agileCup.premierLabyrinthe();

		final JsonRepresentation json = agileCup.envoieRéponse(laby);

		final JSONObject jsonObject = json.getJsonObject();
		assertThat(jsonObject, notNullValue());
	}

}
