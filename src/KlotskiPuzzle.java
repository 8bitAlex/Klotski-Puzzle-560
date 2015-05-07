import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

/**
 * @author Alex Salerno
 * @author Chris Parsoneault
 * @version 0.1
 *
 */

public class KlotskiPuzzle {
	static final String EMPTY = "0";		//an empty space in the grid
	static final String SOLVED_CHAR = "J";	//Block name that meets victory condition
	static final int GRID_WIDTH = 5;
	static final int GRID_HEIGHT = 4;
	static final String DEFAULT_CONFIG = "AJJCAJJCBEEDBGHDF00I"; //AABBF /JJEG0/ JJEH0/ CCDDI
	static final String[] BLOCK_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	
	String[][] grid = new String[GRID_WIDTH][GRID_HEIGHT];
	Map<String, Block> blocks = new Hashtable<String, Block>();
	Stack<String> grids = new Stack<String>();
	Stack<Move> moves = new Stack<Move>();
	
	//CONSTRUCTOR
	public KlotskiPuzzle(){
		init(DEFAULT_CONFIG);
	}
	
	public KlotskiPuzzle(String code){
		init(code);
	}
	
	private void init(String code){
		initBlocks();
		codeToGrid(code);
		grids.push(getGridCode());
	}
	
	/*
	 * @param x - x-coord to move to
	 * @param y - y-coord to move to
	 * @param b - Block being moved
	 */
	public Boolean move(int x, int y, String block){
		Block b = blocks.get(block);
		if(!isValidMove(x,y,b)) return false;
		else {
			replaceBlock(b.name,EMPTY);
			insertBlock(x,y,b);
			grids.push(getGridCode());
			moves.push(new Move(x,y,b));
		}
		return true;
	}
	
	public Boolean isSolved(){
		if(grid[4][1].equals(SOLVED_CHAR) && grid[4][2].equals(SOLVED_CHAR)){
			return true;
		}
		return false;
	}
	
	//Prints out the puzzles current state
	public void printPuzzle(){
		System.out.println("    0 1 2 3 4 ");
		System.out.println("   -----------");
		for(int j=0; j<GRID_HEIGHT;j++){
			System.out.print(j + " | ");
			for(int i=0; i<GRID_WIDTH;i++){
				System.out.print(grid[i][j] + " ");
			}
			if(j==1 || j==2) System.out.print("+");
			else System.out.print("|");
			System.out.println();
		}
		System.out.println("   -----------");
	}
	
	public String getGrid(int x, int y){
		return grid[x][y];
	}
	
	//TODO make more efficient, use Huffman encoding
	public String getGridCode(){
		String code = "";
		for(int i=0;i<GRID_WIDTH;i++){
			for(int j=0;j<GRID_HEIGHT;j++){
				code += grid[i][j];
			}
		}
		return code;
	}
	
	public Stack<Move> getMoves(){
		return moves;
	}
	
	public Boolean undo(){
		if(grids.size() < 2) return false;
		grids.pop();
		moves.pop();
		codeToGrid(grids.peek());
		return true;
	}
	
	public void codeToGrid(String code){
		int s = 0;
		int max = GRID_WIDTH * GRID_HEIGHT;
		for(int i=0;i<GRID_WIDTH;i++){
			for(int j=0;j<GRID_HEIGHT;j++){
				if(s>code.length() || s>=max) break;
				grid[i][j] = Character.toString(code.charAt(s));
				s++;
			}
		}
	}
	
	/*	=================================================
	 * 	Private Functions
	 *  =================================================
	 */ 
	
	//Initializes blocks to default sizes
	private void initBlocks(){
		//new Block(Height,Width,Name)
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
	
//	//Configures grid 
//	private void initConfiguration(){
//		//default configuration
//		grid[0][0] = "A";	grid[0][1] = "J";
//		grid[1][0] = "A";	grid[1][1] = "J";
//		grid[2][0] = "B";	grid[2][1] = "E";
//		grid[3][0] = "B";	grid[3][1] = "G";
//		grid[4][0] = "F";	grid[4][1] = EMPTY;
//		
//		grid[0][2] = "J";	grid[0][3] = "C";
//		grid[1][2] = "J";	grid[1][3] = "C";
//		grid[2][2] = "E";	grid[2][3] = "D";
//		grid[3][2] = "H";	grid[3][3] = "D";
//		grid[4][2] = EMPTY;	grid[4][3] = "I";
//	}
	
	/* If block b will collide with any block return true, else false
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
	
	private Boolean isValidMove(int x, int y, Block b){
		Move c = getBlockMove(b);
		if(x+(b.width-1) >= GRID_WIDTH || y+(b.height-1) >= GRID_HEIGHT) return false;
		if(isCollision(x,y,b)) return false;
		if(x != c.x && y != c.y) return false;
		if(Math.abs(x-c.x) > 1 || Math.abs(y-c.y) > 1) return false;
		return true; 
	}
	
	private Move getBlockMove(Block b){
		Move c = new Move(0,0,null);
		for(int i=0;i<GRID_WIDTH;i++){
			for(int j=0;j<GRID_HEIGHT;j++){
				if(grid[i][j].equals(b.name)){
					c.x = i;
					c.y = j;
					return c;
				}
			}
		}
		return null;
	}
	
	/*
	 * Replaces Block B1 with B2
	 */
	private void replaceBlock(String b1, String b2){
		for(int i=0;i<GRID_WIDTH;i++){
			for(int j=0;j<GRID_HEIGHT;j++){
				if(grid[i][j].equals(b1)){
					grid[i][j] = b2;
				}
			}
		}
	}
	
	private void insertBlock(int x, int y, Block b){
		for(int i=x;i<x+b.width;i++){
			for(int j=y;j<y+b.height;j++){
				grid[i][j] = b.name;
			}
		}
	}
}
