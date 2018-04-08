package nl.khonraad.qls.language;

import nl.khonraad.qls.parser.StylesVisitor;

public interface StylesInterpretor {

    void                        declareAsStyleSheet( StyleNodeTree<StyleNode> styleSheet );

    void                        memoryDump();

    StyleNodeTree<StyleNode>    nodes();

    String                      visitSource( StylesVisitor visitor );

}