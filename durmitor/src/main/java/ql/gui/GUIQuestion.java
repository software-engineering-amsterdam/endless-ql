package ql.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ql.ast.statement.Question;
import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Undefined;
import ql.gui.fields.DateSpinnerField;
import ql.gui.fields.IntTextField;
import ql.gui.fields.MoneyTextField;
import ql.gui.fields.StrTextField;
import ql.visitors.interfaces.TypeVisitor;

public class GUIQuestion extends JPanel implements TypeVisitor {

    private static final long serialVersionUID = 2816798767896918152L;
    ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.gui");
    private JCheckBox checkBox;
    private JTextField textField;
    private DateSpinnerField dateSpinner;
    private Question question;
    
    public GUIQuestion(Question question) {
//    public GUIQuestion(String label, final Type variableType) {
        
        this.question = question;
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(1,2));
        add(new JLabel(question.getLabel()));
        question.getIdentifier().getType().accept(this);
    }

    @Override
    public void visit(Bool type) {
        
        checkBox = new JCheckBox(translations.getString("jcheckbox.label"));
        checkBox.setName(question.getIdentifier().getName());
        checkBox.setSelected(false);
        checkBox.setEnabled(true);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                question.getIdentifier().setValue(new ql.value.Bool(checkBox.isSelected()));
            }
        });
        add(this.checkBox);
    }

    @Override
    public void visit(Str type) {
        textField = new StrTextField(question.getIdentifier());
        add(this.textField);
    }

    @Override
    public void visit(Int type) {
        textField = new IntTextField(question.getIdentifier());
        add(this.textField);
    }

    @Override
    public void visit(Decimal type) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Money type) {
        textField = new MoneyTextField(question.getIdentifier());
        add(this.textField);
    }

    @Override
    public void visit(Date type) {
        dateSpinner = new DateSpinnerField(question.getIdentifier());
        add(this.dateSpinner);
    }

    @Override
    public void visit(Undefined type) { }
}
