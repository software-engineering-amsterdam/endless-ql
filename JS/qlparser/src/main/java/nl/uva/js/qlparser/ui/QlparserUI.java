package nl.uva.js.qlparser.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import nl.uva.js.qlparser.evaluator.Evaluator;

@SpringUI
@Theme("valo")
public class QlparserUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        TextArea area = new TextArea("Enter Questionnaire Language:");
        area.setWidth("700");
        area.setHeight("500");

        final Button btnRender = new Button("Render QL");

        setContent(new VerticalLayout(area, btnRender));

        btnRender.addClickListener(
                e -> setContent(fillQuestionnaire())
        );
    }

    private VerticalLayout fillQuestionnaire() {
        VerticalLayout layout = new VerticalLayout();

//        ParseTree parseTree = null;

        boolean parseSuccess = true;


        try {
            //Lex (with Antlr's generated lexer)
//            ANTLRInputStream inputStream = new ANTLRInputStream(inputPane.getText());
//            QlLexer lexer = new QlLexer(inputStream);
//            lexer.addErrorListener(this);
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//
//            //Parse (with Antlr's generated parser)
//            QlParser parser = new QlParser(tokens);
//            parser.addErrorListener(this);
//            parseTree = parser.questions();
        } catch (Exception e) {
//            parseSuccess = false;
        }

        if (parseSuccess) {
            //Extract AST from the Antlr parse tree
//            feedbackPane.addLine("Building AST...");
//            ASTBuilder builder = new ASTBuilder();
//            ParseTreeWalker walker = new ParseTreeWalker();
//            walker.walk(builder, parseTree);
//            AST ast = builder.getAST();

            //Checking
//            Checker checker = new Checker();

//            ArrayList<SemanticError> errors = checker.check(ast);
//            if (!errors.isEmpty()) {
//                for (SemanticError e : errors) {
//                }
//            }

            Evaluator evaluator = new Evaluator();
//            layout = evaluator.eval(ast);
        }

        return layout;
    }
}
