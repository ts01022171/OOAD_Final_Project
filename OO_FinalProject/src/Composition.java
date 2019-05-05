import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Composition extends CustomButton implements ActionListener{

	private CustomPanel panel;
	private ArrayList<JToggleButton> btnList;
 	private TriangleShape triangleShape;
	public Composition() {
		// TODO Auto-generated constructor stub
	}
	public Composition(ImageIcon image,CustomPanel panel_draw,ArrayList<JToggleButton> btnList) {
		super(image);
		addActionListener(this);
		panel = panel_draw;
		this.btnList = btnList;
		triangleShape = new TriangleShape(
	            new Point2D.Double(5, 0),
	            new Point2D.Double(10, 5),
	            new Point2D.Double(5, 10),
	            new Point2D.Double(0, 5)
	    );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Composition");
		panel.ChangeMode(Mode.Line,this);
		for(JToggleButton btn : btnList) {
			if(!btn.equals(this))
				btn.setSelected(false);
		}
	}
	@Override
	public Shape GetShape() {
		// TODO Auto-generated method stub
		return triangleShape;
	}
}
