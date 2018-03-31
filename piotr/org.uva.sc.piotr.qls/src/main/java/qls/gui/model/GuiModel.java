package qls.gui.model;

import ql.ast.model.Form;
import qls.ast.model.Stylesheet;

public class GuiModel {
    private String qlFilePath;
    private String qlsFilePath;
    private Form form;
    private Stylesheet stylesheet;

    public GuiModel() {
    }

    public String getQlFilePath() {
        return qlFilePath;
    }

    public void setQlFilePath(String qlFilePath) {
        this.qlFilePath = qlFilePath;
    }

    public String getQlsFilePath() {
        return qlsFilePath;
    }

    public void setQlsFilePath(String qlsFilePath) {
        this.qlsFilePath = qlsFilePath;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Stylesheet getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
    }
}
