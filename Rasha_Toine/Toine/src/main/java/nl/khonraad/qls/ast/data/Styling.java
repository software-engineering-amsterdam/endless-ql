package nl.khonraad.qls.ast.data;

import nl.khonraad.qls.QLSVisitor;

import java.util.Optional;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public interface Styling {

    void visitSource( QLSVisitor<Value> visitor );

    void storeElementDefault( StyleElement styleElement );

    Optional<StyleElement> styleElement( Type type );

}