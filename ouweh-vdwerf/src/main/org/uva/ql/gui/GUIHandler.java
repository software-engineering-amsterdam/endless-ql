package org.uva.ql.gui;

import javax.swing.*;
import org.uva.ql.ast.*;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.widgets.QuestionWidget;


public class GUIHandler {

    private JFrame frame;
    private FormEvaluator formEvaluator;
    private QuestionChangeListener questionChangeListener;

    public GUIHandler(FormEvaluator formEvaluator){
        this.formEvaluator = formEvaluator;
        this.questionChangeListener = new QuestionChangeListener(this);

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,  300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        generateGUI();
    }

    public void onQuestionChange(String id, Value value) {
        formEvaluator.addOrUpdateValue(id, value);
        generateGUI();
    }

    private void generateGUI(){
        frame.getContentPane().removeAll();

        WidgetFactory widgetFactory = new WidgetFactory(this.questionChangeListener);

        for(Question question: formEvaluator.getQuestionsAsList()){
            Value value = formEvaluator.getValueById(question.getName());
            QuestionWidget widget = widgetFactory.makeWidget(question, value);

            if(formEvaluator.questionHasCondition(question)){

                widget.setVisible(true);
            }
            frame.add(widget);
        }
        frame.setVisible(true);
    }

}
