import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Alex Salerno
 * @author John Stoddard
 * @author Arron McCarter
 * @version 0.1
 *
 */

public class KlotskiPuzzle {
	static final String EMPTY = "0";	//represents an empty space in the grid
	static final int GRID_WIDTH = 5;
	static final int GRID_HEIGHT = 4;
	
	String[][] grid = new String[GRID_WIDTH][GRID_HEIGHT];
	Map<String, Block> blocks = new Hashtable<String, Block>(); 
	
	public KlotskiPuzzle(){
		initBlocks();
		initConfiguration();
	}
	
	public void solve(){
		debug();
		//TODO solve puzzle
		//TODO output solution
	}
	
	/*
	 * @param x - x-coord to move to
	 * @param y - y-coord to move to
	 * @param b - Block being moved
	 */
	public Boolean move(int x, int y, Block b){
		//TODO move piece
		return isCollision(x,y,b);
	}
	
	public void debug(){
		for(int i=0; i<GRID_WIDTH;i++){
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println(blocks);
	}
	
	/*	=================================================
	 * 	Private Functions
	 *  =================================================
	 */ 
	
	private void initBlocks(){
		//Initializes block to default sizes
		//new Block(Height,Weight,Name)
		blocks.put("A",new Block(1,2,"A"));
		blocks.put("B",new Block(1,2,"B"));
		blocks.put("C",new Block(1,2,"C"));
		blocks.put("D",new Block(1,2,"D"));
		blocks.put("E",new Block(2,1,"E"));
		blocks.put("F",new Block(1,1,"F"));
		blocks.put("G",new Block(1,1,"G"));
		blocks.put("H",new Block(1,1,"H"));
		blocks.put("I",new Block(1,1,"I"));
		blocks.put("J",new Block(2,2,"J"));
	}
	
	private void initConfiguration(){
		grid[0][0] = "A";
		grid[1][0] = "A";
		grid[2][0] = "B";
		grid[3][0] = "B";
		grid[4][0] = "F";
		grid[0][1] = "J";
		grid[1][1] = "J";
		grid[2][1] = "E";
		grid[3][1] = "G";
		grid[4][1] = EMPTY;
		grid[0][2] = "J";
		grid[1][2] = "J";
		grid[2][2] = "E";
		grid[3][2] = "H";
		grid[4][2] = EMPTY;
		grid[0][3] = "C";
		grid[1][3] = "C";
		grid[2][3] = "D";
		grid[3][3] = "D";
		grid[4][3] = "I";
	}
	
	/*
	 * @param x - x-coord to move to
	 * @param y - y-coord to move to
	 * @param b - Block being moved
	 */
	private Boolean isCollision(int x, int y, Block b){
		if(grid[x][y].equals(EMPTY) || grid[x][y].equals(b.name)){
			//check for width collision
			for(int i=1;i<b.width;i++){
				if(!grid[x+i][y].equals(EMPTY) && !grid[x+i][y].equals(b.name)){
					return true;
				}
			}
			//check for height collision
			for(int i=1;i<b.height;i++){
				if(!grid[x][y+1].equals(EMPTY) && !grid[x][y+1].equals(b.name)){
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	private Boolean isSolved(){
		return false;
	}
}
