/**
 * @author Alex Salerno
 * @author John Stoddard
 * @author Arron McCarter
 * @version 0.1
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KlotskiPuzzle puzzle = new KlotskiPuzzle();
		KlotskiSolver solver = new KlotskiSolver(puzzle);
		solver.solve();
	}
}
