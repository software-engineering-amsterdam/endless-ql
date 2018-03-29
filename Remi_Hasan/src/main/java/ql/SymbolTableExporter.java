package ql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.expression.Expression;
import ql.model.statement.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTableExporter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public static void export(QLEvaluator qlEvaluator) {
        Form form = qlEvaluator.getForm();

        Map<String, Object> evaluatedQuestions = new LinkedHashMap<>();

        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                evaluatedQuestions.put(question.getIdentifier(), qlEvaluator.getAnswer(question.getIdentifier()).toString());
                return super.visit(question);
            }
        });

//        System.out.println("Exporting symbol table to " + exportFile.getAbsolutePath());
//
//        PrintWriter printWriter = new PrintWriter(exportFile);
//        Map<String, Expression> table = symbolTable.getTable();
//
//
//        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
//        Map<String, Object> evaluatedTable = new HashMap<>();
//        for (Map.Entry<String, Expression> tableEntry : table.entrySet()) {
//            String identifier = tableEntry.getKey();
//            Expression expression = tableEntry.getValue();
//            Value evaluatedValue = expressionEvaluator.visit(expression);
//            evaluatedTable.put(identifier, evaluatedValue.isUndefined() ? null : evaluatedValue.value);
//        }
//
        String json = gson.toJson(evaluatedQuestions);

        System.out.println("---------");
        System.out.println(json);
        System.out.println("---------");
//        printWriter.write(json);
//        printWriter.close();
    }
}
