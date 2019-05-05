import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Port extends Diagram{

	Line line;
	private boolean Lined = false;;
	public Port(Mode m, Location l, Shape s, int d) {
		super(m, l, s, d);
		// TODO Auto-generated constructor stub
	}
	public void SetLocation(Location l) {
		location.x = l.x;
		location.y = l.y;
		if(Lined)
			SetNewLine();
	}
	
	private void SetNewLine() {
		Shape s;
		Port another;
		Line newline;
		if(line.twoPort[0].equals(this)) {
			another = line.twoPort[1];
			s = new Line2D.Double(0, 0,
					-(this.location.x - line.twoPort[1].location.x),
					-(this.location.y - line.twoPort[1].location.y));
			Shape shapeline = another.line.arow;
			newline = new Line(Mode.Line,location,s,depth-1,shapeline);
			newline.SetPort(line.twoPort[0],line.twoPort[1]);
			line = newline;
			another.line = newline;
		}
		else if(line.twoPort[1].equals(this)){
			another = line.twoPort[0];
			s = new Line2D.Double(0, 0,
					-(this.location.x - line.twoPort[0].location.x),
					-(this.location.y - line.twoPort[0].location.y));
			Shape shapeline = another.line.arow;
			newline = new Line(Mode.Line,location,s,depth-1,shapeline);
			newline.SetPort(line.twoPort[0],line.twoPort[1]);
			line = newline;
			another.line = newline;
		}
		
		
	}
	
	public void SetLine(Line l) {
		line = l;
		Lined = true;
	}
	public boolean IsLined() {
		return Lined;
	}
}
