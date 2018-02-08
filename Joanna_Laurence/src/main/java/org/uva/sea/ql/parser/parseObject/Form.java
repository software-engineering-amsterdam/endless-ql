package org.uva.sea.ql.parser.parseObject;


public class Form {

    private String name;

    public Form(String name, Statement statement) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
