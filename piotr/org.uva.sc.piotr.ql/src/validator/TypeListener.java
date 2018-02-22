package validator;

import grammar.QLBaseListener;
import grammar.QLParser;

public class TypeListener extends QLBaseListener {

    @Override
    public void enterType(QLParser.TypeContext ctx) {
        System.out.println("type: " + ctx.getText());
    }

    @Override
    public void enterFieldDefinition(QLParser.FieldDefinitionContext ctx) {
        System.out.println("definition: " + ctx.getText());
    }
}
