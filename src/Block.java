/**
 * @author Alex Salerno
 * @author John Stoddard
 * @author Arron McCarter
 * @version 0.1
 *
 */
public class Block {
	String name;
	int area;
	int height;
	int width;
	
	public Block(int h, int w, String n){
		this.name = n;
		this.height = h;
		this.width = w;
		this.area = h * w;
	}
}
