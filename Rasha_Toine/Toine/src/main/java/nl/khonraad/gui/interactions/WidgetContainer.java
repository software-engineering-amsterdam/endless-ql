package nl.khonraad.gui.interactions;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.language.Identifier;
import nl.khonraad.ql.language.FormElement.BehaviouralType;
import nl.khonraad.ql.language.Question;
import nl.khonraad.ql.language.QuestionsInterpretor;
import nl.khonraad.ql.parser.QuestionsVisitor;
import nl.khonraad.qls.language.StyleElement;
import nl.khonraad.qls.language.Style;
import nl.khonraad.qls.language.StyleTree;
import nl.khonraad.qls.language.StyleTree.StyleType;
import nl.khonraad.qls.language.StylesInterpretor;
import nl.khonraad.qls.parser.StylesVisitor;

@SuppressWarnings( "serial" )

public class WidgetContainer extends Panel {

    @Inject
    Logger               logger;

    @Inject
    QuestionsVisitor     questionsVisitor;

    @Inject
    QuestionsInterpretor questionsInterpretor;

    @Inject
    StylesVisitor        stylesVisitor;

    @Inject
    StylesInterpretor    stylesInterpretor;

    @PostConstruct
    public void postConstruct() {

        setLayout( new GridLayout( 0, 2 ) );
    }

    public void visualize() {

        removeAll();
        questionsInterpretor.visitSource( questionsVisitor );

        for ( Question question : questionsInterpretor.questions() ) {

            add( new JLabel( addHtmlTag( question.label() ) ) );

            add( visualizeQuestion( question ) );
        }
    }

    public void applyStyles() {

        removeAll();
        questionsInterpretor.visitSource( questionsVisitor );

        stylesInterpretor.visitSource( stylesVisitor );

        visualizeStyle( stylesInterpretor.styles() );

    }

    JPanel visualizeQuestion( Question question ) {

        JPanel parentPanel = new JPanel();

        BehaviouralType behaviouralType = question.getBehaviouralType();

        if ( behaviouralType == BehaviouralType.COMPUTED ) {

            return addToParent( parentPanel, new ComputedQuestionWidget( question ).jLabel );
        }

        Type type = question.type();

        if ( behaviouralType == BehaviouralType.ANSWERABLE ) {

            Optional<StyleElement> styleElement = Optional.of( new StyleElement( "Yes", "No" ) );

            switch ( type ) {

                case Boolean:
                    return addToParent( parentPanel, new BooleanWidget( question, styleElement ).jComponent() );

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

    private String addHtmlTag( String s ) {
        return "<html>" + s + "</html>";
    }

    private String addH1Tag( String s ) {
        return "<h1>" + s + "</h1>";
    }

    private void visualizeStyle( StyleTree<Style> style ) {

        if ( style.data().styleType() == StyleType.Question ) {

            Optional<Question> optional = questionsInterpretor.queryQuestion( new Identifier( style.data().string() ) );

            if ( optional.isPresent() ) {

                Question question = optional.get();

                JLabel jLabel = new JLabel( addHtmlTag( question.label() ) );
                String tooltip = addH1Tag( "&quot;Proof Of Concept&quot;<br/>" + "In QLS the placement for: " + style.data().string() );

                tooltip += "was found in section " +   style.parent().data().string();

                jLabel.setToolTipText( addHtmlTag( tooltip ) );

                add( jLabel );
                add( visualizeQuestion( question ) );
            }
        }

        for ( StyleTree<Style> each : style.children() ) {

            visualizeStyle( each );
        }
    }

    private static JPanel addToParent( JPanel parentPanel, JComponent component ) {

        JPanel childPanel = new JPanel();

        childPanel.add( component, new GridLayout( 0, 2 ) );
        parentPanel.add( childPanel );

        return parentPanel;
    }
}
