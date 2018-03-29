package QLS.QLSVisitor;

import QLS.ParseObjectQLS.Widgets.Widget;

import java.util.HashMap;
import java.util.Map;

public class WidgetTable {
    private Map<String, Widget> widgetTable;

    public WidgetTable(){
        this.widgetTable = new HashMap<>();
    }

    public void addWidget(String id, Widget widget){
        this.widgetTable.put(id, widget);
    }

    public Widget getWidget(String id){
        return this.widgetTable.get(id);
    }

    public void updateWidgetTable(String id, Widget widget){
        this.widgetTable.replace(id, widget);
    }

    //for Debugging
    public boolean existsIn(String id){
        return this.widgetTable.containsKey(id);
    }


}
