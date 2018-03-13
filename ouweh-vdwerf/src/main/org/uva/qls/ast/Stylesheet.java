package org.uva.qls.ast;

import java.util.List;

public class Stylesheet extends TreeNode{

    private String id;
    private List<Page> pages;

    public Stylesheet(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }
}
