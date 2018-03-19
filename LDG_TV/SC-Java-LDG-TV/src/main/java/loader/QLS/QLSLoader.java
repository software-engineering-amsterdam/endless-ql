package loader.QLS;

import antlr.qls.StylesheetBaseListener;
import antlr.qls.StylesheetParser;
import domain.FormNode;
import domain.model.QuestionASTNode;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
import domain.model.stylesheet.Stylesheet;

public class QLSLoader extends StylesheetBaseListener {

    private Stylesheet styleSheet;
    private Page p;
    private Section s;
    private FormNode formNode;
    public QLSLoader(FormNode formNode) {
        styleSheet = new Stylesheet();
        this.formNode = formNode;
    }
    @Override
    public void enterStylesheetBuilder(StylesheetParser.StylesheetBuilderContext ctx){
        styleSheet.setLabel(ctx.identifier().getText());
    }
    @Override
    public void enterStylesheetData(StylesheetParser.StylesheetDataContext ctx) {

    }
    @Override
    public void exitStylesheetData(StylesheetParser.StylesheetDataContext ctx) { }
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
    public void enterIdentifier(StylesheetParser.IdentifierContext ctx) { }
    @Override
    public void exitIdentifier(StylesheetParser.IdentifierContext ctx) { }
    @Override
    public void enterLabel(StylesheetParser.LabelContext ctx) { }
    @Override
    public void exitLabel(StylesheetParser.LabelContext ctx) { }
    @Override
    public void enterValue(StylesheetParser.ValueContext ctx) { }
    @Override
    public void exitValue(StylesheetParser.ValueContext ctx) { }
    @Override
    public void enterQuestionStructure(StylesheetParser.QuestionStructureContext ctx) {
        QuestionASTNode qan = formNode.getQuestionByVariableIdentifier(ctx.identifier().getText());
        s.addQuestion(qan);
    }
    @Override
    public void exitQuestionStructure(StylesheetParser.QuestionStructureContext ctx) { }
    @Override
    public void enterUiElement(StylesheetParser.UiElementContext ctx) { }
    @Override
    public void exitUiElement(StylesheetParser.UiElementContext ctx) { }
    @Override
    public void enterUiIdentifier(StylesheetParser.UiIdentifierContext ctx) { }
    @Override
    public void exitUiIdentifier(StylesheetParser.UiIdentifierContext ctx) { }
}