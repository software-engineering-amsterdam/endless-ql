package QLS.ParseObjectQLS.Widgets;

public class Color extends Widget {
    private String HEX;

    public Color(String color){
        setHEX(color);
    }

    public void setHEX(String HEX) {
        this.HEX = HEX;
    }
}
