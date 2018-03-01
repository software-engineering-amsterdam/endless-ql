package validator;

import exceptions.QLParserException;
import grammar.QLBaseVisitor;
import grammar.QLParser;
import models.guestions.QuestionDependencyForest;
import models.guestions.graph.elements.QuestionVertex;

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
    private List<QLParser.QuestionContext> questions = new ArrayList<>();
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

    @Override
    public Object visitQuestion(QLParser.QuestionContext ctx) {
        this.questions.add(ctx);
        return super.visitQuestion(ctx);
    }

    public void validate() throws QLParserException {



        /* duplicate question declarations with different types */
        /* TODO: Ask the SA if this is enough, or should I allow redeclaration of fields. */
        for (Map.Entry<String, List<QLParser.DataTypeContext>> entry : this.declaredFields.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new QLParserException("Field declared \"" + entry.getKey() + "\" more than once at line " + entry.getValue().get(0).start.getLine() + ".");
            }
        }

        /* TODO: conditions that are not of the type boolean */

        /* TODO: operands of invalid type to operators */

        /* cyclic dependencies between questions */

        // build graph
        QuestionDependencyForest qdf = new QuestionDependencyForest();

        for (QLParser.QuestionContext question : this.questions) {
            qdf.addQuestion(new QuestionVertex(
                    question.label.getText(),
                    question.fieldDefinition().fieldName.getText(),
                    question
            ));
        }
//
//        for (QLParser.VariableReferenceContext reference : this.referredFields) {
//            try {
//                QuestionVertex vertex = qdf.getQuestionVertexByName(reference.name.getText());
//
//            } catch (Exception e) {
//                /* reference to undefined questions */
//                throw new QLParserException("Found a reference to undeclared field \"" + reference.getText() + "\" in line " + reference.start.getLine() + " of the parsed form.");
//            }
//        }

        /* TODO: duplicate labels (warning) */

        System.out.println("Type checker finish");

    }

}
