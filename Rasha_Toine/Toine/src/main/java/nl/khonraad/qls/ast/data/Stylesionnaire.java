package nl.khonraad.qls.ast.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.ql.gui.application.VisualizeEvent;
import nl.khonraad.qls.QLSBaseVisitor;
import nl.khonraad.qls.ast.QLSAbstractSyntaxTreeBuilder;

@ApplicationScoped public class Stylesionnaire {

    @Inject
    Logger                              logger;

    @Inject
    SourcePathProvider                  qLSource;

    @Inject
    Event<VisualizeEvent>               eventQueue;


    @Inject
    private QLSAbstractSyntaxTreeBuilder qLsAstBuilder;

    public void visitSource( QLSBaseVisitor<Value> visitor ) {
        
        try {
            
            visitor.visit( qLsAstBuilder.getTree() );
            
        } catch (IllegalStateException e) {
            
            logger.info( e.getMessage() );
        }
        
    }


}
