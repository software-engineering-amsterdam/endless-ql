package nl.khonraad.qls.ast.data;

import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.algebra.value.Value;
import nl.khonraad.qls.QLSVisitor;

public interface Styling {

    void visitSource( QLSVisitor<Value> visitor );

    void storeElementDefault( StyleElement styleElement );

    StyleElement find( Type type );

}