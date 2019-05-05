import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public abstract class CustomButton extends JToggleButton{

	public CustomButton() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomButton(ImageIcon image) {
		super(image);
	}

	public abstract Shape GetShape();
	
}
