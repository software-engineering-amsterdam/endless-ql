import antlr.QLLexer;
import antlr.QLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import visitor.VisitorExpression;

class ANTLRTester {

    final QLParser parser;
    final VisitorExpression visitor;

    ANTLRTester(String input){
        QLLexer lexer = new QLLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        this.parser = new QLParser(tokens);

        // Walk it and attach our listener
        this.visitor = new VisitorExpression();
    }
}
