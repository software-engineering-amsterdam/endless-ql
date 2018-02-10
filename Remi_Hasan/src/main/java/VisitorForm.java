import model.Block;
import model.Form;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorBlock visitorBlock = new VisitorBlock();
        Block block = visitorBlock.visitBlock(ctx.block());
        return new Form(ctx.IDENTIFIER().getText(), block);
    }

}
