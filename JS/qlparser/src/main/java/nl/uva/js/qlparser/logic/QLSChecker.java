package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.ql.expressions.form.IfBlock;
import nl.uva.js.qlparser.models.ql.expressions.form.Question;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.QuestionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class QLSChecker {
    public boolean validate(Form form, Stylesheet stylesheet) throws Exception {
        HashSet<String> questionReferences = getQuestionReferences(stylesheet);
        HashSet<String> questions = new HashSet<>();
        for(Question q : getQuestions(form.getFormExpressions())) {
            questions.add(q.getVariable().getName());
        }

        //Todo improve
        if(!questionReferences.equals(questions)) {
            throw new Exception("Questions references by QLS do not match QL");
        }

        return true;
    }

    private HashSet<String> getQuestionReferences(Stylesheet stylesheet) {
        LinkedList<QuestionReference> questionReferences = new LinkedList<>();

        LinkedList<Page> pages = stylesheet.getPages();
        if (null != pages) {
            LinkedList<Section> sections = new LinkedList<>();
            for (Page page : pages) {
                sections.addAll(page.getSections());
            }

            for (Section section : sections) {
                questionReferences.addAll(section.getQuestions());
            }
        }

        return questionReferences.stream()
                .map(QuestionReference::getName)
                .collect(Collectors.toCollection(HashSet::new));
    }

    private LinkedList<Question> getQuestions(LinkedList<FormExpression> formExpressions) {
        LinkedList<Question> questions = new LinkedList<>();

        for(FormExpression formExpression : formExpressions) {
            if (formExpression instanceof Question) {
                questions.add((Question) formExpression);
            } else if (formExpression instanceof IfBlock) {
                questions.addAll(getQuestions(((IfBlock) formExpression).getExpressions()));
            }
        }
        return questions;
    }
}
