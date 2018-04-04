package QLS.parsing.checkers;

import QL.classes.Question;
import QL.classes.values.Value;
import QLS.parsing.checkers.errors.WrongWidgetError;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.HashMap;

public class WidgetChecker extends QLSBaseVisitor {
    private HashMap<String, Question> questionMap;
    private String currentQuestionID;

    WidgetChecker(HashMap<String, Question> questionMap) {
        this.questionMap = questionMap;
    }

    void checkStyleSheet(QLSParser.StylesheetContext stylesheet) {
        visitStylesheet(stylesheet);
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        this.currentQuestionID = ctx.IDENTIFIER().getText();
        QLSParser.WidgetContext widget = ctx.widget();

        if (widget != null) {
            visitWidget(widget);
        }

        return null;
    }

    @Override
    public Object visitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx) {
        String type = questionMap.get(currentQuestionID).getValue().getType();

        if (!type.equals(Value.BOOLEAN)) {
            throw new WrongWidgetError(ctx.CHECKBOX().getText(), currentQuestionID);
        }

        return null;
    }

    @Override
    public Object visitTextWidget(QLSParser.TextWidgetContext ctx) {
        String type = questionMap.get(currentQuestionID).getValue().getType();

        if (!type.equals(Value.STRING)) {
            throw new WrongWidgetError(ctx.TEXT().getText(), currentQuestionID);
        }

        return null;
    }

    @Override
    public Object visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        String type = questionMap.get(currentQuestionID).getValue().getType();

        if (!type.equals(Value.BOOLEAN)) {
            throw new WrongWidgetError(ctx.RADIO().getText(), currentQuestionID);
        }

        return null;
    }

    @Override
    public Object visitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx) {
        //TODO: implement spinbox checker
        return null;
    }

    @Override
    public Object visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        //TODO: implement slider checker
        return null;
    }

    @Override
    public Object visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        //TODO: implement dropdown checker
        return null;
    }
}
