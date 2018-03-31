package nl.khonraad.ql.algebra.formatters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class SimpleDateFormatter {

    private static DateTimeFormatter simpleDateFormat = DateTimeFormat.forPattern( "dd/MM/yyyy" );

    public static DateTime parseDateTime( String string ) {
        return simpleDateFormat.parseDateTime( string );
    }

    public static String string( DateTime dateTime ) {
        return simpleDateFormat.print( dateTime );
    }

}
