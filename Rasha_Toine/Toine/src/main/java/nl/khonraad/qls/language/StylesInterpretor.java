package nl.khonraad.qls.language;

import nl.khonraad.qls.parser.StylesVisitor;

public interface StylesInterpretor {

    /*
     * Sorted alphabetically for readability
     */
    
    void                declareAsStyleSheet( StyleTree<Style> styleSheet );

    void                memoryDump();

    StyleTree<Style>    styles();

    String              visitSource( StylesVisitor visitor );

}