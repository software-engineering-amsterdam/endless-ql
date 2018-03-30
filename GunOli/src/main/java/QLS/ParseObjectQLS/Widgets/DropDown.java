package QLS.ParseObjectQLS.Widgets;

import QLS.Analysis.WidgetVisitorInterface;

public class DropDown extends Widget {

    DropDown(int line) {
        super(WidgetType.Dropdown, line);
    }


    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
