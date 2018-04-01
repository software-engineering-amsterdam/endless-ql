package nl.khonraad.ql.gui.visuals;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;

import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;
import nl.khonraad.ql.cdi.LoggingAspect;
import nl.khonraad.qls.ast.ExtendedQLSBaseVisitor;
import nl.khonraad.qls.ast.data.Styles;
import nl.khonraad.qls.ast.data.Stylesionnaire;

@SuppressWarnings( "serial" )

public class WidgetContainer extends Panel {

    @Inject
    Logger                 logger;

    @Inject
    ExtendedQLBaseVisitor  extendedQLBaseVisitor;

    @Inject
    ExtendedQLSBaseVisitor extendedQLSBaseVisitor;

    @Inject
    Questionnaire          questionnaire;

    @Inject
    Stylesionnaire        stylesionnaire;
    
    @Inject
    Styles                 styles;

    @PostConstruct
    public void postConstruct() {

        setLayout( new GridLayout( 0, 2 ) );
    }

    @LoggingAspect
    public void visualize() {

        removeAll();

        questionnaire.visitSource( extendedQLBaseVisitor );
        stylesionnaire.visitSource( extendedQLSBaseVisitor );

        for ( Question question : questionnaire.questions() ) {

            add( new JLabel( question.label() ) );
            add( visualizeQuestion( question ) );
        }
    }

    private static JPanel addToParent( JPanel parentPanel, JComponent component ) {

        JPanel childPanel = new JPanel();

        childPanel.add( component, new GridLayout( 0, 2 ) );
        parentPanel.add( childPanel );

        return parentPanel;
    }

    @LoggingAspect
    public JPanel visualizeQuestion( Question question ) {

        JPanel parentPanel = new JPanel();

        BehaviouralType behaviouralType = question.getBehaviouralType();

        if ( behaviouralType == BehaviouralType.COMPUTED ) {

            return addToParent( parentPanel, new ComputedQuestionWidget( question ).jLabel );
        }

        Type type = question.type();

        if ( behaviouralType == BehaviouralType.ANSWERABLE ) {

            switch ( type ) {

                case Boolean:
                    return addToParent( parentPanel, new BooleanWidget( question, styles.find( type ) ).jComboBox );

                case Date:
                    return addToParent( parentPanel, new DateWidget( question ).jTextField );

                case Integer:
                    return addToParent( parentPanel, new IntegerWidget( question ).jSpinner );

                case Money:
                    return addToParent( parentPanel, new MoneyWidget( question ).jSpinner );

                case String:
                    return addToParent( parentPanel, new StringWidget( question ).jTextField );
            }
        }
        throw new RuntimeException( "Do not know how to diplay type: " + type );

    }
}
