import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Select extends CustomButton implements ActionListener{

	private CustomPanel panel;
	private ArrayList<JToggleButton> btnList;
	public Select() {
		// TODO Auto-generated constructor stub
	}
	public Select(ImageIcon image,CustomPanel panel_draw,ArrayList<JToggleButton> btnList) {
		super(image);
		panel = panel_draw;
		addActionListener(this);
		this.btnList = btnList;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Select");
		panel.ChangeMode(Mode.Select,this);
		for(JToggleButton btn : btnList) {
			if(!btn.equals(this))
				btn.setSelected(false);
		}
	}
	@Override
	public Shape GetShape() {
		// TODO Auto-generated method stub
		return null;
	}
}
