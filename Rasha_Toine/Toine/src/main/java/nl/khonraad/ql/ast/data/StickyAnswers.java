package nl.khonraad.ql.ast.data;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;
import nl.khonraad.ql.algebra.value.Type;

public class StickyAnswers {

    private Map<Identifier, Value> stickyAnswers = new HashMap<>();

    private static Value initialValueOf( Type type ) {
        switch ( type ) {
            case Boolean:
                return new Value( false );
            case Date:
                return new Value( Type.Date, SimpleDateFormatter.string( new DateTime() ) );
            case Integer:
                return new Value( Type.Integer, "0" );
            case Money:
                return new Value( Type.Money, "0.00" );
            case String:
                return new Value( Type.String, "" );
        }
        throw new RuntimeException( "'Constructor' not implemented for type " + type );
    }

    void clear() {
        stickyAnswers.clear();
    }

    void add( Identifier identifier, Value value ) {
        stickyAnswers.put( identifier, value );
    }

    Value get( Identifier identifier, Type type ) {
        if ( stickyAnswers.containsKey( identifier ) ) {
            return stickyAnswers.get( identifier );
        }
        return initialValueOf( type );

    }

}
