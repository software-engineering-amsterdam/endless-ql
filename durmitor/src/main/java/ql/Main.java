package ql;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.form.Form;
import ql.ast.statement.Question;
import ql.ast.type.Type;
import ql.checker.TypeChecker;
import ql.visitors.ConditionCollector;
import ql.visitors.QuestionCollector;
import ql.visitors.ReferenceCollector;
import ql.visitors.SymbolTable;

public class Main {

    // private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // logger.info("Start");

        String filePath;
        QL ql;
        Form form = null;

        if (args.length == 0) {
            filePath = "resources/default.tax";
        } else {
            filePath = args[0];
        }

        ql = new QL(filePath);
        try {
            form = (Form) ql.getForm();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Visit and TypeCheck the AST
        TypeChecker checker             = new TypeChecker();
        Map<String, Type> symbolTable   = new SymbolTable(form).build();
        List<Identifier> references     = new ReferenceCollector().collect(form);
        List<Question> questions        = new QuestionCollector().collect(form);
        List<Expression> conditions     = new ConditionCollector().collect(form);
        
        checker.checkUndefinedRefs(references, symbolTable);
        checker.printErrors();
        
        checker = new TypeChecker();
        checker.checkConflictingQuestionTypes(questions);
        checker.printErrors();
        
        checker = new TypeChecker();
        checker.checkConditionTypes(conditions, symbolTable);
        checker.printErrors();
        
        checker = new TypeChecker();
        checker.checkDuplicateLabels(questions);
        checker.printWarnings();
        
        // Visit and build GUI from AST

        // Add Action/DocumentListeners to GUI.
    }
}
