package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.ql.expressions.form.IfBlock;
import nl.uva.js.qlparser.models.ql.expressions.form.Question;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.QuestionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;

import java.util.*;

public class QLSChecker {
    public static final String UNPLACED_QL_QUESTION = "Unplaced QL question: ";
    public static final String QUESTION_DOES_NOT_EXIST = "Question does not exist: ";
    public static final String DUPLICATE_REFERENCE_TO_QUESTION = "Duplicate reference to question: ";

    private ArrayList<String> errors;

    public ArrayList<String> checkForErrors(Form form, Stylesheet stylesheet) {
        errors.clear();

        HashMap<String, Question> questionsByName              = getQuestions(form.getFormExpressions());
        HashMap<String, QuestionReference> questionRefsByName  = getQuestionReferences(stylesheet);

        checkDuplicateReferences(questionRefsByName.values());

        checkQuestionsExist(questionsByName.keySet(), questionRefsByName.keySet());

        checkWidgetAssignments(questionsByName, questionRefsByName, stylesheet.getDefaultStyles());

        return errors;
    }

    private void checkQuestionsExist(Set<String> questionNames, Set<String> questionRefNames) {
        compareLeftToRight(questionNames, questionRefNames, UNPLACED_QL_QUESTION);
        compareLeftToRight(questionRefNames, questionNames, QUESTION_DOES_NOT_EXIST);
    }

    private void compareLeftToRight(Set<String> left, Set<String> right, String errorMessage) {
        ArrayList<String> difference = new ArrayList<>(left);
        difference.removeAll(right);

        for (String question : difference) {
            errors.add(errorMessage + question);
        }
    }

    private void checkDuplicateReferences(Collection<QuestionReference> questionReferences) {
        HashSet<String> uniques = new HashSet<>();
        for(QuestionReference ref : questionReferences) {
            if(!uniques.add(ref.getName())) {
                errors.add(DUPLICATE_REFERENCE_TO_QUESTION + ref.getName());
            }
        }
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
        for (QuestionReference ref : questionRefs.values()) {
            DataType dataType = questions.get(ref.getName()).getDataType();
            WidgetType widgetType = ref.getWidgetType();

            if (null != widgetType && !WidgetType.mapDataTypeToWidget.get(dataType).contains(widgetType)) {
                errors.add("Can not use widget " + widgetType.name().toLowerCase()
                           + " for question " + ref.getName() + "with data type " + dataType.name().toLowerCase());
            }
        }

        for (DefaultStyle def : defaultStyles) {
            DataType dataType = def.getDataType();
            WidgetType widgetType = def.getWidgetType();
            if (!WidgetType.mapDataTypeToWidget.get(dataType).contains(widgetType)) {
                errors.add("Can not use widget " + widgetType.name().toLowerCase()
                           + " for data type " + dataType.name().toLowerCase());
            }
        }
    }
}
