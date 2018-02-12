package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLLexer;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.models.Form;
import nl.uva.js.qlparser.models.formexpressions.FormExpression;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Component
public class Ingester {
    @Autowired
    private TypeRegistry typeRegistry;

    public Form toParsedForm(String location) throws IOException {
        QLLexer lex = new QLLexer(CharStreams.fromPath(Paths.get(location)));
        QLParser parser = new QLParser(new CommonTokenStream(lex));

        Form.FormBuilder formBuilder = Form.builder();

        formBuilder.name(parser.form().NAME().getSymbol().getText());
        formBuilder.formExpressions(parser.form().formBlock().formExpression()
                .stream()
                .map(this::ingestFormExpressions)
                .collect(Collectors.toCollection(LinkedList::new))
        );

        return formBuilder.build();
    }

    private FormExpression ingestFormExpressions(QLParser.FormExpressionContext c) {


        return null;
    }
}
