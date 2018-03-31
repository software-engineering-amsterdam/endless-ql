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
import nl.khonraad.ql.cdisupport.QLSource;
import nl.khonraad.qls.QLSLexer;
import nl.khonraad.qls.QLSParser;

public class QLSAstBuilder {

    public ParseTree ast;

    @Inject
    Logger            logger;

    @Inject
    QLSource          qLSource;

    @PostConstruct
    public void postConstruct() {

        QLSLexer qLexer;

        try {

            InputStream inputStream = getClass().getResourceAsStream( qLSource.getPath() );

            qLexer = new QLSLexer( CharStreams.fromStream( inputStream, StandardCharsets.UTF_8 ) );

            QLSParser qParser = new QLSParser( new CommonTokenStream( qLexer ) );

            qParser.addErrorListener( new ErrorListener() );

            ast = qParser.stylesheet();

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
