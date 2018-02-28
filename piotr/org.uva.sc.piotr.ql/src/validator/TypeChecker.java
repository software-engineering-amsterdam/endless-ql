package validator;

import exceptions.QLParserException;
import grammar.QLBaseVisitor;
import grammar.QLParser;

import java.util.*;

public class TypeChecker extends QLBaseVisitor {

    /*
        The type checker detects:
        # reference to undefined questions
        # duplicate question declarations with different types
        - conditions that are not of the type boolean
        - operands of invalid type to operators
        - cyclic dependencies between questions
        - duplicate labels (warning)
     */

    private Map<String, List<QLParser.DataTypeContext>> declaredFields = new HashMap<>();
    private List<QLParser.VariableReferenceContext> referredFields = new ArrayList<>();

    @Override
    public Object visitFieldDefinition(QLParser.FieldDefinitionContext ctx) {
        if (this.declaredFields.containsKey(ctx.fieldName.getText())) {
            this.declaredFields.get(ctx.fieldName.getText()).add(ctx.fieldType);
        } else {
            this.declaredFields.put(ctx.fieldName.getText(), new ArrayList<>(Arrays.asList(ctx.fieldType)));
        }
        return super.visitFieldDefinition(ctx);
    }

    @Override
    public Object visitVariableReference(QLParser.VariableReferenceContext ctx) {
        this.referredFields.add(ctx);
        return super.visitVariableReference(ctx);
    }

    public void validate() throws QLParserException {

        /* reference to undefined questions */
        for (QLParser.VariableReferenceContext field : this.referredFields) {
            if (!this.declaredFields.containsKey(field.getText())) {
                throw new QLParserException("Found a reference to undeclared field \"" + field.getText() + "\" in line " + field.start.getLine() + " of the parsed form.");
            }
        }

        /* duplicate question declarations with different types */
        /* TODO: Ask the SA if this is enough, or should I allow redeclaration of fields. */
        for (Map.Entry<String, List<QLParser.DataTypeContext>> entry : this.declaredFields.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new QLParserException("Field declared \"" + entry.getKey() + "\" more than once at line " + entry.getValue().get(0).start.getLine() + ".");
            }
        }

        /* conditions that are not of the type boolean */

        System.out.println("done");

    }

}
