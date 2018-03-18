package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.ql.expressions.form.IfBlock;
import nl.uva.js.qlparser.models.ql.expressions.form.Question;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.QuestionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;

import java.util.*;
import java.util.stream.Collectors;

public class QLSChecker {
    private ArrayList<String> errors;

    public boolean checkErrors(Form form, Stylesheet stylesheet) throws Exception {
        errors.clear();

        HashMap<String, Question> questionsByName   = getQuestions(form.getFormExpressions());
        HashMap<String, QuestionReference> questionRefsByName  = getQuestionReferences(stylesheet);

        checkQuestions(questionsByName.keySet(), questionRefsByName.keySet());
        checkWidgetAssignments(questionsByName, questionRefsByName, stylesheet.getDefaultStyles());

        return true;
    }

    private void checkQuestions(Set<String> questionNames, Set<String> questionReferences) {
        checkDuplicateReferences(questionReferences);

        checkDifference(questionNames, questionReferences, "Unplaced QL question: ");
        checkDifference(questionReferences, questionNames, "Question does not exist: ");
    }

    private void checkDifference(Set<String> left, Set<String> right, String errorMessage) {
        ArrayList<String> difference = new ArrayList<>(left);
        difference.removeAll(right);

        for (String question : difference) {
            errors.add(errorMessage + question);
        }
    }

    private void checkDuplicateReferences(Set<String> questionReferences) {
        HashSet<String> uniqueQuestionReferences = new HashSet<>(questionReferences);
        if (questionReferences.size() > uniqueQuestionReferences.size()) {
            errors.add("A single question may not be placed multiple times.");
        }
    }

    private ArrayList<String> getQuestionNames(ArrayList<Question> questions) {
        return questions.stream().map(q -> q.getVariable().getName()).collect(Collectors.toCollection(ArrayList::new));
    }

    private HashMap<String, QuestionReference> getQuestionReferences(Stylesheet stylesheet) {
        HashMap<String, QuestionReference> questionReferences = new HashMap<>();

        LinkedList<Page> pages = stylesheet.getPages();
        if (null != pages) {
            LinkedList<Section> sections = new LinkedList<>();
            pages.stream().map(Page::getSections).forEach(sections::addAll);

            for (Section section : sections) {
                LinkedList<QuestionReference> refs = section.getQuestions();
                refs.forEach(ref -> questionReferences.put(ref.getName(), ref));
            }
        }
        return questionReferences;
    }

    private HashMap<String, Question> getQuestions(LinkedList<FormExpression> formExpressions) {
        HashMap<String, Question> questions = new HashMap<>();

        for(FormExpression formExpression : formExpressions) {
            if (formExpression instanceof Question) {
                questions.put(((Question) formExpression).getVariable().getName(), (Question) formExpression);
            } else if (formExpression instanceof IfBlock) {
                HashMap<String, Question> ifQuestions = getQuestions(((IfBlock) formExpression).getExpressions());
                questions.putAll(ifQuestions);
            }
        }
        return questions;
    }

    private void checkWidgetAssignments(
            HashMap<String, Question> questions,
            HashMap<String, QuestionReference> questionRefs,
            LinkedList<DefaultStyle> defaultStyles
    ) {

    }
}
