import java.awt.Shape;

public class Line extends Diagram{

	Port[] twoPort;
	Shape arow;
	public Line(Mode m, Location l, Shape s, int d,Shape shapeline) {
		super(m, l, s, d);
		// TODO Auto-generated constructor stub
		twoPort = new Port[2];
		arow = shapeline;
	}
	
	public void SetLocation(Location l) {
		location.x = l.x;
		location.y = l.y;
	}
	
	public void SetShape(Shape s) {
		shape = s;
	}
	
	public void SetPort(Port front,Port end) {
		twoPort[0] = front;
		twoPort[1] = end;
		
	}

}
