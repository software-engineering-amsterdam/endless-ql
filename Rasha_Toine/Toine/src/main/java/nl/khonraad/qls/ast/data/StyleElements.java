package nl.khonraad.qls.ast.data;

import java.util.ArrayList;
import java.util.List;


import nl.khonraad.ql.algebra.value.Type;

public class StyleElements {

    private List<StyleElement> styleElementList = new ArrayList<>();


    void saveElementDefault( StyleElement styleElement ) {
        styleElementList.add( styleElement );
    }

    StyleElement find( Type type ) {

        for ( StyleElement styleElement : styleElementList ) {
            if ( styleElement.type.equals( type ) ) {
                return styleElement;
            }
        }
        return null;
    }
}
