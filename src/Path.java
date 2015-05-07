import java.util.LinkedList;
import java.util.Queue;


public class Path {
	String gridCode;
	Queue<Move> moves = new LinkedList<Move>();
	
	public Path(String code, Move move){
		this.gridCode = code;
		this.moves.add(move);
	}
}
