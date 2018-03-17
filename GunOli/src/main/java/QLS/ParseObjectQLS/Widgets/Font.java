package QLS.ParseObjectQLS.Widgets;

public class Font extends Widget {
    private String font;

    public Font(String fontToUse){


        setFont(fontToUse);
    }

    public void setFont(String font) {
        this.font = font;
    }
}
