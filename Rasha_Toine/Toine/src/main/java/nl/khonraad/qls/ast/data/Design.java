package nl.khonraad.qls.ast.data;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.qls.QLSVisitor;
import nl.khonraad.qls.ast.QLSAbstractSyntaxTreeBuilder;

@ApplicationScoped public class Design implements Styling {

    @Inject
    Logger                               logger;

    @Inject
    StyleElements                        styleElements;

    @Inject
    private QLSAbstractSyntaxTreeBuilder qLsAstBuilder;

    @Override
    public void visitSource( QLSVisitor<Value> visitor ) {

        try {

            visitor.visit( qLsAstBuilder.getTree() );

        } catch (IllegalStateException e) {

            logger.info( e.getMessage() );
        }

    }

    @Override
    public void storeElementDefault( StyleElement styleElement ) {
        styleElements.saveElementDefault( styleElement );
    }

    @Override
    public Optional<StyleElement> styleElement( Type type ) {
        return styleElements.find( type );
    }

}
