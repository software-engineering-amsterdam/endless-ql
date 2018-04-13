package GUI.Styles;


import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import javafx.scene.Node;
import javafx.scene.text.Font;


public class CheckBoxWidget extends javafx.scene.control.CheckBox implements StyleInterface {


    public Expression getExpression(){
        return new BooleanConstant(this.isSelected(),  0);
    }

    @Override
    public Node node(){
        return this;
    };

    @Override
    public void setColor(String color) {

        this.setStyle("-fx-text-inner-color:" + color + ";" );

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int size) {

    }

    @Override
    public void setWidth(int width) {

    }
}
