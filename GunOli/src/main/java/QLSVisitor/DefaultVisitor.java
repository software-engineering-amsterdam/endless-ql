package QLSVisitor;

import ParseObjectQLS.Default;
import ParseObjectQLS.Widgets.Widget;
import ParseObjectsQL.Expressions.EvaluationType;
import QLSAntlrGen.QLSBaseVisitor;
import QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class DefaultVisitor extends QLSBaseVisitor<Default> {

    @Override
    public Default visitDefaultSec(QLSParser.DefaultSecContext ctx){

        ArrayList<Widget> widgets = new ArrayList<>();
        WidgetVisitor widgetvisitor = new WidgetVisitor();
        QLSParser.TypeContext typeCTX = ctx.type();
        String typeText = typeCTX.getText();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
        //Format text of type to match EvaluationType declarations
        EvaluationType typeValue = EvaluationType.valueOf(typeText);


        for(QLSParser.WidgetContext widgetContext : ctx.widget()){
            Widget widget = (Widget) widgetvisitor.visit(widgetContext);
            widgets.add(widget);
        }

        return new Default(typeValue, widgets);

    }
}
