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
    	
		System.out.println("Solving puzzle...");
		
		//compute solution
		findValidPath(puzzle.getGridCode(),verbose);
		
		//calculate duration to solve
		long end = cal.getTimeInMillis();
		long duration = (end - start)/1000;
		System.out.println("Duration: " + duration + "s");
		
		printSolution();
		puzzle.printPuzzle();
	}
	
	private void printSolution(){
		if(puzzle.isSolved()){
			puzzle.printMoves();
		} else {
			System.out.println("The puzzle is not solved yet");
		}
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
			
//			KlotskiPuzzle z = new KlotskiPuzzle(current);
//			z.printPuzzle();
			
			if(isSolved(current)){
				if(verbose) System.out.println("Solution found in " + moveCount + " tries!");
				puzzle = new KlotskiPuzzle(current);
				//puzzle.printPuzzle();
				break;
			}
			String[] nextGrid = findAllMoves(current, verbose);
			for(String g : nextGrid){
				if(pastGrid.contains(g)) continue;
				if(verbose) System.out.println(moveCount + "\tAdding grid " + g + " to queue. ");
				grids.add(g);
				pastGrid.add(g);
			}
			moveCount++;
//			if(moveCount % 10000 == 0){
//				KlotskiPuzzle z = new KlotskiPuzzle(current);
//				z.printPuzzle();
//			}
			
		}
	}
	
	private String[] findAllMoves(String gridCode,Boolean verbose){
		if(verbose) System.out.println("\tFinding all moves for " + gridCode);
		//String[] blocks = KlotskiPuzzle.BLOCK_NAMES;
		List<String> results = new ArrayList<String>();
		KlotskiPuzzle p = new KlotskiPuzzle(gridCode);
		
		//Iterate through each column
		for(int i=0; i<KlotskiPuzzle.GRID_WIDTH;i++){
			for(int j=0;j<KlotskiPuzzle.GRID_HEIGHT;j++){
				if(p.getGrid(i,j).equals(KlotskiPuzzle.EMPTY)){
					String moveBlock = "";
					Move blockPos = null;
					
					//look up, check if you can move it down
					moveBlock = p.getGrid(i, j-1);
					blockPos = p.getBlockPos(p.getBlock(moveBlock));
					if(blockPos != null && p.move(blockPos.x, blockPos.y+1,moveBlock)){
						results.add(p.getGridCode());
						p = new KlotskiPuzzle(gridCode);
					}
					
					//look left, check if you can move it right
					moveBlock = p.getGrid(i-1, j);
					blockPos = p.getBlockPos(p.getBlock(moveBlock));
					if(blockPos != null && p.move(blockPos.x+1, blockPos.y,moveBlock)){
						results.add(p.getGridCode());
						p = new KlotskiPuzzle(gridCode);
					}
					
					//look right, check if you can move it left
					moveBlock = p.getGrid(i+1, j);
					blockPos = p.getBlockPos(p.getBlock(moveBlock));
					if(blockPos != null && p.move(blockPos.x-1, blockPos.y,moveBlock)){
						results.add(p.getGridCode());
						p = new KlotskiPuzzle(gridCode);
					}
					
					//look down, check if you can move it up
					moveBlock = p.getGrid(i, j+1);
					blockPos = p.getBlockPos(p.getBlock(moveBlock));
					if(blockPos != null && p.move(blockPos.x, blockPos.y-1,moveBlock)){
						results.add(p.getGridCode());
						p = new KlotskiPuzzle(gridCode);
					}
				}
			}
		}
		
//		for(String s: blocks){
//			for(int i=0; i<KlotskiPuzzle.GRID_WIDTH;i++){
//				for(int j=0;j<KlotskiPuzzle.GRID_HEIGHT;j++){
//					p = new KlotskiPuzzle(gridCode);
//					if(p.move(i, j, s)){
//						if(!pastGrid.contains(p.getGridCode())) results.add(p.getGridCode());
//					}
//				}
//			}
//		}
		
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
