package QLS.QLSVisitor;

import QLS.ParseObjectQLS.Default;
import QLS.ParseObjectQLS.Widgets.Widget;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

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
