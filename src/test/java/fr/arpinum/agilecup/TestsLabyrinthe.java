package fr.arpinum.agilecup;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestsLabyrinthe {

	@Test
	public void laMatrixALaBonneTaille() {
		final Labyrinthe labyrinthe = new Labyrinthe("kjkl", "#I#\n#O#");

		final String[][] matrix = labyrinthe.getMatrix();

		assertThat(matrix.length, is(2));
		assertThat(matrix[0].length, is(3));
	}

	@Test
	public void peutParserLaMatrix() {
		final Labyrinthe labyrinthe = new Labyrinthe("kjkl", "#I#\n#O#");

		final String[][] matrix = labyrinthe.getMatrix();

		assertThat(matrix, is(new String[][] { new String[] { "#", "I", "#" },
				new String[] { "#", "O", "#" } }));
	}

	@Test
	public void peutRésoudreLePremierLabyrinthe() {
		testLabyrinthe("01", "#I#\n#O#", "S");
	}

	@Test
	public void peutRésoudreLeDeuxièmeLabyrinthe() {
		testLabyrinthe("02", "##\nIO\n##", "E");

	}

	@Test
	public void peutRésoudreLeTroisièmeLabyrinthe() {
		testLabyrinthe("03", "#O#\n#I#", "N");
	}

	@Test
	public void peurRésoudreLeQuatrièmeLabyrinthe() {
		testLabyrinthe("04", "##\nOI\n##", "W");
	}

	@Test
	public void laby05() {
		testLabyrinthe("05", "#I#\n#.O\n###", "SE");
	}

	@Test
	public void testLaby07() {
		testLabyrinthe("07", "#I###\n#.#.O\n#...#\n#####", "SSEENE");
	}

	@Test
	public void testLaby08() {
		testLabyrinthe("08", "#I#\n###\n#0#", "I");
	}

	private void testLabyrinthe(final String numéro, final String laby,
			final String solution) {
		final Labyrinthe labyrinthe = new Labyrinthe(numéro, laby);

		final String résultat = labyrinthe.résoud();

		assertThat(résultat, is(solution));
	}

}
