package fr.arpinum.agilecup;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class Jeu {

	private final AgileCup agileCup;

	public static void main(final String[] args) throws IOException,
			JSONException {
		while (true) {
			new Jeu(new AgileCup()).tourne();
		}
	}

	public Jeu(final AgileCup agileCup) {
		this.agileCup = agileCup;
	}

	public void tourne() throws IOException, JSONException {
		System.out.println("Début");
		Labyrinthe labyrinthe = agileCup.premierLabyrinthe();
		logLabyrinthe(labyrinthe);
		JSONObject jsonObject = agileCup.envoieRéponse(labyrinthe)
				.getJsonObject();
		while ("ok".equals(jsonObject.getString("success"))) {
			final String expiry = jsonObject.getString("expiry");
			labyrinthe = agileCup.labyrinthe(numéroSuivant());
			logLabyrinthe(labyrinthe);
			jsonObject = agileCup.envoieRéponse(labyrinthe, expiry)
					.getJsonObject();
		}
		System.out.println("Fin");
	}

	private String numéroSuivant() {
		++numéroCourant;
		if (numéroCourant > 9) {
			return String.valueOf(numéroCourant);
		}
		return String.format("0%s", numéroCourant);
	}

	private void logLabyrinthe(final Labyrinthe labyrinthe) {
		System.out.println(labyrinthe.getNuméro());
		System.out.println(labyrinthe.toString());
		System.out.println(labyrinthe.résoud());
	}

	private int numéroCourant = 1;
}
