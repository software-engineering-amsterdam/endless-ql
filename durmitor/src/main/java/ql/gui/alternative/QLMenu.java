package ql.gui.alternative;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ql.QL;

public class QLMenu {

	private JMenuBar menuBar;
	private ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.gui");

	public QLMenu() {

		JMenu fileMenu = new JMenu(translations.getString("jmenu.file"));

		addLoadFileMenuItem(fileMenu);
		addSaveFileMenuItem(fileMenu);

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.revalidate();
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	private void addSaveFileMenuItem(JMenu parentMenu) {

		JMenuItem saveFileMenuItem	= new JMenuItem(translations.getString("jmenu.item.save.file"));
		JFileChooser fileChooserSave	= new JFileChooser();
		FileFilter saveFilter		= new FileNameExtensionFilter(
				translations.getString("save.file.type"),
				translations.getString("save.file.extension")
			);

		fileChooserSave.setFileFilter(saveFilter);
		fileChooserSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
				{
					System.out.println("Save results");
				}
			}
		});
		
		saveFileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooserSave.showOpenDialog(parentMenu);
			}
		});
		
		parentMenu.add(saveFileMenuItem);
	}
	
	private void addLoadFileMenuItem(JMenu parentMenu) {
		
		JMenuItem loadFileMenuItem	= new JMenuItem(translations.getString("jmenu.item.load.file"));
		JFileChooser fileChooserLoad	= new JFileChooser();
		FileFilter loadFilter		= new FileNameExtensionFilter(
				translations.getString("load.file.type"),
				translations.getString("load.file.extension")
			);
		
		fileChooserLoad.setFileFilter(loadFilter);
		fileChooserLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
				{
					String filePath = ((JFileChooser) e.getSource()).getSelectedFile().getPath();
					
					if(filePath.endsWith(".".concat(translations.getString("load.file.extension"))))
					{
						GUI.reset();
						new QL(filePath);
					}
					else
					{
						System.out.println("Invalid file");
					}
				}
			}
		});
		
		loadFileMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooserLoad.showOpenDialog(parentMenu);
			}
		});
		
		parentMenu.add(loadFileMenuItem);
	}
}
