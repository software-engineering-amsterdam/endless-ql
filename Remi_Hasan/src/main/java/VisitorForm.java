import expression.Expression;
import model.Block;
import model.Form;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        ArrayList<Expression> conditions = new ArrayList<>();
        VisitorBlock visitorBlock = new VisitorBlock(conditions);
        Block block = visitorBlock.visitBlock(ctx.block());
        return new Form(ctx.IDENTIFIER().getText(), block);
    }

}
