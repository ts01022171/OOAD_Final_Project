import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class BasicObject extends Diagram{

	private int height;
	private int width;
	private boolean selected;
	private List<Port> list_Port;
	private List<Location> list_locatePort;
	public String name;
	public BasicObject(Mode m, Location l, Shape s, int d) {
		super(m, l, s, d);
		// TODO Auto-generated constructor stub
		location.x = l.x - (int)shape.getBounds2D().getWidth()/2;
		location.y = l.y - (int)shape.getBounds2D().getHeight()/2;
		selected = false;
		height = (int)shape.getBounds2D().getHeight(); 
		width = (int)shape.getBounds2D().getWidth(); 
		list_Port = new ArrayList<Port>();
		list_locatePort = new ArrayList<Location>();
		CreateNewPortLocate();
		Shape shapePort = new Rectangle2D.Double(-10, -10, 10, 10); 
		AddNewPort(list_locatePort,shapePort,d);
	}
	
	private void CreateNewPortLocate() {
		list_locatePort.clear();
		list_locatePort.add(new Location(location.x + width/2+5, location.y));
		list_locatePort.add(new Location(location.x + width/2+5, location.y + height+10));
		list_locatePort.add(new Location(location.x, location.y + height/2+5));
		list_locatePort.add(new Location(location.x + width+10, location.y + height/2+5));
	}
	
	private void AddNewPort(List<Location> list_l,Shape s,int d) {
		for(Location l : list_l) {
			list_Port.add(new Port(Mode.Port,l,s,d));
		}
	}
	public void SetLoaction(Location l) {
		
		//int subX = location.x-l.x;
		//int subY = location.y-l.y;
		
		//後面修正東西點在中心點的問題
//		location.x = l.x - (int)shape.getBounds2D().getWidth()/2;
//		location.y = l.y - (int)shape.getBounds2D().getHeight()/2;
		
		location.x = location.x - l.x;
		location.y = location.y - l.y;
		
		
		System.out.println("實際的位置: "+location.x+" 第二個: "+location.y);
		
		CreateNewPortLocate();
//		for(Port p : list_Port) {
//			p.SetLocation(l);
//		}
		for(int i=0;i<list_Port.size();i++) {
			list_Port.get(i).SetLocation(list_locatePort.get(i));
		}
	}

	
	public boolean Select(Location l) {		
		return shape.contains(l.x-location.x,l.y-location.y);	
	}
	public void SetSelectedState(boolean b) {
		selected = b;
	}
	public boolean GetSelectedState() {
		return selected;
	}
	public List<Port> GetPort(){
		return list_Port;
	}
	public boolean IsFullPort() {
		for(Port p : list_Port) {
			if(p.line==null)
				return true;
		}
		return false;
	}

	
}
