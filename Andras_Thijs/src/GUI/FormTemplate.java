package GUI;

import Nodes.Condition;
import Nodes.QLForm;
import Nodes.Question;
import Nodes.Type;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class FormTemplate {

    private QLForm form;

    public FormTemplate(QLForm form){
        this.form = form;
    }

    public void renderForm(){

        JFrame frame = new JFrame(form.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new FlowLayout());

        List<Question> questions = form.getAllQuestions();
        Iterator<Question> questionIterator = questions.iterator();
        while (questionIterator.hasNext())
            panel.add(new QuestionPanel(questionIterator.next()));

        frame.pack();

        frame.setVisible(true);


    }

    /*
    private FormLayout layout;
    private JFrame frame;
    private JPanel mainPanel;
    private QLForm body;
    private JButton submit;
    private Map<Question, JPanel> questionDictionary;

    public FormTemplate(QLForm form){
        body = form;
        questionDictionary = new HashMap<>();
    }

    public final void initGUI(){

        frame = new JFrame(body.getName());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout = new FormLayout(
                "left:default, 3dlu default");


        updatePanel();




    }

    private void updatePanel(){

        List<Question> questions = body.getQuestions();

        iterateQuestions(questions);

        List<Condition> conditions = body.getConditions();
        Iterator<Condition> conditionIterator = conditions.iterator();
        while (conditionIterator.hasNext())
            iterateQuestions(visitCondition(conditionIterator.next(), true));

        //builder.append(submit = new JButton("submit"));

    }

    //TODO MOVE TO QLForm
    // Return the list of all available questions if valid elements is set to false, or all questions if it's set to true
    private List<Question> visitCondition(Condition condition, boolean allQuestions){

        List<Question> questions;

        if(condition.getExpression().getBoolean() || allQuestions){
            questions = condition.getQuestions();
            List<Condition> conditions = condition.getConditions();
            Iterator<Condition> conditionIterator = conditions.iterator();
            while (conditionIterator.hasNext()) {
                List<Question> subQuestions = visitCondition(conditionIterator.next(), allQuestions);
                if (subQuestions != null)
                    questions.addAll(subQuestions);
            }

            return questions;
        }

        return null;
    }

    //TODO MOVE TO QLForm
    private void iterateQuestions (List<Question> questions){
        Iterator<Question> questionIterator = questions.iterator();
        while (questionIterator.hasNext()) {
            addQuestion(questionIterator.next());
        }
    }


    private JPanel createQuestion(Question question){
        DefaultFormBuilder builder = new DefaultFormBuilder(layout);
        builder.append(question.getLabel(), getComponent(question.getType()));
        return builder.getPanel();
    }

    private void addQuestion(Question question) {

        DefaultFormBuilder builder = new DefaultFormBuilder(layout);

        if(!question.isDisplayed()) {
            if (!questionDictionary.containsKey(question)) {
                builder.append(question.getLabel(), getComponent(question.getType()));
                System.out.println(question.getLabel());
                questionDictionary.put(question, builder.getPanel());
            }
            question.setDisplayed(true);
            frame.add(questionDictionary.get(question));
        }
    }

    private void removeQuestion(Question question){
        if (questionDictionary.containsKey(question) && question.isDisplayed()){
            frame.remove(questionDictionary.get(question));
        }
    }

    private void refreshPanel(JPanel newPanel){

        frame.add(newPanel);

        frame.pack();

        frame.setVisible(true);
    }

    */

}
