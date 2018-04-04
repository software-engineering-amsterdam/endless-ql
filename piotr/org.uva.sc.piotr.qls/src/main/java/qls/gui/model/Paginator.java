package qls.gui.model;

import qls.gui.view.PagePanel;

import javax.swing.*;
import java.util.List;

public class Paginator {

    private List<PagePanel> pages;
    private Integer currentPageIndex = 0;

    public Paginator(List<PagePanel> pages) {
        this.pages = pages;
    }

    public JComponent getCurrentPage() {
        return this.pages.get(currentPageIndex);
    }

    public boolean hasNext() {
        return this.currentPageIndex < this.pages.size() - 1;
    }

    public void moveToNext() {
        this.currentPageIndex++;
    }

    public boolean hasPrevious() {
        return this.currentPageIndex > 0;
    }

    public void moveToPrevious() {
        this.currentPageIndex--;
    }

    public Integer getCurrentPageNumber() {
        return this.currentPageIndex + 1;
    }

    public Integer getTotalNumberPages() {
        return this.pages.size();
    }
}
