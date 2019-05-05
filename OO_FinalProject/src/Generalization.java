import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Generalization extends CustomButton implements ActionListener{

	private CustomPanel panel;
	private ArrayList<JToggleButton> btnList;
 	private TriangleShape triangleShape;
	public Generalization() {
		// TODO Auto-generated constructor stub
	}
	public Generalization(ImageIcon image,CustomPanel panel_draw,ArrayList<JToggleButton> btnList) {
		super(image);
		addActionListener(this);
		panel = panel_draw;
		this.btnList = btnList;
		triangleShape = new TriangleShape(
				new Point2D.Double(5, 0),
	            new Point2D.Double(10, 10),
	            new Point2D.Double(0, 10)
	    );
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Generalize");
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
