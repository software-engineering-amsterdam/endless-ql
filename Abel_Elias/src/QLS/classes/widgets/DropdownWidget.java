package QLS.classes.widgets;

public class DropdownWidget extends WidgetType {
    private String trueLbl;
    private String falseLbl;

    public DropdownWidget(String trueLbl, String falseLbl) {
        super();
        this.trueLbl = trueLbl;
        this.falseLbl = falseLbl;
    }

    public DropdownWidget() {
        super();
    }

    public String getTrueLbl() {
        return trueLbl;
    }

    public void setTrueLbl(String trueLbl) {
        this.trueLbl = trueLbl;
    }

    public String getFalseLbl() {
        return falseLbl;
    }

    public void setFalseLbl(String falseLbl) {
        this.falseLbl = falseLbl;
    }
}

