package com.chariotit.uva.sc.qdsl.ast.qls.visitor;

import com.chariotit.uva.sc.qdsl.ast.qls.node.type.BooleanTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.IntegerTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.MoneyTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.*;
import com.chariotit.uva.sc.qdsl.ast.qls.node.property.*;
import com.chariotit.uva.sc.qdsl.ast.qls.node.widget.*;

public abstract class NodeVisitor {

    public abstract void visitColorProperty(ColorProperty colorProperty);

    public abstract void visitFontProperty(FontProperty fontProperty);

    public abstract void visitFontSizeProperty(FontSizeProperty fontSizeProperty);

    public abstract void visitWidgetProperty(WidgetProperty widgetProperty);

    public abstract void visitWidthProperty(WidthProperty widthProperty);

    public abstract void visitStringType(StringTypeNode stringTypeNode);

    public abstract void visitMoneyType(MoneyTypeNode moneyTypeNode);

    public abstract void visitIntegerType(IntegerTypeNode integerTypeNode);

    public abstract void visitBooleanType(BooleanTypeNode booleanTypeNode);

    public abstract void visitCheckboxWidget(CheckboxWidget checkboxWidget);

    public abstract void visitDropdownWidget(DropdownWidget dropdownWidget);

    public abstract void visitRadioWidget(RadioWidget radioWidget);

    public abstract void visitSliderWidget(SliderWidget sliderWidget);

    public abstract void visitSpinboxWidget(SpinboxWidget spinboxWidget);

    public abstract void visitTextWidget(TextWidget textWidget);

    public abstract void visitDefaultProperties(DefaultProperties defaultProperties);

    public abstract void visitPage(Page page);

    public abstract void visitProperties(Properties properties);

    public abstract void visitQuestion(Question question);

    public abstract void visitSection(Section section);

    public abstract void visitStylesheet(Stylesheet stylesheet);
}
