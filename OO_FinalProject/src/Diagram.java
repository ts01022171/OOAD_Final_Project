import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Diagram {
	protected Mode mode;
	protected Location location;
	protected Shape shape;
	protected int depth;
	public Diagram(Mode m,Location l,Shape s,int d) {
		mode = m;
		shape = s;
		location = new Location();
		location.x = l.x;
		location.y = l.y;
		depth = d;
	}
	public Mode GetMode() {
		return mode;
	}
	public Shape GetShape() {
		return shape;
	}
	public Location GetLocation() {
		return location;
	}
	public int GetDepth() {
		return depth;
	}
	
}
