import java.util.Hashtable;
import java.util.Map;

/**
 * @author Alex Salerno
 * @author John Stoddard
 * @author Aaron McCarter
 * @version 0.1
 *
 */

public class KlotskiPuzzle {
	String[][] grid;
	Map<String, Block> blocks = new Hashtable<String, Block>(); 
	
	public KlotskiPuzzle(){
		initBlocks();
	}
	
	public void solve(){
		//TODO solve puzzle
		//TODO output solution
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
	
	private Boolean isCollision(){
		return false;
	}
}
