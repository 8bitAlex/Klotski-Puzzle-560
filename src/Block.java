/**
 * @author hardline
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
