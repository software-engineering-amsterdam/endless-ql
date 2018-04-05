package nl.khonraad.qls.ast.data;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.values.Type;


public class StyleElements {

    private List<StyleElement> styleElementList = new ArrayList<>();


    void saveElementDefault( StyleElement styleElement ) {
        styleElementList.add( styleElement );
    }

    Optional<StyleElement> find( Type type ) {

        for ( StyleElement styleElement : styleElementList ) {
            if ( styleElement.type.equals( type ) ) {
                return Optional.of( styleElement );
            }
        }
        return Optional.empty();
    }
}
