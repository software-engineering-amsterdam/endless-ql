package nl.khonraad.ql.gui.visuals;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;

import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.cdi.LoggingAspect;
import nl.khonraad.ql.language.QLInterpretor;
import nl.khonraad.ql.language.Question;
import nl.khonraad.ql.language.Question.BehaviouralType;
import nl.khonraad.qls.ast.ExtendedQLSBaseVisitor;
import nl.khonraad.qls.language.QLSInterpretor;
import nl.khonraad.qls.language.StyleElement;
import nl.khonraad.qls.language.StyleNode;
import nl.khonraad.qls.language.StyleNodeTree;
import nl.khonraad.qls.language.StyleNodeTree.NodeType;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.values.Type;

@SuppressWarnings( "serial" )

public class WidgetContainer extends Panel {

    @Inject
    Logger                 logger;

    @Inject
    ExtendedQLBaseVisitor  extendedQLBaseVisitor;

    @Inject
    ExtendedQLSBaseVisitor extendedQLSBaseVisitor;

    @Inject
    QLInterpretor          qlInterpretor;

    @Inject
    QLSInterpretor         qlsInterpretor;

    @PostConstruct
    public void postConstruct() {

        setLayout( new GridLayout( 0, 2 ) );
    }

    public void visualize() {

        removeAll();
        qlInterpretor.visitSource( extendedQLBaseVisitor );

        for ( Question question : qlInterpretor.questions() ) {

            add( new JLabel( question.label() ) );

            add( visualizeQuestion( question ) );
        }
    }

    public void visualizeStyled() {
    
        removeAll();
        qlInterpretor.visitSource( extendedQLBaseVisitor );
    
        qlsInterpretor.visitSource( extendedQLSBaseVisitor );
        // qlsInterpretor.memoryDump();
    
        visualizeTree( qlsInterpretor.nodes() );
    
    }

    JPanel visualizeQuestion( Question question ) {
    
        JPanel parentPanel = new JPanel();
    
        BehaviouralType behaviouralType = question.getBehaviouralType();
    
        if ( behaviouralType == BehaviouralType.COMPUTED ) {
    
            return addToParent( parentPanel, new ComputedQuestionWidget( question ).jLabel );
        }
    
        Type type = question.type();
    
        if ( behaviouralType == BehaviouralType.ANSWERABLE ) {
    
            Optional<StyleElement> styleElement = Optional.of( new StyleElement( "VOORTRUE", "VOORFALSE" ) );
    
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

    private void visualizeTree( StyleNodeTree<StyleNode> node ) {

        if ( node.data().nodeType() == NodeType.Question ) {

            
            Optional<Question> optional = qlInterpretor.queryQuestion( new Identifier( node.data().string() ) );
            if ( optional.isPresent() ) {

                Question question = optional.get();

                JLabel jLabel = new JLabel( addHtmlTag( question.label() ) );
                jLabel.setToolTipText( addHtmlTag( addH1Tag( node.data().nodeType().name() ) + node.data().string() ) );
                add( jLabel );
                add( visualizeQuestion( question ) );
            }
        }

        for ( StyleNodeTree<StyleNode> each : node.children() ) {

            visualizeTree( each );
        }
    }

    private static JPanel addToParent( JPanel parentPanel, JComponent component ) {

        JPanel childPanel = new JPanel();

        childPanel.add( component, new GridLayout( 0, 2 ) );
        parentPanel.add( childPanel );

        return parentPanel;
    }
}
