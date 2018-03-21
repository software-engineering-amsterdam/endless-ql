package GUI;

import GUI.Listeners.RefreshListener;
import Nodes.QLForm;
import Nodes.Question;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class FormTemplate implements RefreshListener{

    private QLForm form;
    private List<QuestionPanel> questionPanels;

    public FormTemplate(QLForm form){
        this.form = form;
        questionPanels = new ArrayList<>();
    }

    public void renderForm(){

        JFrame frame = new JFrame(form.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FormLayout layout = new FormLayout(
                "left:default, 3dlu default");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout);

        List<Question> questions = form.getAllQuestions();
        Iterator<Question> questionIterator = questions.iterator();
        while (questionIterator.hasNext()) {
            QuestionPanel questionPanel = new QuestionPanel(questionIterator.next(), this);
            questionPanels.add(questionPanel);
            builder.append(questionPanel);
        }

        //JButton submit = new JButton("submit");

        //builder.add(submit);

        //JPanel buttonPanel = new JPanel();

        JPanel panel = builder.getPanel();

        frame.add(panel);

        frame.pack();

        frame.setVisible(true);

    }

    private void refreshView(){
        Iterator<QuestionPanel> questionPanelIterator = questionPanels.iterator();
        while(questionPanelIterator.hasNext()){
            questionPanelIterator.next().setVisibility();
        }
    }

    @Override
    public void refreshQuestions() {
        refreshView();
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

        if(condition.getResult().getBoolean() || allQuestions){
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
