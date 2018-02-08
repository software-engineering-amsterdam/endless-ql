import model.Block;
import model.Condition;
import model.Form;
import model.Question;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

public class Application extends JFrame{

    private QLLexer lexer;
    private CommonTokenStream tokens;
    private QLParser parser;

    public static void main(String[] args) {
        String fileName = "example.ql";
        Application application = new Application();
        Form form = application.parseForm(fileName);
        application.viewForm(form);
    }

    private Form parseForm(String fileName) {
        InputStream stream = getClass().getResourceAsStream(fileName);

        try {
            lexer = new QLLexer(CharStreams.fromStream(stream));
        } catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }

        tokens = new CommonTokenStream(lexer);
        parser = new QLParser(tokens);

        // Walk it and attach our listener
        VisitorForm visitor = new VisitorForm();
        Form form = visitor.visit(parser.root());

        System.out.println(form);

        // Visualize tree
        parser.reset();
        Trees.inspect(parser.root(), parser);

        return form;
    }

    private void viewForm(Form form) {
        // Do jframe stuff
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(0, 1));

        // Add questions
        container.add(new JLabel("FORM[" + form.identifier + "]"));
        addQuestions(form.block, container);

        // Add conditional questions



        // Frame options
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        container.setBorder(padding);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(container);
        setSize(800, 600);
        setLocationRelativeTo(null);
//        pack();
    }

    public void addQuestions(Block block, JPanel container){
        for(Question question : block.questions){
            container.add(new JLabel(question.text));
            container.add(new JTextField(""));
        }
        for(Condition condition : block.conditions){
            if(Boolean.TRUE.equals(condition.expression.evaluate())){
                addQuestions(condition.block, container);
            }
        }
    }
}
