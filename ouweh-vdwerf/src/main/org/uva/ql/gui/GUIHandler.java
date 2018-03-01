package org.uva.ql.gui;

import javax.swing.*;
import org.uva.ql.ast.*;
import org.uva.ql.evaluator.FormEvaluator;


public class GUIHandler {

    public GUIHandler(FormEvaluator formEvaluator){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        for(Question question: formEvaluator.getQuestionsAsList()){
            QuestionWidget widget = new QuestionWidget(question);
            frame.add(widget);
        }
        frame.setVisible(true);

    }

}
