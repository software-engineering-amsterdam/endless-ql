package nl.khonraad.ql.domain;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public class StickyAnswers {

    private Map<Identifier, Value> stickyAnswersMap = new HashMap<>();

    private static Value initialValueOf( Type type ) {

        switch ( type ) {

            case Boolean:
                return Value.False;

            case Date:
                return Value.typed( Type.Date, SimpleDateFormatter.string( new DateTime() ) );

            case Integer:
                return Value.typed( Type.Integer, "0" );

            case Money:
                return Value.typed( Type.Money, "0.00" );

            case String:
                return Value.typed( Type.String, "" );
        }
        throw new RuntimeException( "'Constructor' not implemented for type " + type );
    }

    void clear() {

        stickyAnswersMap.clear();
    }

    void add( Identifier identifier, Value value ) {

        stickyAnswersMap.put( identifier, value );
    }

    Value get( Identifier identifier, Type type ) {

        if ( stickyAnswersMap.containsKey( identifier ) ) {

            return stickyAnswersMap.get( identifier );
        }
        return initialValueOf( type );
    }
}
