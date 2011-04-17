package fr.arpinum.agilecup;

public class Labyrinthe {

	private final String labyrinthe;
	private final String numéro;
	private final String[][] matrix;
	private Position positionDébut;

	public Labyrinthe(final String numéro, final String labyrinthe) {
		this.numéro = numéro;
		this.labyrinthe = labyrinthe;
		final String[] split = labyrinthe.replace("\"", "").split("\n");
		matrix = new String[split.length][split[0].length()];
		for (int ligne = 0; ligne < split.length; ligne++) {
			for (int colonne = 0; colonne < split[ligne].length(); colonne++) {
				final String valeur = split[ligne].charAt(colonne) + "";
				if ("I".equals(valeur)) {
					positionDébut = new Position(ligne, colonne);
				}
				matrix[ligne][colonne] = valeur;
			}
		}
	}

	public String[][] getMatrix() {
		return matrix;
	}

	@Override
	public String toString() {
		return labyrinthe;
	}

	public String résoud() {
		String résultat = "";
		Position positionCourante = positionDébut;
		String dernierMouvement = "";
		while (!"O".equals(getValeur(positionCourante))) {
			if (peutAllerALEst(positionCourante, dernierMouvement)) {
				dernierMouvement = "E";
				positionCourante = positionCourante.aLEst();
			} else if (peutAllerAuNord(positionCourante, dernierMouvement)) {
				dernierMouvement = "N";
				positionCourante = positionCourante.auNord();
			} else if (peutAllerALOuest(positionCourante, dernierMouvement)) {
				dernierMouvement = "W";
				positionCourante = positionCourante.aLOuest();
			} else if (peutAllerAuSud(positionCourante, dernierMouvement)) {
				dernierMouvement = "S";
				positionCourante = positionCourante.auSud();
			} else {
				return "I";
			}
			résultat += dernierMouvement;
		}
		return résultat;
	}

	private boolean peutAllerAuNord(final Position positionCourante,
			final String dernierMouvement) {
		return peutAllerA(positionCourante.auNord())
				&& !dernierMouvement.equals("S");
	}

	private boolean peutAllerALEst(final Position positionCourante,
			final String dernierMouvement) {
		return peutAllerA(positionCourante.aLEst())
				&& !dernierMouvement.equals("W");
	}

	private boolean peutAllerAuSud(final Position positionCourante,
			final String dernierMouvement) {
		return peutAllerA(positionCourante.auSud())
				&& !dernierMouvement.equals("N");
	}

	private boolean peutAllerALOuest(final Position positionCourante,
			final String dernierMouvement) {
		return peutAllerA(positionCourante.aLOuest())
				&& !dernierMouvement.equals("E");
	}

	private boolean peutAllerA(final Position nouvellePosition) {
		return estDansLeLabyrinthe(nouvellePosition)
				&& !"#".equals(getValeur(nouvellePosition))
				&& !"I".equals(getValeur(nouvellePosition));
	}

	private boolean estDansLeLabyrinthe(final Position nouvellePosition) {
		return nouvellePosition.getLigne() >= 0
				&& nouvellePosition.getLigne() < getMatrix().length
				&& nouvellePosition.getColonne() >= 0
				&& nouvellePosition.getColonne() < getMatrix()[0].length;
	}

	private String getValeur(final Position positionCourante) {
		return getMatrix()[positionCourante.getLigne()][positionCourante
				.getColonne()];
	}

	public String getNuméro() {
		return numéro;
	}
}
