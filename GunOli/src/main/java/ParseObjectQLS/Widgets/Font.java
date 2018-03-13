package ParseObjectQLS.Widgets;

public class Font extends Widget {
    private String font;

    public Font(String fontToUse){

        getFont();
        setFont(fontToUse);
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
}
