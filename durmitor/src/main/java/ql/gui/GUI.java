package ql.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ql.ast.QLNode;
import ql.gui.actionlisteners.LoadMenu;
import ql.gui.actionlisteners.SaveMenu;

public class GUI {
    
    ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.gui");
    public JFrame frame;
    public JPanel panel;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItemLoad, menuItemSave;
    final JFileChooser fileChooserLoad = new JFileChooser();
    final JFileChooser fileChooserSave = new JFileChooser();
    QLNode node;
    
    public GUI() {
        node = null;
        
        FileFilter loadFilter = new FileNameExtensionFilter(
                translations.getString("load.file.type"), 
                translations.getString("load.file.extension"));
        FileFilter saveFilter = new FileNameExtensionFilter(
                translations.getString("save.file.type"),
                translations.getString("save.file.extension"));
        fileChooserLoad.setFileFilter(loadFilter);
        fileChooserSave.setFileFilter(saveFilter);
        
        //create frame
        frame = new JFrame(translations.getString("jframe.title"));
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setPreferredSize( new Dimension(400, 600) );
        frame.setLayout( new FlowLayout( FlowLayout.CENTER ) );
        
        //show frame
        frame.pack();
        frame.setVisible(true);
        
        //set frame position on screen, center
        frame.setLocationRelativeTo(null);
        
        frame.addComponentListener(new ComponentAdapter() {
            
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
//                        panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
//                        panel.revalidate();
                    }
                });
            }
        });
        
        addMenu();
        addPanel();
    }
    
    private void addMenu() {
        //create menu
        menuBar = new JMenuBar();
        
        //File
        menu = new JMenu(translations.getString("jmenu.file"));
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                translations.getString("jmenu.file.description"));
        menuBar.add(menu);
        
        //Load File
        menuItemLoad = new JMenuItem(translations.getString("jmenu.item.load.file"));
        menuItemLoad.setMnemonic(KeyEvent.VK_L);
        menuItemLoad.getAccessibleContext().setAccessibleDescription(
                translations.getString("jmenu.item.load.file.description"));
        menu.add(menuItemLoad);
        
        //Save File
        menuItemSave = new JMenuItem(translations.getString("jmenu.item.save.file"));
        menuItemSave.setMnemonic(KeyEvent.VK_S);
        menuItemSave.getAccessibleContext().setAccessibleDescription(
                translations.getString("jmenu.item.save.file.description"));
        menu.add(menuItemSave);
        
        frame.setJMenuBar(menuBar);

        menuItemLoad.addActionListener(new LoadMenu(fileChooserLoad, frame, this));
        menuItemSave.addActionListener(new SaveMenu(fileChooserSave, frame, node));
    }
    
    public void addPanel() {
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        //panel.setBackground(new Color(0, 200, 0));
        frame.add(panel, BorderLayout.CENTER);
        
        frame.pack();
        frame.revalidate();
    }
    
    public GUI resetFrame () {
        frame.getContentPane().removeAll();
        addMenu();
        addPanel();
        
        return this;
    }
}
