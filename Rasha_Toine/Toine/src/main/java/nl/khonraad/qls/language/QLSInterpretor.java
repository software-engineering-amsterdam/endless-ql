package nl.khonraad.qls.language;

import nl.khonraad.qls.QLSVisitor;

public interface QLSInterpretor {

    String visitSource( QLSVisitor<String> visitor );

    void memoryDump();

    void declareStyleSheet( StyleNodeTree<StyleNode> styleSheet );

    StyleNodeTree<StyleNode> nodes();
    
}