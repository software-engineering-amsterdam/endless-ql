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
            question.typeCheck(this.symbolTable);
        }
    }


}
