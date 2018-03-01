package analysis;

import model.Form;
import model.Question;

public class TypeChecker {
    private Form form;
    private SymbolTable symbolTable;

    public TypeChecker(Form form, SymbolTable symbolTable) {
        this.form = form;
        this.symbolTable = symbolTable;
    }

    public void typeCheck() {
        for(Question question : form.questions) {
            // Type check condition and answer expression
            question.condition.typeCheck(this.symbolTable);
            question.defaultAnswer.typeCheck(this.symbolTable);

            // Type check question default value assignment
            question.typeCheck(this.symbolTable);
        }
    }


}
