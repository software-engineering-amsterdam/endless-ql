package ql.analysis.error;

import ql.evaluation.SymbolTable;
import ql.model.Form;

import java.util.List;

public class QLErrorAnalyzer implements IQLErrorAnalysis {

    // All analysis phases in order
    private final List<IQLErrorAnalysis> analysisPhases = List.of(
            new UnknownIdentifiersDetector(),
            new CycleDetector(),
            new TypeChecker(),
            new InvalidDuplicateQuestionDetector());

    @Override
    public void analyze(Form form, SymbolTable symbolTable) {
        for (IQLErrorAnalysis analysisPhase : this.analysisPhases) {
            analysisPhase.analyze(form, symbolTable);
        }
    }
}
