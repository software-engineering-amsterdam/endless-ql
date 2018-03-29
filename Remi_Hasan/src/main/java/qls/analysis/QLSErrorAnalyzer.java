package qls.analysis;

import ql.model.Form;
import qls.model.StyleSheet;

import java.util.List;

public class QLSErrorAnalyzer implements IQLSAnalysis {

    // All analysis phases in order
    private final List<IQLSAnalysis> analysisPhases = List.of(
            new QuestionAnalyzer(),
            new TypeChecker());

    @Override
    public void analyze(Form form, StyleSheet styleSheet) {
        for (IQLSAnalysis analysisPhase : this.analysisPhases) {
            analysisPhase.analyze(form, styleSheet);
        }
    }
}
