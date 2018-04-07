package nl.khonraad.qls.language;

import nl.khonraad.qls.QLSVisitor;

public interface QLSInterpretor {

    String visitSource( QLSVisitor<String> visitor );

    void dump();

    void declareStyleSheet( TreeNode<String> styleSheet );

}