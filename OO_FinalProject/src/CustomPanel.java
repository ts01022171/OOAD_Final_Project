import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class CustomPanel extends JPanel implements MouseListener ,MouseMotionListener{

	private ArrayList<BasicObject> list_BasicObject;
	private ArrayList<BasicObject> list_FrameObject;
	private ArrayList<ArrayList<BasicObject>> list_GroupObject;
	private BasicObject move_diagram;
	private BasicObject selected_diagram;
	private Rectangle frame;
	private boolean IsSelectedMode = false;
	private boolean IsLineMode = false;
	private boolean IsBasicObjectMode = false;
	private boolean IsOnObject = false;
	private boolean IsOnDragged = false;
	private boolean IsOnFrameDragged = false;
	private Location clickedLocation;
	private Location pressedLocation;
	private Location releasedLocation;
	private Location draggedLocation;
	private Mode mode;
	private Graphics2D g2D;
	private CustomButton button;
	private int Count_depth=0;
	public CustomPanel() {
		// TODO Auto-generated constructor stub
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		clickedLocation = new Location();
		pressedLocation = new Location();
		releasedLocation = new Location();
		draggedLocation = new Location();
		list_BasicObject = new ArrayList<BasicObject>();
		list_GroupObject = new ArrayList<ArrayList<BasicObject>>();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g2D = (Graphics2D) g;
		DrawDiagram();
		if(IsSelectedMode && IsOnFrameDragged)
			DrawFrameDiagram();
	}
	//畫框選線
	private void DrawFrameDiagram() {
        Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10, },
                0f);
        g2D.setStroke(dash);
        g2D.setColor(Color.RED);
        Location startPoint = pressedLocation;
        if(draggedLocation.x - pressedLocation.x<0||draggedLocation.y - pressedLocation.y<0) {
        	startPoint = draggedLocation;
        }
        frame = new Rectangle(startPoint.x, startPoint.y,
        		Math.abs( draggedLocation.x-pressedLocation.x), Math.abs( draggedLocation.y-pressedLocation.y));
        g2D.draw(frame);
	}
	
	//畫圖型以及port
	private void DrawDiagram() {
		for(BasicObject d : list_BasicObject) {
			g2D.translate(d.GetLocation().x, d.GetLocation().y);
			g2D.setColor(Color.white);
			g2D.fill(d.GetShape());
			g2D.setColor(Color.BLACK);
			g2D.draw(d.GetShape());
			
			g2D.translate(-d.GetLocation().x, -d.GetLocation().y);
			if(d.name!=null)
				g2D.drawString(d.name, d.GetLocation().x+30, d.GetLocation().y+25);
			//被選擇的點點與他們的好夥伴
			if(IsSelectedMode && d.GetSelectedState()) {
				for(Port p : d.GetPort()) {
					g2D.translate(p.GetLocation().x,p.GetLocation().y);
					g2D.setColor(Color.white);
					g2D.fill(p.GetShape());
					g2D.setColor(Color.BLACK);
					g2D.draw(p.GetShape());
					g2D.translate(-p.GetLocation().x,-p.GetLocation().y);
				}
			}
			for(Port p : d.GetPort()) {
				if(p.IsLined()) {
					g2D.translate(p.line.GetLocation().x,p.line.GetLocation().y);
					g2D.setColor(Color.white);
					g2D.fill(p.line.GetShape());
					g2D.setColor(Color.BLACK);
					g2D.draw(p.line.GetShape());
					g2D.translate(-p.line.GetLocation().x,-p.line.GetLocation().y);
					
					g2D.translate(p.GetLocation().x,p.GetLocation().y);
					g2D.setColor(Color.white);
					g2D.fill(p.GetShape());
					g2D.setColor(Color.BLACK);
					g2D.draw(p.GetShape());
					g2D.translate(-p.GetLocation().x,-p.GetLocation().y);
					if(p.line.arow!=null && p.line.twoPort[1].equals(p)) {
						g2D.translate(p.GetLocation().x-10,p.GetLocation().y-10);
						g2D.setColor(Color.BLUE);
						g2D.fill(p.line.arow);
						g2D.draw(p.line.arow);
						g2D.translate(-(p.GetLocation().x-10),-(p.GetLocation().y-10));	
					}
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		clickedLocation.x = e.getX();
		clickedLocation.y = e.getY();
		//SelectedMode下
		if(IsSelectedMode) {
			//點擊空白處
			if(selected_diagram!=null) {
				selected_diagram.SetSelectedState(false);
				selected_diagram = null;
			}
			if(list_FrameObject!=null)
				for(BasicObject b : list_FrameObject) {
					b.SetSelectedState(false);
				}
			list_FrameObject = null;
			//將所有圖形的selectedstate設為FALSE
			if(list_BasicObject!=null) {
				for(BasicObject b : list_BasicObject) {
					b.SetSelectedState(false);
				}
			}
			//點在東西上面
			selected_diagram = GetTopDiagram(clickedLocation);
			if(selected_diagram!=null) {
				selected_diagram.SetSelectedState(true);
			}
			if(list_GroupObject.size()!=0)
			for(ArrayList<BasicObject> aab : list_GroupObject) {
				if(aab.contains(selected_diagram)) {
					for(BasicObject b : aab) {
						b.SetSelectedState(true);
					}
				}
			}
		}
		else if(IsBasicObjectMode) {
			list_BasicObject.add(new BasicObject(mode,clickedLocation,button.GetShape(),Count_depth));
			Count_depth++;
			
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressedLocation.x = e.getX();
		pressedLocation.y = e.getY();
		if(IsLineMode) {
			if(GetTopDiagram(pressedLocation)!=null)
				IsOnObject = true;
		}
		else if(IsSelectedMode){
			move_diagram = GetTopDiagram(pressedLocation);
			if(move_diagram!=null) {
				IsOnDragged = true;
			}
			else {
				IsOnFrameDragged = true;
				if(list_FrameObject!=null)
					for(BasicObject b : list_FrameObject) {
						b.SetSelectedState(false);
					}
				list_FrameObject = null;
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		releasedLocation.x = e.getX();
		releasedLocation.y = e.getY();
		if(IsLineMode) {
				IsOnObject = GetTopDiagram(releasedLocation)==null? false : IsOnObject&&true;
			if(IsOnObject) {
				BasicObject tempPress = GetTopDiagram(pressedLocation);
				BasicObject tempRelease = GetTopDiagram(releasedLocation);
				//如果沒PORT就跳轉
				if(!tempPress.IsFullPort() && !tempRelease.IsFullPort())
					return;
				System.out.println("開始畫線縣縣縣縣縣");
				//畫線硬幹
				Port[] arrayP = new Port[2];
				for(Port p : tempPress.GetPort()) {
					if(p.line==null) {
						arrayP[0] = p;
						break;
					}
				}
				for(Port p : tempRelease.GetPort()) {
					if(p.line==null) {
						arrayP[1] = p;
						break;
					}
				}
				Shape s = new Line2D.Double(0, 0,
						arrayP[1].location.x-arrayP[0].location.x,
						arrayP[1].location.y-arrayP[0].location.y);
				Line line = new Line(Mode.Line,arrayP[0].location,s,Count_depth-1,button.GetShape());
				arrayP[0].SetLine(line);
				arrayP[1].SetLine(line);
				
				line.SetPort(arrayP[0],arrayP[1]);
			}
			repaint();
			IsOnObject = false;
		}
		else if(IsSelectedMode){
			IsOnDragged = false;
			IsOnFrameDragged = false;
			if(move_diagram==null)
			list_FrameObject = GetFrameDiagram();
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(IsSelectedMode && move_diagram!=null) {
			Location sub = new Location(move_diagram.GetLocation().x-e.getX(),
					move_diagram.GetLocation().y-e.getY());
			move_diagram.SetLoaction(sub);
			repaint();
			if(list_GroupObject.size()!=0) {
				for(ArrayList<BasicObject> aab : list_GroupObject) {
					if(aab.contains(move_diagram)) {
						for(BasicObject b : aab) {
							if(b!=move_diagram)
								b.SetLoaction(sub);
						}
					}
				}
			}
		}
		if(IsOnDragged)
			repaint();
		else if(IsOnFrameDragged) {
			draggedLocation.x = e.getX();
			draggedLocation.y = e.getY();
			repaint();
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void ChangeMode(Mode m,CustomButton b) {
		IsSelectedMode = m==Mode.Select? true:false;
		IsLineMode = m==Mode.Line? true:false;
		IsBasicObjectMode = m==Mode.Object? true:false;
		mode = m;
		button = b;
	}
	
	public void Group() {
		ArrayList<ArrayList<BasicObject>> temparray = new ArrayList<ArrayList<BasicObject>>();
		if(list_FrameObject!=null) {
			if(list_GroupObject.size()!=0) {
				for(int i=0;i<list_GroupObject.size();i++){
					for(BasicObject b : list_GroupObject.get(i)) {
						if(list_FrameObject.contains(b)) {
							temparray.add(list_GroupObject.get(i));
							b.SetSelectedState(false);
						}
					}
				}
				for(int i=0;i<temparray.size();i++){
					list_GroupObject.remove(temparray.get(i));
				}
			}
			list_GroupObject.add(list_FrameObject);
		}
		System.out.println("GROUP");
	}
	public void UnGroup() {
		if(list_GroupObject.size()!=0 && selected_diagram!=null) {
			
			for(int i=0;i<list_GroupObject.size();i++){
				if(list_GroupObject.get(i).contains(selected_diagram)) {
					for(BasicObject b : list_GroupObject.get(i)) {
						b.SetSelectedState(false);
					}
					list_GroupObject.get(i).clear();
					list_GroupObject.remove(list_GroupObject.get(i));
				}
			}
		}
	}
	public void ChangeName() {
		String name = JOptionPane.showInputDialog("Input Field:");
	    selected_diagram.name = name.equals("") ? selected_diagram.name: name;
		repaint();
	}
	
	private BasicObject GetTopDiagram(Location l) {
		int temp = -1;
		BasicObject temp_BasicObject = null;
		for(BasicObject b : list_BasicObject) {
			if(b.Select(l)) {
				temp = Math.max(b.GetDepth(), temp);
				temp_BasicObject = b.GetDepth()==temp? b:temp_BasicObject;
			}
		}
		System.out.println("抓到你了");
		return temp_BasicObject;
	}
	private ArrayList<BasicObject> GetFrameDiagram() {
		ArrayList<BasicObject> list_FrameObject = new ArrayList<BasicObject>();
		if(list_BasicObject.size()!=0)
		for(BasicObject b : list_BasicObject) {
			if(frame.x <b.GetLocation().x && 
					b.GetLocation().x<frame.x+frame.width &&
					frame.y <b.GetLocation().y &&
					b.GetLocation().y<frame.y+frame.height
					) {
				list_FrameObject.add(b);
				b.SetSelectedState(true);
			}
		}
		System.out.println("有多少個呢? "+list_FrameObject.size());
		
		return list_FrameObject;
	}


}
