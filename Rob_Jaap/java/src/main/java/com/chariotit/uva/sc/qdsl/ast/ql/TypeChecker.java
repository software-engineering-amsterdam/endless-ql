package com.chariotit.uva.sc.qdsl.ast.ql;


import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckErrorComparator;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.CycleChecker;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.CycleError;
import com.chariotit.uva.sc.qdsl.ast.ql.node.QLAstRoot;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.CycleDetectionVisitor;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.SymbolTableBuilderVisitor;
import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.TypeCheckVisitor;

import java.util.List;

/**
 * Should check:
 * <p>
 * cyclic dependencies between questions -> Need Symboltable
 * duplicate labels (warning) -> Can do
 * <p>
 * Check cyclic dependencies
 */
public class TypeChecker {

    public List<TypeCheckError> typeCheckAst(QLAstRoot astRoot) {

        // First run. Build symbol table
        SymbolTableBuilderVisitor symbolTableVisitor = new SymbolTableBuilderVisitor();
        astRoot.acceptVisitor(symbolTableVisitor);

        if (symbolTableVisitor.getErrors().size() > 0) {
            return symbolTableVisitor.getErrors();

        }

        // Second run. TypeNode checker
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(astRoot.getSymbolTable());
        astRoot.acceptVisitor(typeCheckVisitor);

        // Cycledetection
        CycleDetectionVisitor cycleDetectionVisitor = new CycleDetectionVisitor();
        astRoot.acceptVisitor(cycleDetectionVisitor);
        CycleChecker cycleChecker = new CycleChecker(cycleDetectionVisitor.getDependencyTree());
        List<CycleError> cycleErrors = cycleChecker.checkCycles();

        List<TypeCheckError> typeCheckErrors = typeCheckVisitor.getErrors();

        for (CycleError cycleError : cycleErrors) {
            typeCheckErrors.add(
                    new TypeCheckError(
                            String.format("Cyclic dependency between '%s' and '%s'",
                                    cycleError.getNodeA().getLabel(),
                                    cycleError.getNodeB().getLabel()),
                            new SourceFilePosition(0, 0)
                    )
            );
        }

        typeCheckErrors.sort(new TypeCheckErrorComparator());

        return typeCheckErrors;
    }
}
