package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import model.BlockElement;
import model.Form;

import java.util.ArrayList;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorBlockElement visitorBlockElement = new VisitorBlockElement();

        ArrayList<BlockElement> elements = new ArrayList<>();
        for(QLParser.BlockElementContext blockElementContext : ctx.block().blockElement()){
            BlockElement blockElement = visitorBlockElement.visit(blockElementContext);
            elements.add(blockElement);
        }

        return new Form(ctx.IDENTIFIER().getText(), elements);
    }

}
