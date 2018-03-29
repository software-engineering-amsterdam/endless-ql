package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.ql.expressions.form.Question;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.QuestionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;

import java.util.*;

public class QLSChecker {
    private static final String UNPLACED_FORM_EXPRESSION = "Unplaced form expression: ";
    private static final String INVALID_FORM_EXPRESSION = "Invalid form expression: ";
    private static final String DUPLICATE_REFERENCE_TO_FORM_EXPRESSION = "Duplicate reference to form expression: ";

    private ArrayList<String> errors;

    public ArrayList<String> checkForErrors(Form form, Stylesheet stylesheet) {
        errors = new ArrayList<>();

        HashMap<String, FormExpression> questionsByName        = getFormExpressions(form.getFormExpressions());
        HashMap<String, QuestionReference> questionRefsByName  = getQuestionReferences(stylesheet);

        checkDuplicateReferences(questionRefsByName.values());

        checkQuestionsExist(questionsByName.keySet(), questionRefsByName.keySet());

        checkWidgetAssignments(questionsByName, questionRefsByName, stylesheet.getDefaultStyles());

        return errors;
    }

    private void checkQuestionsExist(Set<String> questionNames, Set<String> questionRefNames) {
        compareLeftToRight(questionNames, questionRefNames, UNPLACED_FORM_EXPRESSION);
        compareLeftToRight(questionRefNames, questionNames, INVALID_FORM_EXPRESSION);
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
                errors.add(DUPLICATE_REFERENCE_TO_FORM_EXPRESSION + ref.getName());
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

    private HashMap<String, FormExpression> getFormExpressions(LinkedList<FormExpression> formExpressions) {
        HashMap<String, FormExpression> expressions = new HashMap<>();

        for(FormExpression formExpression : formExpressions) {
            expressions.put(formExpression.getName(), formExpression);
        }

        return expressions;
    }

    private void checkWidgetAssignments(
            HashMap<String, FormExpression> questions,
            HashMap<String, QuestionReference> questionRefs,
            LinkedList<DefaultStyle> defaultStyles
    ) {
        for (QuestionReference ref : questionRefs.values()) {
            FormExpression formExpression = questions.get(ref.getName());
            if (!(formExpression instanceof Question)) {
                continue;
            }

            DataType dataType = ((Question) formExpression).getDataType();
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
