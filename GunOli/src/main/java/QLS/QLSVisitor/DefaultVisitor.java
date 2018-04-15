package QLS.QLSVisitor;

import QLS.AST.Statements.Default;
import QLS.AST.StyleAttribute.Style;
import QLS.AST.Widgets.Widget;
import QL.Analysis.EvaluationType;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class DefaultVisitor extends QLSBaseVisitor<Default> {

    @Override
    public Default visitDefaultSec(QLSParser.DefaultSecContext ctx){

        ArrayList<Style> styles = new ArrayList<>();
        WidgetVisitor widgetvisitor = new WidgetVisitor();
        StyleVisitor styleVisitor = new StyleVisitor();

        QLSParser.TypeContext typeCTX = ctx.type();

        //Format text of type to match EvaluationType declarations
        String typeText = typeCTX.getText();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);

        EvaluationType typeValue = EvaluationType.valueOf(typeText);

        Widget widget = null;
        if(ctx.widget() != null){
            widget = (Widget) widgetvisitor.visit(ctx.widget());
        }

        for(QLSParser.StyleContext styleContext : ctx.style()){
            Style style = (Style) styleVisitor.visit(styleContext);
            styles.add(style);
        }

        return new Default(typeValue, widget, styles, ctx.getStart().getLine());

    }
}
