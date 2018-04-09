package nl.khonraad.qls.language;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.qls.parser.StylesAST;
import nl.khonraad.qls.parser.StylesVisitor;

@ApplicationScoped public class StylesLanguage implements StylesInterpretor {

    @Inject
    Logger               logger;

    @Inject
    private StylesAST    qLsAstBuilder;

    private StylesMemory memory;

    @PostConstruct
    void postConstruct() {
        memory = new StylesMemory();
    }

    @Override
    public String visitSource( StylesVisitor visitor ) {
        return visitor.visit( qLsAstBuilder.getTree() );
    }

    @Override
    public void declareAsStyleSheet( StyleTree<Style> styleSheet ) {
        memory.addStylesheet( styleSheet );
    }

    @Override
    public void memoryDump() {
        memory.dump();
    }

    @Override
    public StyleTree<Style> styles() {
        return memory.styles();
    }
}
