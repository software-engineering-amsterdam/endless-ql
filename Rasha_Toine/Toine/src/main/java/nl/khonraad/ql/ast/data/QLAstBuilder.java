package nl.khonraad.ql.ast.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;

import nl.khonraad.ql.QLLexer;
import nl.khonraad.ql.QLParser;
import nl.khonraad.ql.cdisupport.QLSource;

public class QLAstBuilder {

    ParseTree ast;

    @Inject
    Logger            logger;

    @Inject
    QLSource          qLSource;

    @PostConstruct
    public void postConstruct() {

        QLLexer qLexer;

        try {

            InputStream inputStream = getClass().getResourceAsStream( qLSource.getPath() );

            qLexer = new QLLexer( CharStreams.fromStream( inputStream, StandardCharsets.UTF_8 ) );

            QLParser qParser = new QLParser( new CommonTokenStream( qLexer ) );

            qParser.addErrorListener( new ErrorListener() );

            ast = qParser.form();

        } catch (IOException e) {
            logger.info( e.getMessage() );
            e.printStackTrace();
        }

    }

    private final class ErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e ) {
            throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
        }
    }
}
