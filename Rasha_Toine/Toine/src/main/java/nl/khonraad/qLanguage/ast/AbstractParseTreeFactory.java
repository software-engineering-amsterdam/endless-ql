package nl.khonraad.qLanguage.ast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import nl.khonraad.qLanguage.QLexer;
import nl.khonraad.qLanguage.QParser;

public abstract class AbstractParseTreeFactory {

    public static QParser parseDataForTest( String testData ) throws IOException {
        InputStream stream = new ByteArrayInputStream( testData.getBytes( StandardCharsets.UTF_8 ) );

        QLexer QLexer = new QLexer(
                CharStreams.fromStream( stream, StandardCharsets.UTF_8 ) );

        QParser QParser = new QParser(
                new CommonTokenStream( QLexer ) );

        QParser.addErrorListener( new BaseErrorListener() {
            @Override
            public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                    int charPositionInLine, String msg, RecognitionException e ) {
                throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
            }
        } );
        return QParser;
    }

}
