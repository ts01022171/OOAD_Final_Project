import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Association extends CustomButton implements ActionListener{

	private CustomPanel panel;
	private ArrayList<JToggleButton> btnList;
	public Association() {
		// TODO Auto-generated constructor stub
	}
	public Association(ImageIcon image,CustomPanel panel_draw,ArrayList<JToggleButton> btnList) {
		super(image);
		addActionListener(this);
		panel = panel_draw;
		this.btnList = btnList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Association");
		panel.ChangeMode(Mode.Line,this);
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
