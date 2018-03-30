package GUI.Styles;

public class CheckBox implements StyleInterface {
    @Override
    public void Color(String color) {
        System.out.println(color);
    }

    @Override
    public void Font(String font) {
        System.out.println(font);
    }

    @Override
    public void FontSize(int size) {
        System.out.println(size);
    }

    @Override
    public void width(int width) {
        System.out.println(width);
    }
}
