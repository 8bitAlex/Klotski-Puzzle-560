import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	KlotskiPuzzle puzzle;
	Set<String> pastGrid = new HashSet<String>();
	
	public KlotskiSolver(KlotskiPuzzle puzzle){
			pastGrid.add(puzzle.getGridCode());
			this.puzzle = puzzle;
	}
	
	public void solve(){
		solve(false);
	}
	
	public void solve(Boolean verbose){
		Calendar cal = Calendar.getInstance();
    	long start = cal.getTimeInMillis();
		if(verbose) System.out.println("Solving puzzle...");
		findValidPath(puzzle.getGridCode(),verbose);
		long end = cal.getTimeInMillis();
		long duration = (end - start)/1000;
		System.out.println("Duration: " + duration + "s");
	}
	
	private void printSolution(){
		
	}
	
	//Root is the grids gridCode
	private void findValidPath(String rootCode, Boolean verbose){
		if(verbose) System.out.println("Finding optimal solution");
		Queue<String> grids = new LinkedList<String>();
		int moveCount = 0;
		
		grids.add(rootCode);
		pastGrid.add(rootCode);
		
		while(!grids.isEmpty()){
			String current = grids.remove();
			if(isSolved(current)){
				if(verbose) System.out.println("Solution found in " + moveCount + " tries!");
				puzzle = new KlotskiPuzzle(current);
				puzzle.printPuzzle();
				break;
			}
			String[] nextGrid = findAllMoves(current, verbose);
			for(String g : nextGrid){
				if(verbose) System.out.println(moveCount + "\tAdding grid " + g + " to queue. ");
				grids.add(g);
				pastGrid.add(g);
			}
			moveCount++;
//			if((moveCount % 10000) == 0) {
//				clearConsole();
//				KlotskiPuzzle p = new KlotskiPuzzle(current);
//				p.printPuzzle();
//			};
		}
	}
	
	private String[] findAllMoves(String gridCode,Boolean verbose){
		if(verbose) System.out.println("\tFinding all moves for " + gridCode);
		String[] blocks = KlotskiPuzzle.BLOCK_NAMES;
		List<String> results = new ArrayList<String>();
		KlotskiPuzzle p;
		
		for(String s: blocks){
			for(int i=0; i<KlotskiPuzzle.GRID_WIDTH;i++){
				for(int j=0;j<KlotskiPuzzle.GRID_HEIGHT;j++){
					p = new KlotskiPuzzle(gridCode);
					if(p.move(i, j, s)){
						if(!pastGrid.contains(p.getGridCode())) results.add(p.getGridCode());
					}
				}
			}
		}
		
		String[] resultsArray = new String[ results.size() ];
		return results.toArray(resultsArray);
	}
	
	private Boolean isSolved(String gridCode){
		KlotskiPuzzle p = new KlotskiPuzzle(gridCode);
		return p.isSolved();
	}
	
	private void clearConsole(){
		for(int i=0; i<1000; i++) System.out.println("\b") ;
	}

}
