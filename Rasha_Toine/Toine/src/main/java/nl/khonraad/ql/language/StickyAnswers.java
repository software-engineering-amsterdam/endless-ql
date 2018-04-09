package nl.khonraad.ql.language;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.algebra.values.utility.SimpleDateFormatter;

class StickyAnswers {

    private Map<Identifier, Value> stickyAnswersMap = new HashMap<>();

    private static Value initialValueOf( Type type ) {

        switch ( type ) {

            case Boolean:
                return Value.False;

            case Date:
                return Value.of( Type.Date, SimpleDateFormatter.string( new DateTime() ) );

            case Integer:
                return Value.of( Type.Integer, "0" );

            case Money:
                return Value.of( Type.Money, "0.00" );

            case String:
                return Value.of( Type.String, "" );
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
