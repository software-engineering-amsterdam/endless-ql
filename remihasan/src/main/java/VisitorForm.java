import java.util.ArrayList;
import java.util.stream.Collectors;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorBlock visitorBlock = new VisitorBlock();
        Block block = visitorBlock.visitBlock(ctx.block());
        return new Form(ctx.IDENTIFIER().getText(), block);
    }

}
