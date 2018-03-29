package com.chariotit.uva.sc.qdsl.ast.qls.visitor;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;
import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.qls.node.*;
import com.chariotit.uva.sc.qdsl.ast.qls.node.property.*;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.BooleanTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.IntegerTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.MoneyTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.node.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeCheckVisitor extends NodeVisitor {

    private SymbolTable symbolTable;
    private List<TypeCheckError> errors = new ArrayList<>();
    private List<String> questions = new ArrayList<>();

    public TypeCheckVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public void visitColorProperty(ColorProperty colorProperty) {

    }

    @Override
    public void visitFontProperty(FontProperty fontProperty) {

    }

    @Override
    public void visitFontSizeProperty(FontSizeProperty fontSizeProperty) {

    }

    @Override
    public void visitWidgetProperty(WidgetProperty widgetProperty) {

    }

    @Override
    public void visitWidthProperty(WidthProperty widthProperty) {

    }

    @Override
    public void visitStringType(StringTypeNode stringTypeNode) {

    }

    @Override
    public void visitMoneyType(MoneyTypeNode moneyTypeNode) {

    }

    @Override
    public void visitIntegerType(IntegerTypeNode integerTypeNode) {

    }

    @Override
    public void visitBooleanType(BooleanTypeNode booleanTypeNode) {

    }

    @Override
    public void visitCheckboxWidget(CheckboxWidget checkboxWidget) {

    }

    @Override
    public void visitDropdownWidget(DropdownWidget dropdownWidget) {

    }

    @Override
    public void visitRadioWidget(RadioWidget radioWidget) {

    }

    @Override
    public void visitSliderWidget(SliderWidget sliderWidget) {

    }

    @Override
    public void visitSpinboxWidget(SpinboxWidget spinboxWidget) {

    }

    @Override
    public void visitTextWidget(TextWidget textWidget) {

    }

    @Override
    public void visitDefaultProperties(DefaultProperties defaultProperties) {

    }

    @Override
    public void visitPage(Page page) {

    }

    @Override
    public void visitProperties(Properties properties) {

    }

    @Override
    public void visitQuestion(Question question) {
        if (!symbolTable.hasEntry(question.getLabel())) {
            addError(question, "Label does not exist");
        } else if (questions.contains(question.getLabel())) {
            addError(question, "Question is placed multiple times");
        } else {

            questions.add(question.getLabel());
        }
    }

    @Override
    public void visitSection(Section section) {

    }

    @Override
    public void visitStylesheet(Stylesheet stylesheet) {
        for (Map.Entry<String, SymbolTableEntry> entry : symbolTable.getEntries()) {
            if (!questions.contains(entry.getKey())) {
                addError(stylesheet, String.format("Missing label '%s' in stylesheet", entry
                        .getKey()));
            }
        }
    }

    private void addError(AstNode node, String message) {
        errors.add(new TypeCheckError(message, node.getSourceFilePosition()));
    }

    public List<TypeCheckError> getErrors() {
        return errors;
    }
}
