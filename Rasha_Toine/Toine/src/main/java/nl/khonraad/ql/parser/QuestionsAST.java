package nl.khonraad.ql.parser;

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

import nl.khonraad.cdi.producers.SourcePathProvider;
import nl.khonraad.ql.QLLexer;
import nl.khonraad.ql.QLParser;

public class QuestionsAST {

    @Inject
    Logger             logger;

    @Inject
    SourcePathProvider sourcePathProvider;

    private ParseTree  parseTree;

    public ParseTree getTree() {
        return parseTree;
    }

    @PostConstruct
    public void postConstruct() {

        QLLexer qlLexer;

        try {

            InputStream inputStream = getClass().getResourceAsStream( sourcePathProvider.getSourcePathQL() );

            qlLexer = new QLLexer( CharStreams.fromStream( inputStream, StandardCharsets.UTF_8 ) );

            QLParser qlParser = new QLParser( new CommonTokenStream( qlLexer ) );

            qlParser.addErrorListener( new ErrorListener() );

            parseTree = qlParser.form();

        } catch (IOException e) {
            logger.info( e.getMessage() );
        }
    }

    private final class ErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg,
                RecognitionException e ) {
            throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
        }
    }
}
