package QLS.QLSVisitor;

import QLS.ParseObjectQLS.StyleAttribute.Color;
import QLS.ParseObjectQLS.StyleAttribute.Font;
import QLS.ParseObjectQLS.StyleAttribute.FontSize;
import QLS.ParseObjectQLS.StyleAttribute.Width;
import QLS.ParseObjectQLS.Widgets.*;
import QLS.ParseObjectQLS.StyleAttribute.*;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;
import org.antlr.v4.runtime.tree.TerminalNode;


import java.util.ArrayList;

public class WidgetVisitor extends QLSBaseVisitor {

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx){
        ArrayList<String> radioQuestions = new ArrayList<>();
        int line = ctx.getStart().getLine();
        for(TerminalNode questionStringCtx : ctx.STRING()){
            String questionString = questionStringCtx.getText();
            radioQuestions.add(questionString);
        }

        return new Radio(radioQuestions, line);
    }

    @Override
    public Widget visitCheckWidget(QLSParser.CheckWidgetContext ctx){

        return new CheckBox(ctx.getStart().getLine());
    }

    @Override
    public Widget visitSpinWidget(QLSParser.SpinWidgetContext ctx){
        return new SpinBox(ctx.getStart().getLine());
    }

    @Override
    public Style visitWidthWidget(QLSParser.WidthWidgetContext ctx){
        return new Width(Integer.parseInt(ctx.INTEGER().getText()), ctx.getStart().getLine());
    }

    @Override
    public Style visitFontWidget(QLSParser.FontWidgetContext ctx){
        return new Font(ctx.STRING().getText(), ctx.getStart().getLine());
    }

    @Override
    public Style visitFontSizeWidget(QLSParser.FontSizeWidgetContext ctx){
        return new FontSize(Integer.parseInt(ctx.INTEGER().getText()), ctx.getStart().getLine());
    }

    @Override
    public Style visitColorWidget(QLSParser.ColorWidgetContext ctx){
        return new Color(ctx.HEXVALUE().getText(), ctx.getStart().getLine());
    }
}
