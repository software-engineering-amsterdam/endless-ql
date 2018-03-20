package domain.model.stylesheet;

import domain.model.ast.QuestionASTNode;
import domain.model.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String label;
    private List<Variable> variables;

    public Section(String label) {
        this.label = label;
        this.variables = new ArrayList<>();
    }

    /**
     * Adds a Variable to the variables list.
     * @param v Variable to add to add
     */
    public void addVariable(Variable v) {
        this.variables.add(v);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Variable> getVariables() {
        return variables;
    }

}
