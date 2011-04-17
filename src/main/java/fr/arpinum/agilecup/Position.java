package fr.arpinum.agilecup;

public class Position {

	private final int ligne;
	private final int colonne;

	public Position(final int ligne, final int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public Position auSud() {
		return new Position(ligne + 1, colonne);
	}

	public Position aLEst() {
		return new Position(ligne, colonne + 1);
	}

	public Position auNord() {
		return new Position(ligne - 1, colonne);
	}

	public Position aLOuest() {
		return new Position(ligne, colonne - 1);
	}

}
