package ql.gui.fields.document.filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import ql.ast.type.Type;

public abstract class Filter extends DocumentFilter {

    private Type type;
    private String regex;
    
    public Filter(Type type, String regex) {
        this.type = type;
        this.regex = regex;
    }
    
    @Override
    public void insertString(FilterBypass filterBypass, int offset, 
            String string, AttributeSet attribute) 
                    throws BadLocationException {
        Document document = filterBypass.getDocument();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(document.getText(0, document.getLength()));
        stringBuilder.insert(offset, string);
        
        if(test(stringBuilder.toString())) {
            super.insertString(filterBypass, offset, string, attribute);
//TODO            type.setValue(stringBuilder.toString());
        }
    }
    
    private boolean test(String text) {
        if (text.matches(regex)) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    @Override
    public void replace(FilterBypass filterBypass, int offset, 
            int length, String text, AttributeSet attribute) 
                    throws BadLocationException {
        Document document = filterBypass.getDocument();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(document.getText(0, document.getLength()));
        stringBuilder.replace(offset, offset + length, text);
        
        if (test(stringBuilder.toString())) {
            super.replace(filterBypass, offset, length, text, attribute);
//TODO            type.setValue(stringBuilder.toString());
        }
    }
    
    @Override
    public void remove(FilterBypass filterBypass, int offset, 
            int length)
                    throws BadLocationException {
        Document document = filterBypass.getDocument();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(document.getText(0, document.getLength()));
        stringBuilder.delete(offset, offset + length);
        
        if(test(stringBuilder.toString())) {
            super.remove(filterBypass, offset, length);
//TODO            type.setValue(stringBuilder.toString());
        }
    }
}
