import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class UseCase<T> extends CustomButton implements ActionListener{
	
	private CustomPanel panel;
	private ArrayList<JToggleButton> btnList;
	private T shape;
	public UseCase() {
		// TODO Auto-generated constructor stub
	}
	public UseCase(ImageIcon image,CustomPanel panel_draw,T s,ArrayList<JToggleButton> btnList) {
		super(image);
		addActionListener(this);
		panel = panel_draw;
		shape = s;
		this.btnList = btnList;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("UseCase");
		panel.ChangeMode(Mode.Object,this);
		for(JToggleButton btn : btnList) {
			if(!btn.equals(this))
				btn.setSelected(false);
		}
	}
	@Override
	public Shape GetShape() {
		// TODO Auto-generated method stub
		return (Shape) shape;
	}


	
}
