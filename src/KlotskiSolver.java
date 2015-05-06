import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Salerno
 * @author John Stoddard
 * @author Arron McCarter
 * @version 0.1
 *
 */

public class KlotskiSolver {
	
	Set<String> pastGrid = new HashSet<String>();
	KlotskiPuzzle puzzle;
	
	public KlotskiSolver(KlotskiPuzzle puzzle){
			pastGrid.add(puzzle.getGridCode());
			this.puzzle = puzzle;
	}
	
	public void solve(){
		solve(false);
	}
	
	public void solve(Boolean verbose){
		if(verbose) System.out.println("Solving puzzle...");
	}
	
	private void printSolution(){
		
	}

}
