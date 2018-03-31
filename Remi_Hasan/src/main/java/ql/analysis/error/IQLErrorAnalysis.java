package ql.analysis.error;

import ql.evaluation.SymbolTable;
import ql.model.Form;

public interface IQLErrorAnalysis {

    void analyze(Form form, SymbolTable symbolTable);
}
