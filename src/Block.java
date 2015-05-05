/**
 * 
 */

/**
 * @author hardline
 *
 */
public class Block {
	String name;
	int x;
	int y;
	int height;
	int width;
	
	public Block(int x, int y, int h, int w, String n){
		this.name = n;
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
	}
}
