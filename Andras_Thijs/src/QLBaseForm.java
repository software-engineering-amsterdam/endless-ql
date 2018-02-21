import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

public class QLBaseForm {
    private FormLayout layout;
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel formTitle;

    public final void init(){

        frame = new JFrame("QLForm");

        mainPanel = initPanel();

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setVisible(true);
    }

    private JPanel initPanel(){

        FormLayout layout = new FormLayout(
                "right:default, default, 10dlu"); // 5 columns; add rows later

        DefaultFormBuilder builder = new DefaultFormBuilder(layout);
        builder.append("Label1", new JTextField(), 1);
        builder.append("Label2", new JTextField());
        builder.append("Label3", new JTextField());
        builder.append(new JButton("/u2026"));
        return builder.getPanel();
    }

    public void setTitle(String title){
//        formTitle = new JLabel(title);
//        mainPanel.add(formTitle);
//        frame.revalidate();
    }

    public void addQuestion(Question question){

//        JPanel questionPanel = new JPanel();
//
//
//
//        questionPanel.add(new JLabel(question.getType()));
//
//        mainPanel.add(questionPanel);
//
//        frame.revalidate();
    }
}
