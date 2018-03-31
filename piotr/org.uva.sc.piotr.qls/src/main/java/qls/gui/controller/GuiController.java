package qls.gui.controller;

import ql.ast.model.Form;
import ql.gui.controller.BuildFormController;
import ql.gui.view.WindowView;
import qls.ast.model.Stylesheet;
import qls.gui.model.GuiModel;
import qls.gui.view.MainPanel;

import javax.swing.*;

public class GuiController {

    private GuiModel guiModel;
    private WindowView windowView;

    public GuiController() {
        this.guiModel = new GuiModel();
        this.windowView = new WindowView();
        JPanel mainPanel = new MainPanel(this);
        windowView.setMainPanel(mainPanel);
        windowView.formatAndShow();
    }

    private void tryToProceed() {
        if (this.guiModel.getForm() != null && this.guiModel.getStylesheet() != null) {

            // build Form with Stylesheet model

        }
    }

    public void setQlFilePath(String path) {
        this.guiModel.setQlFilePath(path);

        Form form = BuildFormController.buildForm(this.guiModel.getQlFilePath(), windowView);
        if (form != null)
            this.guiModel.setForm(form);

        this.tryToProceed();
    }

    public void setQlsFilePath(String path) {
        this.guiModel.setQlsFilePath(path);

        Stylesheet stylesheet = BuildStylesheetController.buildStylesheet(this.guiModel.getQlsFilePath(), windowView);
        if (stylesheet != null)
            this.guiModel.setStylesheet(stylesheet);

        this.tryToProceed();
    }


}
