package QLS.Analysis;

import QL.AST.Form;
import QL.AST.Question;
import QL.Evaluation.EvaluationType;
import QLS.AST.Page;
import QLS.AST.Statements.Default;
import QLS.AST.Statements.QLSQuestion;
import QLS.AST.Statements.Statement;
import QLS.AST.StyleAttribute.Style;
import QLS.AST.Stylesheet;
import QLS.AST.Widgets.Widget;
import QLS.AST.Widgets.WidgetType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QLSTypeChecker implements WidgetVisitorInterface<WidgetType>{

    Stylesheet stylesheet;
    Form form;

    public QLSTypeChecker(Stylesheet stylesheet, Form form){
        this.stylesheet = stylesheet;
        this.form = form;
    }

    public void typeCheck(){
        stylesheet.accept(this);
    }

    private boolean checkCompatibility(WidgetType widgetType, EvaluationType evaluationType){
        switch(evaluationType){
        case Date:
            return false;
        case Decimal:
        case Integer:
        case Money:
            return (widgetType == WidgetType.SpinBox || widgetType == WidgetType.Slider || widgetType == WidgetType.Text);
        case String:
            return widgetType == WidgetType.Text;
        case Boolean:
            return widgetType == WidgetType.CheckBox || widgetType == WidgetType.Radio || widgetType == WidgetType.Dropdown;
        }

        return false;
    }

    public void detectDuplicateQuestions(){
        Set<String> checkedQuestions = new HashSet<>();
        for(QLSQuestion qlsQuestion : stylesheet.getQlsQuestions()){
            if(checkedQuestions.contains(qlsQuestion.getIdentifier())){
                throw new IllegalArgumentException("Invalid assignment: question with identifier '" + qlsQuestion.getIdentifier()
                                                    + "' already defined.");
            }
            checkedQuestions.add(qlsQuestion.getIdentifier());
        }
    }

    public void detectUnknownReferences(){
        List<String> qlQuestionIdentifiers = new ArrayList<>();
        List<String> qlsQuestionIdentifiers = new ArrayList<>();

        for(Question question : form.getQuestions()){
            qlQuestionIdentifiers.add(question.getIdentifier());
        }

        for(QLSQuestion qlsQuestion : stylesheet.getQlsQuestions()){
            qlsQuestionIdentifiers.add(qlsQuestion.getIdentifier());
        }

        List<String> unknownReferences = calculateReferencesDifference(qlQuestionIdentifiers, qlsQuestionIdentifiers);

        if(!unknownReferences.isEmpty()){
            throw new IllegalArgumentException("Unknown References: references not found in QL form: " + unknownReferences);
        }
    }

    public void detectUnplacedQuestions(){
        List<String> qlQuestionIdentifiers = new ArrayList<>();
        List<String> qlsQuestionIdentifiers = new ArrayList<>();

        for(Question question : form.getQuestions()){
            qlQuestionIdentifiers.add(question.getIdentifier());
        }

        for(QLSQuestion qlsQuestion : stylesheet.getQlsQuestions()){
            qlsQuestionIdentifiers.add(qlsQuestion.getIdentifier());
        }

        List<String> undefinedQLSQuestions = calculateReferencesDifference(qlsQuestionIdentifiers, qlQuestionIdentifiers);

        if(!undefinedQLSQuestions.isEmpty()){
            throw new IllegalArgumentException("Undefined reference: references not defined in QLS stylesheet: " + undefinedQLSQuestions);
        }
    }

    private List<String> calculateReferencesDifference(List<String> list1, List<String> list2){
        List<String> listDifference = list1.stream().filter(
                                            itemList1 -> list2.stream().noneMatch(
                                            itemList2 -> itemList2.equals(itemList1))
                                        ).collect(Collectors.toList());

        return listDifference;
    }

    @Override
    public WidgetType visit(Stylesheet stylesheet) {
        for(Page page : stylesheet.getPages()){
            page.accept(this);
        }
        return null; }

    @Override
    public WidgetType visit(Page page) {
        for(Statement statement : page.getStatements()){
            statement.accept(this);
        }
        return null;
    }

    @Override
    public WidgetType visit(QLSQuestion question) {
        if(question.getWidget() != null){
            WidgetType widgetType = question.getWidget().accept(this);
            EvaluationType questionType = form.getQuestion(question.getIdentifier()).getType();

            if(!this.checkCompatibility(widgetType, questionType)){
                throw new IllegalArgumentException("Invalid assignment: question with type '" + questionType +
                                                    "' not compatible with widget type '" + widgetType +"'.");
            }
        }

        return null;
    }

    @Override
    public WidgetType visit(Default defaultSection) {
        if(defaultSection.getWidget() != null){
            WidgetType widgetType = defaultSection.getWidget().accept(this);
            EvaluationType defaultType =  defaultSection.getType();

            if(!this.checkCompatibility(widgetType, defaultType)){
                throw new IllegalArgumentException("Invalid assignment: default style with type '" + defaultType +
                                                    "' not compatible with widget type '" + widgetType +"'.");
            }
        }
        return null;
    }

    @Override
    public WidgetType visit(Widget widget) {
        return widget.getType();
    }

    @Override
    public WidgetType visit(Style style) { return null; }
}
