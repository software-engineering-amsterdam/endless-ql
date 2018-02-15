import expression.Expression;
import model.Block;
import model.BlockElement;
import model.Form;
import model.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorBlockElement visitorBlockElement = new VisitorBlockElement();

        ArrayList<BlockElement> elements = new ArrayList<>();
        for(QLParser.BlockElementContext blockElementContext : ctx.block().blockElement()){
            BlockElement blockElement = visitorBlockElement.visit(blockElementContext);
            elements.add(blockElement);
        }

        return new Form(ctx.IDENTIFIER().getText(), elements, visitorBlockElement.getLookupTable());
    }

}
