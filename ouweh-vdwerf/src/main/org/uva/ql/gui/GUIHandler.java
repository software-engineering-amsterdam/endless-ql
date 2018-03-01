package org.uva.ql.gui;

import javax.swing.*;
import org.uva.ql.ast.*;


public class GUIHandler {

    public GUIHandler(Form form){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        for(Statement statement: form.getStatements()){
            if(statement instanceof Question) {
                QuestionWidget widget = new QuestionWidget((Question) statement);
                frame.add(widget);
            }
        }
        frame.setVisible(true);

    }

}
