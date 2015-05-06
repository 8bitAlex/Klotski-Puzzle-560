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
//		KlotskiSolver solver = new KlotskiSolver(new KlotskiPuzzle());
//		solver.solve(true);
		
		KlotskiPuzzle puzzle = new KlotskiPuzzle();
		
		
		//example, delete or comment out if you want
		puzzle.printPuzzle();
		puzzle.move(4, 1, "F");
		puzzle.move(3, 0, "B");
		puzzle.move(2, 0, "E");
		puzzle.move(2, 2, "H");
		puzzle.move(3, 2, "D");	//illegal move
		puzzle.move(4, 2, "I");
		puzzle.move(4, 3, "D");	//illegal move
		puzzle.move(3, 3, "D");
		puzzle.undo();
		puzzle.undo();
		puzzle.printPuzzle();
		System.out.println(puzzle.isSolved());
		System.out.println(puzzle.getGridCode());
	}
}
