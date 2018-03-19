package loader.QLS;

import antlr.qls.StylesheetBaseListener;
import antlr.qls.StylesheetParser;
import domain.FormNode;
import domain.model.QuestionASTNode;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
import domain.model.stylesheet.Stylesheet;
import domain.model.stylesheet.UIElement;

import java.util.HashMap;
import java.util.Map;

public class QLSLoader extends StylesheetBaseListener {

    private Stylesheet styleSheet;
    private Page p;
    private Section s;
    private FormNode formNode;
    private QuestionASTNode qan;
    public QLSLoader(FormNode formNode) {
        styleSheet = new Stylesheet();
        this.formNode = formNode;
    }
    @Override
    public void enterStylesheetBuilder(StylesheetParser.StylesheetBuilderContext ctx){
        styleSheet.setLabel(ctx.identifier().getText());
    }
    @Override
    public void enterPageNodeStructure(StylesheetParser.PageNodeStructureContext ctx) {
        p = new Page(ctx.identifier().getText());
        styleSheet.addPage(p);
    }
    @Override
    public void exitPageNodeStructure(StylesheetParser.PageNodeStructureContext ctx) {
        p = null;
    }
    @Override
    public void enterSectionNodeStructure(StylesheetParser.SectionNodeStructureContext ctx) {
        s = new Section(ctx.label().getText());
        p.addSection(s);
    }
    @Override
    public void exitSectionNodeStructure(StylesheetParser.SectionNodeStructureContext ctx) {
        s = null;
    }
    @Override
    public void enterQuestionStructure(StylesheetParser.QuestionStructureContext ctx) {
        qan = formNode.getQuestionByVariableIdentifier(ctx.identifier().getText());
    }
    @Override
    public void exitQuestionStructure(StylesheetParser.QuestionStructureContext ctx) {
        s.addQuestion(qan);
        qan = null;
    }
    @Override
    public void enterUiElement(StylesheetParser.UiElementContext ctx) {
        UIElement uiElement = null;
        Map<String, String> options;
        if (ctx.uiType().checkbox() instanceof StylesheetParser.CheckboxContext){
            options = null;
            uiElement = new UIElement(ctx.uiType().getText(), options);
        }
        if (ctx.uiType().spinbox() instanceof StylesheetParser.SpinboxContext){
            options = null;
            uiElement = new UIElement(ctx.uiType().getText(), options);
        }
        if (ctx.uiType().radio() instanceof StylesheetParser.RadioContext){
            options = new HashMap<>();
            for (int i = 0; i < ctx.uiType().radio().radioOptions().size() ; i++){
                String option = ctx.uiType().radio().radioOptions().get(i).getText();
                options.put("option" + i, option);
            }
            uiElement = new UIElement(ctx.uiType().getText(), options);
        }
        if (ctx.uiType().money() instanceof StylesheetParser.MoneyContext){
            options = new HashMap<>();
            for (StylesheetParser.OptionContext octx : ctx.uiType().money().option()){
                options.put(octx.identifier().getText(), octx.value().getText());
            }
            uiElement = new UIElement(ctx.uiType().getText(), options);
        }
        qan.setUiElement(uiElement);

    }
}