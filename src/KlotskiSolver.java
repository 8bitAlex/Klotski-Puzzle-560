import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Alex Salerno
 * @author John Stoddard
 * @author Arron McCarter
 * @author Chris Parsoneault
 * @version 0.1
 *
 */

public class KlotskiSolver {
	
	Set<String> pastGrid = new HashSet<String>();
	KlotskiPuzzle puzzle;
	int counter = 0;
	
	public KlotskiSolver(KlotskiPuzzle puzzle){
			pastGrid.add(puzzle.getGridCode());
			this.puzzle = puzzle;
			System.out.println(puzzle.getGridCode());
	}
	
	public void solve(){
		solve(false);
	}
	
	public void solve(Boolean verbose){
		if(verbose) System.out.println("Solving puzzle...");
		findValidPath(puzzle.getGridCode(),verbose);
	}
	
	private void printSolution(){
		
	}
	
	//Root is the grids gridCode
	private void findValidPath(String rootCode, Boolean verbose){
		if(verbose) System.out.println("Finding optimal solution");
		Queue<String> grids = new LinkedList<String>();
		grids.add(rootCode);
		pastGrid.add(rootCode);
		
		while(!grids.isEmpty()){
			String current = grids.remove();
			if(isSolved(current)) break;
			String[] nextGrid = findAllMoves(current);
			System.out.println("Before for(String g: nextGrid)");
			for(String g : nextGrid){
				if(!pastGrid.contains(g)){
					if(verbose) {
						System.out.println("Adding grid " + g + " to queue.");
						KlotskiPuzzle z = new KlotskiPuzzle(g);
						z.printPuzzle();
					}
					grids.add(g);
					pastGrid.add(g);
				} else {
					;
				}
			}
			counter++;
			System.out.println(counter);
			System.out.println("After for(String g: nextGrid)");
		}
	}
	
	private String[] findAllMoves(String gridCode){
		String[] blocks = KlotskiPuzzle.BLOCK_NAMES;
		List<String> results = new ArrayList<String>();
		for(String s: blocks){
			for(int i=0; i<KlotskiPuzzle.GRID_WIDTH;i++){
				for(int j=0;i<KlotskiPuzzle.GRID_HEIGHT;j++){
					KlotskiPuzzle p = new KlotskiPuzzle(gridCode);
					if(p.move(i, j, s)){
						results.add(p.getGridCode());
					}
				}
			}
		}
		System.out.println("Exiting findAllMoves");
		String[] resultsArray = new String[ results.size() ];
		//return next move grid code
		return results.toArray(resultsArray);
	}
	
	private Boolean isSolved(String gridCode){
		KlotskiPuzzle p = new KlotskiPuzzle(gridCode);
		return p.isSolved();
	}

}
