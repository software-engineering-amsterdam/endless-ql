package GUI;

import AST.gen.QLParser;
import Nodes.Condition;
import Nodes.QLForm;
import Nodes.Question;
import Nodes.Type;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import javafx.util.Builder;

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

    private FormLayout layout;
    private JFrame frame;
    private JPanel mainPanel;
    private QLForm body;
    private JButton submit;
    private List<Component> components;
    private DefaultFormBuilder builder;
    private Map<Question, JPanel> questionDictionary;

    public FormTemplate(){
        body  = new QLForm("Undefined");
    }

    public FormTemplate(QLForm form){
        body = form;
    }

    public final void initGUI(){

        frame = new JFrame(body.getName());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout = new FormLayout(
                "left:default, 3dlu default"); // 5 columns; add rows later

        builder = new DefaultFormBuilder(layout);

        updatePanel();

        //refreshPanel(builder.getPanel());
    }

    private void updatePanel(){

        List<Question> questions = body.getQuestions();

        iterateQuestions(questions);

        List<Condition> conditions = body.getConditions();
        Iterator<Condition> conditionIterator = conditions.iterator();
        while (conditionIterator.hasNext())
            iterateQuestions(visitCondition(conditionIterator.next()));

        //builder.append(submit = new JButton("submit"));

    }

    private List<Question> visitCondition(Condition condition){

        List<Question> questions;

        if(condition.getExpression().getBoolean()){
            questions = condition.getQuestions();
            List<Condition> conditions = condition.getConditions();
            Iterator<Condition> conditionIterator = conditions.iterator();
            while (conditionIterator.hasNext()) {
                List<Question> subQuestions = visitCondition(conditionIterator.next());
                if (subQuestions != null)
                    questions.addAll(subQuestions);
            }

            return questions;
        }

        return null;
    }

    private void iterateQuestions (List<Question> questions){
        Iterator<Question> questionIterator = questions.iterator();
        while (questionIterator.hasNext()) {
            System.out.println(questionIterator.next().getLabel());
        }
    }

    private void addQuestion(Question question) {
        if (!(questionDictionary.containsKey(question))){
            DefaultFormBuilder panelBuilder = new DefaultFormBuilder(layout);
            panelBuilder.append(question.getLabel(), getComponent(question.getType()));
            questionDictionary.put(question, panelBuilder.getPanel());
        }
        question.setDisplayed(true);
        frame.add(questionDictionary.get(question));
    }

    private void removeQuestion(Question question){
        if (questionDictionary.containsKey(question) && question.isDisplayed()){
            frame.remove(questionDictionary.get(question));
        }
    }

    private void refreshPanel(JPanel newPanel){

        mainPanel = newPanel;

        frame.add(mainPanel);

        frame.pack();

        frame.setVisible(true);
    }

    private Component getComponent(Type type){
        switch (type){
            case BOOL: return new JCheckBox();

            case STRING: return new JTextField();

            case INT: return new JFormattedTextField(intField());

            case DATE: return new JFormattedTextField(dateField());

            case DECIMAL: return new JFormattedTextField(decimalField());

            case MONEY: return new JFormattedTextField(moneyField());

            default: return new JLabel(String.valueOf(type));
        }
    }


    private NumberFormatter intField(){
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        return formatter;
    }

    private DateFormatter dateField(){
        DateFormat format = new SimpleDateFormat("yyyy--MMMM--dd");
        DateFormatter formatter = new DateFormatter(format);
        formatter.setValueClass(Date.class);
        formatter.setAllowsInvalid(false);
        return formatter;
    }


    private NumberFormatter decimalField() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        return formatter;
    }

    // TODO define currency field
    private NumberFormatter moneyField() {
        return decimalField();
    }

//    public void setTitle(String title){
//
//        frame.setTitle(title);
//    }
//
//    public void addQuestion(Nodes.Question question){
//
//        questionPanel.add(new JLabel(question.getType()));
//
//        mainPanel.add(questionPanel);
//
////        frame.revalidate();
//    }
}
