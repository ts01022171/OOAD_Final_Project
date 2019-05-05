import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class AWTDEMO {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame();
		//MenuBar Set
	 	JMenuBar menubar = new JMenuBar();
	 	JMenu menu_File = new JMenu("File");
	 	menu_File.add("Save");
	 	menu_File.add("Load");
	 	JMenu menu_Edit = new JMenu("Edit");
	 	
	 	menubar.add(menu_File);
	 	menubar.add(menu_Edit);

	 	//Container Set
	 	Container container = frame.getContentPane();
	 	
	 	//Draw Panel
	 	CustomPanel panel_draw = new CustomPanel();
	 	panel_draw.setSize(500, 600);
	 	//panel_draw.setBackground(Color.BLUE);
	 	
	 	//MenuItem
	 	JMenuItem menuGroup = new JMenuItem(new AbstractAction("Group") {
	 	    public void actionPerformed(ActionEvent e) {
	 	    	panel_draw.Group();
	 	    }
	 	});
	 	
	 	JMenuItem menuUnGroup = new JMenuItem(new AbstractAction("UnGroup") {
	 	    public void actionPerformed(ActionEvent e) {
	 	    	panel_draw.UnGroup();
	 	    }
	 	});
	 	JMenuItem menuChangeName = new JMenuItem(new AbstractAction("ChangeName") {
	 	    public void actionPerformed(ActionEvent e) {
	 	    	panel_draw.ChangeName();
	 	    }
	 	});
	 	menu_Edit.add(menuGroup);
	 	menu_Edit.add(menuUnGroup);
	 	menu_Edit.add(menuChangeName);
	 	
	 	//Button
	 	ArrayList<JToggleButton> btnList = new ArrayList<JToggleButton>();
	 	JToggleButton btn_association = new Association(new ImageIcon("src/images/associationline.PNG"),panel_draw,btnList);
	 	JToggleButton btn_composition = new Composition(new ImageIcon("src/images/compositionline.PNG"),panel_draw,btnList);
	 	JToggleButton btn_generaliztion = new Generalization(new ImageIcon("src/images/generalizationline.PNG"),panel_draw,btnList);
	 	JToggleButton btn_select = new Select(new ImageIcon("src/images/select.PNG"),panel_draw,btnList);
	 	JToggleButton btn_class = new Class(new ImageIcon("src/images/class.PNG"),panel_draw,new Rectangle(0,0,100,70),btnList);
	 	JToggleButton btn_usecase = new UseCase(new ImageIcon("src/images/usecase.PNG"),panel_draw,new Ellipse2D.Double(0,0,100,100),btnList);
	 	
	 	btnList.add(btn_association);
	 	btnList.add(btn_composition);
	 	btnList.add(btn_generaliztion);
	 	btnList.add(btn_select);
	 	btnList.add(btn_class);
	 	btnList.add(btn_usecase);
	 	
	 	//Button Panel
	 	JPanel panel_btn = new JPanel(new GridLayout(6,1));
	 	panel_btn.add(btn_association);
	 	panel_btn.add(btn_class);
	 	panel_btn.add(btn_composition);
	 	panel_btn.add(btn_generaliztion);
	 	panel_btn.add(btn_select);
	 	panel_btn.add(btn_usecase);

	 	container.add(panel_btn,BorderLayout.WEST);
	 	container.add(panel_draw,BorderLayout.CENTER);
	 	
	 	frame.setJMenuBar(menubar);
	 	frame.setContentPane(container);
	 	
	 	frame.setSize(800,600);
	 	frame.setVisible(true);
	}

}

