package ql.ast.type;

import java.time.LocalDate;

public class Date extends Type {

    private String name = "";
    private LocalDate localDate = LocalDate.now();
    
    @Override
    public String toString() {
        return "date";
    }
    
    @Override
    public boolean isDate() {
        return true;
    }
    
    @Override
    public void setValue(String value) {
        this.localDate = LocalDate.parse(value);
    }
    
    public void setDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    public LocalDate getDate() {
        return localDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
