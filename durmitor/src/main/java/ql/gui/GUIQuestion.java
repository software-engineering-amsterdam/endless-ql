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

public class GUIQuestion extends JPanel implements TypeVisitor<Void> {

    private static final long serialVersionUID = 2816798767896918152L;
    ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.gui");
    private JCheckBox checkBox;
    private JTextField textField;
    private DateSpinnerField dateSpinner;
    private Question question;
    
    public GUIQuestion(Question question) {
        
        this.question       = question;
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(1,2));
        add(new JLabel(question.getLabel()));
        question.getIdentifier().getType().accept(this);
    }

    @Override
    public Void visit(Bool type) {
        
        checkBox = new JCheckBox(translations.getString("jcheckbox.label"));
        checkBox.setName(question.getIdentifier().getName());
        checkBox.setSelected(false);
        checkBox.setEnabled(true);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                question.getIdentifier().setValue(new ql.ast.expression.literal.BoolLiteral(checkBox.isSelected()));
            }
        });
        add(this.checkBox);
        return null;
    }

    @Override
    public Void visit(Str type) {
        textField = new StrTextField(question.getIdentifier());
        add(this.textField);
        return null;
    }

    @Override
    public Void visit(Int type) {
        textField = new IntTextField(question.getIdentifier());
        add(this.textField);
        return null;
    }

    @Override
    public Void visit(Decimal type) {
        return null;
    }

    @Override
    public Void visit(Money type) {
        textField = new MoneyTextField(question.getIdentifier());
        add(this.textField);
        return null;
    }

    @Override
    public Void visit(Date type) {
        dateSpinner = new DateSpinnerField(question.getIdentifier());
        add(this.dateSpinner);
        return null;
    }

    @Override
    public Void visit(Undefined type) {
        return null; 
    }
}
