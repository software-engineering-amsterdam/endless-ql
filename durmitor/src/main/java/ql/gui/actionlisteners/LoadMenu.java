package ql.gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import ql.QL;
import ql.ast.form.Form;
import ql.gui.GUI;
import ql.visitors.ASTtoGUI;

public class LoadMenu implements ActionListener {

    JFileChooser fileChooser;
    JFrame frame;
    GUI gui;
    
    public LoadMenu(JFileChooser fileChooser, JFrame frame, GUI gui) {
        this.fileChooser = fileChooser;
        this.frame = frame;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        int state = fileChooser.showOpenDialog(frame);
        
        if ( state == JFileChooser.APPROVE_OPTION ) {
            String filePath = fileChooser.getSelectedFile().toPath().toString();
            QL ql = new QL(filePath);
            try {
                Form form = ql.getForm();
                ASTtoGUI astToGUI= new ASTtoGUI(gui.resetFrame());
                astToGUI.visit(form);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            // Cancelled
        }
    }
}

