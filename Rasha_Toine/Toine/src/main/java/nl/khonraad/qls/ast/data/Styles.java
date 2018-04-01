package nl.khonraad.qls.ast.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import nl.khonraad.ql.algebra.value.Type;

@ApplicationScoped
public class Styles {

    List<Style> list;

    Styles() {
        list = new ArrayList<>();
    }

    public void saveDefault( Style style ) {
        list.add( style );
    }

    public Style find( Type type ) {

        for ( Style style : list ) {
            if ( style.type.equals( type ) ) {
                return style;
            }
        }
        return null;
    }
}
