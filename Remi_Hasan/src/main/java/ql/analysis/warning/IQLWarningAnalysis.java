package ql.analysis.warning;

import ql.evaluation.SymbolTable;
import ql.model.Form;

import java.util.Set;

public interface IQLWarningAnalysis {

    Set<String> analyze(Form form, SymbolTable symbolTable);
}
