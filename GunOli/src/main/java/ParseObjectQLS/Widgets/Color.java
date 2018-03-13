package ParseObjectQLS.Widgets;

public class Color extends Widget {
    private String HEX;

    public void color(String color){
        setHEX(color);
    }

    public void setHEX(String HEX) {
        this.HEX = HEX;
    }
}
