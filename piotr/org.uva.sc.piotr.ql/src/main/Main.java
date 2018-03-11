package main;

import ast.ASTBuilder;
import ast.model.Form;
import ast.model.expressions.Expression;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.collectors.CollectFormFieldModelsVisitor;
import ast.visitors.collectors.CollectQuestionsVisitor;
import ast.visitors.collectors.CollectReferencesVisitor;
import ast.visitors.evaluators.ExpressionEvaluator;
import ast.visitors.evaluators.ExpressionResult;
import grammar.QLLexer;
import grammar.QLParser;
import gui.QLGui;
import gui.model.FormQuestion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import validators.QuestionsDependencyValidator;
import validators.QuestionsValidator;
import validators.VariablesReferencesValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form1.qlform");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.visitForm(formContext);

        // Collect all references from all expressions in the form (both: assignments and conditions)
        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
        form.accept(collectReferencesVisitor);
        ArrayList<VariableReference> references = collectReferencesVisitor.getVariableReferences();

        // Collect all questions
        CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
        form.accept(collectQuestionsVisitor);
        ArrayList<Question> questions = collectQuestionsVisitor.getQuestions();

        // Validate questions against cyclic dependency @TODO: finish
        HashMap<Question, ArrayList<VariableReference>> questionsMap = collectQuestionsVisitor.getQuestionsMap();
        QuestionsDependencyValidator questionsDependencyValidator = new QuestionsDependencyValidator(questionsMap);

        // Validate undeclared variables usage in questions and conditions
        VariablesReferencesValidator.validateVariablesUsage(questions, references);

        // Validate duplicate question declarations with different types
        QuestionsValidator.validateDuplicates(questions);

        // Validate duplicate labels (warning)
        try {
            QuestionsValidator.validateLabels(questions);
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
        }

        // TODO: validate conditions that are not of the type boolean

        // TODO: operands of invalid type to operators

//        HashMap<String, ExpressionResult> variablesValues = new HashMap<>();
//
//        // Default empty setup
//        for (VariableReference reference : references) {
//            for (Question question : questions) {
//                if (question.getVariableName().equals(reference.getName())) {
//                    if (question.getVariableType().toDataType() == Expression.DataType.STRING) {
//                        variablesValues.put(question.getVariableName(), ExpressionResult.createExpressionResult(Expression.DataType.STRING, ""));
//                    } else if (question.getVariableType().toDataType() == Expression.DataType.DECIMAL) {
//                        variablesValues.put(question.getVariableName(), ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "0"));
//                    } else if (question.getVariableType().toDataType() == Expression.DataType.INTEGER) {
//                        variablesValues.put(question.getVariableName(), ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "0"));
//                    } else if (question.getVariableType().toDataType() == Expression.DataType.BOOLEAN) {
//                        variablesValues.put(question.getVariableName(), ExpressionResult.createExpressionResult(Expression.DataType.BOOLEAN, "FALSE"));
//                    }
//                }
//            }
//        }
//
//
//        // evaluate each variable
//        List<ExpressionResult> evaluatedExpressions = new ArrayList<>();
//
//        ExpressionEvaluator evaluator = new ExpressionEvaluator(variablesValues);
//        for (Question q : questions) {
//            if (q.getAssignedExpression() != null) {
//                evaluatedExpressions.add(q.getAssignedExpression().accept(evaluator));
//            }
//        }

//        for (VariableReference variableReference : references) {
//            variablesValues.put(variableReference.getName(), ExpressionResult.createExpressionResult(variableReference.))
//        }


        CollectFormFieldModelsVisitor collectFormFieldModelsVisitor = new CollectFormFieldModelsVisitor();
        form.accept(collectFormFieldModelsVisitor);

        List<FormQuestion> formQuestions = collectFormFieldModelsVisitor.getFormQuestions();
        new QLGui(formQuestions);


//        Gson gson = new Gson();
//        System.out.println(gson.toJson(form));


        System.out.println("Main finish.");


    }
}
