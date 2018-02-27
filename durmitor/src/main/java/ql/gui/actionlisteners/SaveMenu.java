package ql.gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import ql.ast.QLNode;
import ql.visitors.ASTtoJSON;

public class SaveMenu implements ActionListener {

    JFileChooser fileChooser;
    JFrame frame;
    QLNode node;
    
    public SaveMenu(JFileChooser fileChooser, JFrame frame, QLNode node) {
        this.fileChooser = fileChooser;
        this.frame = frame;
        this.node = node;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int status = fileChooser.showSaveDialog(frame);
        
        if ( status == JFileChooser.APPROVE_OPTION ) {
            String fileName = fileChooser.getSelectedFile().toString();
            fileName = (fileName.toLowerCase().endsWith(".json"))? fileName : fileName + ".json";
            ASTtoJSON astToJSON = new ASTtoJSON(node);
            try {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(astToJSON.visit());
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // Cancelled
        }
    }
}
