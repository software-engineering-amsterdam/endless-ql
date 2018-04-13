package GUI.Styles;

import QLS.QLSVisitor.StylesheetVisitor;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class RadioWidget extends HBox implements StyleInterface {

    private final javafx.scene.control.RadioButton leftLabel;
    private final javafx.scene.control.RadioButton rightLabel;


    public RadioWidget(ArrayList<String> values){

        this.leftLabel = new javafx.scene.control.RadioButton(values.get(0));
        this.rightLabel = new javafx.scene.control.RadioButton(values.get(1));

    }


    @Override
    public Node node() {
        return this;
    }

    @Override
    public void setColor(String color) {
        this.leftLabel.setTextFill(Color.valueOf(color));
        this.rightLabel.setTextFill(Color.valueOf(color));
    }

    @Override
    public void setFont(String font) {
        this.leftLabel.setFont(Font.font(font));
        this.rightLabel.setFont(Font.font(font));
    }

    @Override
    public void setFontSize(int size) {
        Font currentFont = this.leftLabel.getFont();
        this.rightLabel.setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, size));
        this.leftLabel.setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, size));
    }

    @Override
    public void setWidth(int width) {
        this.rightLabel.setPrefWidth(width);
        this.leftLabel.setPrefWidth(width);
    }
}
