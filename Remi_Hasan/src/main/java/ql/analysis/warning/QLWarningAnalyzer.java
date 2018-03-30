package ql.analysis.warning;

import ql.evaluation.SymbolTable;
import ql.model.Form;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QLWarningAnalyzer implements IQLWarningAnalysis {

    // All analysis phases in order
    private final List<IQLWarningAnalysis> analysisPhases = List.of(
            new DuplicateLabelDetector());

    @Override
    public Set<String> analyze(Form form, SymbolTable symbolTable) {
        Set<String> warnings = new HashSet<>();
        for (IQLWarningAnalysis analysisPhase : this.analysisPhases) {
            warnings.addAll(analysisPhase.analyze(form, symbolTable));
        }
        return warnings;
    }
}
