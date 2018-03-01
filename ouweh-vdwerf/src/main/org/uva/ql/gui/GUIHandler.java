package org.uva.ql.gui;

import javax.swing.*;
import org.uva.ql.ast.*;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.widgets.QuestionWidget;


public class GUIHandler {

    public GUIHandler(FormEvaluator formEvaluator){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,  300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        WidgetFactory widgetFactory = new WidgetFactory();

        for(Question question: formEvaluator.getQuestionsAsList()){
            Value value = formEvaluator.getValueById(question.getName());
            QuestionWidget widget = widgetFactory.makeWidget(question, value);
            frame.add(widget);
        }
        frame.setVisible(true);

    }

}
