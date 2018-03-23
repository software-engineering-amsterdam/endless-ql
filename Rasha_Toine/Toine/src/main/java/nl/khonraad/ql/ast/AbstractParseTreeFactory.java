package nl.khonraad.ql.ast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import nl.khonraad.ql.QLexer;
import nl.khonraad.ql.QParser;

public interface AbstractParseTreeFactory {

    public static QParser resourceToStream( String s ) throws IOException {
        InputStream stream = AbstractParseTreeFactory.class.getResourceAsStream( s );
        return parseData( stream );
    }

    public static QParser fromString( String s ) throws IOException {
        InputStream stream = new ByteArrayInputStream( s.getBytes( StandardCharsets.UTF_8 ) );
        return parseData( stream );
    }

    static QParser parseData( InputStream stream ) throws IOException {

        QLexer qLexer = new QLexer( CharStreams.fromStream( stream, StandardCharsets.UTF_8 ) );

        QParser qParser = new QParser( new CommonTokenStream( qLexer ) );

        qParser.addErrorListener( new BaseErrorListener() {
            @Override
            public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                    int charPositionInLine, String msg, RecognitionException e ) {
                throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
            }
        } );
        return qParser;
    }

}
