package nl.khonraad.qls.ast;

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

import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.qls.QLSLexer;
import nl.khonraad.qls.QLSParser;

public class QLSAbstractSyntaxTreeBuilder {

    @Inject
    Logger           logger;

    @Inject
    SourcePathProvider         qLSource;

    private ParseTree tree;

    public ParseTree getTree() {
        return tree;
    }

    @PostConstruct
    public void postConstruct() {

        QLSLexer qLsLexer;

        try {

            InputStream inputStream = getClass().getResourceAsStream( qLSource.getSourcePathQLS() );

            qLsLexer = new QLSLexer( CharStreams.fromStream( inputStream, StandardCharsets.UTF_8 ) );

            QLSParser qLsParser = new QLSParser( new CommonTokenStream( qLsLexer ) );

            qLsParser.addErrorListener( new ErrorListener() );

            tree = qLsParser.stylesheet();

        } catch (IOException e) {
            logger.info( e.getMessage() );
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
