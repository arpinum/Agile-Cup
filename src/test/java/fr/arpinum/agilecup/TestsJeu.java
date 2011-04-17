package fr.arpinum.agilecup;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;

public class TestsJeu {

	private AgileCup agileCup;
	private Jeu jeu;
	private JSONObject jsonOk;

	@Before
	public void avant() throws JSONException, IOException {
		agileCup = mock(AgileCup.class);
		jeu = new Jeu(agileCup);
		jsonOk = new JSONObject();
		jsonOk.put("success", "ok");
		jsonOk.put("expiry", "exp");
		when(agileCup.envoieRéponse(any(Labyrinthe.class))).thenReturn(
				new JsonRepresentation(jsonOk));
		when(agileCup.envoieRéponse(any(Labyrinthe.class), anyString()))
				.thenReturn(new JsonRepresentation(jsonKo()));
		when(agileCup.labyrinthe(anyString())).thenReturn(
				new Labyrinthe("01", "plop"));
		when(agileCup.premierLabyrinthe()).thenReturn(
				new Labyrinthe("01", "plop"));

	}

	@Test
	public void peutDemanderLePremireLabyrinthe() throws IOException,
			JSONException {
		jeu.tourne();

		verify(agileCup).premierLabyrinthe();
	}

	@Test
	public void envoieLaRéponseEtDemandeLeSuivant() throws JSONException,
			IOException {
		final Labyrinthe laby = new Labyrinthe("01", "");
		when(agileCup.premierLabyrinthe()).thenReturn(laby);

		jeu.tourne();

		verify(agileCup).envoieRéponse(laby);
		verify(agileCup).labyrinthe("02");
	}

	private JSONObject jsonKo() throws JSONException {
		final JSONObject jsonKo = new JSONObject();
		jsonKo.put("success", "error");
		return jsonKo;
	}

	@Test
	public void tenteDeRésoudreLaSuiteSiCEstBon() {

	}
}
