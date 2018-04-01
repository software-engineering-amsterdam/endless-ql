package qls.analysis;

import ql.model.Form;
import qls.model.StyleSheet;

public interface IQLSAnalysis {

    void analyze(Form form, StyleSheet styleSheet);
}
