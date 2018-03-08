package analysis;

import model.Form;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class UnknownIdentifiersDetector {

    private final Form form;

    public UnknownIdentifiersDetector(Form form){
        this.form = form;
    }

    public List<String> detectUnknownIdentifiers(){
        ReferencedIdentifiersVisitor referencedIdentifiersVisitor = new ReferencedIdentifiersVisitor();

        List<String> formQuestionIdentifiers = new ArrayList<>();
        List<String> referencedIdentifiers = new ArrayList<>();
        for(Question question : form.questions){
            formQuestionIdentifiers.add(question.name);
            referencedIdentifiers.addAll(referencedIdentifiersVisitor.visit(question.defaultAnswer));
            referencedIdentifiers.addAll(referencedIdentifiersVisitor.visit(question.condition));
        }

        // Determine which identifiers are referenced but no question exists with such identifier
        // Subtraction of formQuestionIdentifiers - referencedIdentifiers
        List<String> unknownReferencedIdentifiers = new ArrayList<>(referencedIdentifiers);
        unknownReferencedIdentifiers.removeAll(formQuestionIdentifiers);

        return unknownReferencedIdentifiers;
    }
}
