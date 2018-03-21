package ql.gui.alternative;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ql.ast.statement.Statement;

public final class GUI {
    
	private static final GUI gui = new GUI();
	private final static Map<Statement,JPanel> panels = new HashMap<Statement,JPanel>();
	
	private static JFrame frame;
	private static JPanel panel;
    private ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.gui");
    
    public static GUI getInstance() {
    		return gui;
    }
    
    private GUI() {
    	
	    	//create frame
	    	frame = new JFrame(translations.getString("jframe.title"));
	    	frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    	frame.setPreferredSize( new Dimension(400, 600) );
	    	frame.setLayout(new BorderLayout());
	    	
	    	//add menu
	    	frame.add(new QLMenu().getMenuBar(),BorderLayout.PAGE_START);
	    	
	    	//add panel
	    	panel = new JPanel();
	    	frame.add(panel,BorderLayout.CENTER);
	    	
	    	//set frame position on screen, center
	    	frame.pack();
	    	frame.setLocationRelativeTo(null);
	    	
	    	//show frame
	    	frame.setVisible(true);
    }
    
    public static JFrame getFrame() {
		return frame;
	}
    
    public static JPanel getPanel() {
		return panel;
	}
    
    public static JPanel getStatementPanel(Statement statement) {
		
		if(!panels.containsKey(statement))
		{
			JPanel stmtPanel = new JPanel();
			stmtPanel.setLayout(new BoxLayout(stmtPanel, BoxLayout.Y_AXIS));
			panels.put(statement, stmtPanel);
		}
		
		return panels.get(statement);
    }
    
    public static void reset() {
    	
    		panel.removeAll();
    		frame.repaint();
    		panels.clear();
    }
}
