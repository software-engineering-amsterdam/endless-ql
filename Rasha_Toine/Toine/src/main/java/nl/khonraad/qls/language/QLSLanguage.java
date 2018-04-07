package nl.khonraad.qls.language;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.qls.QLSVisitor;
import nl.khonraad.qls.ast.QLSAbstractSyntaxTreeBuilder;

@ApplicationScoped public class QLSLanguage implements QLSInterpretor {

    @Inject
    Logger                               logger;

    @Inject
    private QLSAbstractSyntaxTreeBuilder qLsAstBuilder;

    private Memory                      memory;

    @PostConstruct
    void postConstruct() {

        memory = new Memory();
    }
    
    public String visitSource( QLSVisitor<String> visitor ) {

        return visitor.visit( qLsAstBuilder.getTree() );
    }

    @Override
    public void dump() {
        memory.dump();
    }
    @Override
    public void declareStyleSheet( TreeNode<String> styleSheet ) {
        memory.addStylesheet( styleSheet );
        
    }

}
