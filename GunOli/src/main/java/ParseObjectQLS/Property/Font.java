package ParseObjectQLS.Property;

public class Font {
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
