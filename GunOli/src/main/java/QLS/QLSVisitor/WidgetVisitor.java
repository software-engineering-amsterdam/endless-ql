package QLS.QLSVisitor;

import QLS.AST.Widgets.*;
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

}
