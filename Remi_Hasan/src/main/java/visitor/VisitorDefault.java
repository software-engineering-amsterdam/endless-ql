package visitor;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.Default;

public class VisitorDefault extends QLSBaseVisitor<Default> {
    @Override
    public Default visitDefault_(QLSParser.Default_Context ctx) {
        // TODO
        return new Default();
    }
}
