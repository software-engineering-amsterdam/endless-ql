package org.uva.qls.ast.Widget.WidgetTypes;


import java.util.Arrays;
import java.util.List;

public class TextType extends WidgetType {

    public TextType(){

    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("StringType", "IntegerType");
    }

}
