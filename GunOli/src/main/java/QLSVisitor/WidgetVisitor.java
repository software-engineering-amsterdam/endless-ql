package QLSVisitor;

import ParseObjectQLS.Widgets.*;
import QLSAntlrGen.QLSBaseVisitor;
import QLSAntlrGen.QLSParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class WidgetVisitor extends QLSBaseVisitor {

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx){
        ArrayList<String> radioQuestions = new ArrayList<>();

        for(TerminalNode questionStringCtx : ctx.STRING()){
            String questionString = questionStringCtx.getText();
            radioQuestions.add(questionString);
        }

        return new Radio(radioQuestions);
    }

    @Override
    public Widget visitCheckWidget(QLSParser.CheckWidgetContext ctx){
        return new CheckBox();
    }

    @Override
    public Widget visitSpinWidget(QLSParser.SpinWidgetContext ctx){
        return new SpinBox();
    }

    @Override
    public Widget visitWidthWidget(QLSParser.WidthWidgetContext ctx){
        return new Width(Integer.parseInt(ctx.INTEGER().getText()));
    }

    @Override
    public Widget visitFontWidget(QLSParser.FontWidgetContext ctx){
        return new Font(ctx.STRING().getText());
    }

    @Override
    public Widget visitFontSizeWidget(QLSParser.FontSizeWidgetContext ctx){
        return new FontSize(Integer.parseInt(ctx.INTEGER().getText()));
    }

    @Override
    public Widget visitColorWidget(QLSParser.ColorWidgetContext ctx){
        return new Color(ctx.HEXVALUE().getText());
    }
}
