import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	String gridCode;	//configuration after move
	TreeNode parent;	//parent
	List<TreeNode> possibleMoves = new ArrayList<TreeNode>();	//all moves
	
	public TreeNode(String code, TreeNode parent){
		this.gridCode = code;
		this.parent = parent;
	}
	
	public void addNextMove(String code){
		if(!code.isEmpty() && !possibleMoves.contains(code)) possibleMoves.add(new TreeNode(code,this));
	}
}
