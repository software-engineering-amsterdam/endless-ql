package QLS.classes.widgets;

public class RadioWidget extends WidgetType {
    private String trueLbl;
    private String falseLbl;

    public RadioWidget(String trueLbl, String falseLbl) {
        super();
        this.trueLbl = trueLbl;
        this.falseLbl = falseLbl;
    }

    public RadioWidget() {
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
