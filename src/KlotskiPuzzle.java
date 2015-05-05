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
	static final String EMPTY = "0";
	
	String[][] grid;
	Map<String, Block> blocks = new Hashtable<String, Block>(); 
	
	public KlotskiPuzzle(){
		initBlocks();
	}
	
	public void solve(){
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
