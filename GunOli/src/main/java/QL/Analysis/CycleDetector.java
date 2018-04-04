package QL.Analysis;

import QL.AST.Form;
import QL.AST.Question;

public class CycleDetector {
    private final Form form;

    public CycleDetector(Form form){
        this.form = form;
    }

    public void detectCycles(){
        for(Question question : form.getQuestions()){
        }
    }
}
