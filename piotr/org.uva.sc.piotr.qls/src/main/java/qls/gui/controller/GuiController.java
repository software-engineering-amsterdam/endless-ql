package qls.gui.controller;

import ql.ast.model.Form;
import ql.gui.controller.BuildFormController;
import ql.gui.controller.FormController;
import ql.gui.model.FormModel;
import ql.gui.view.WindowView;
import qls.ast.model.Stylesheet;
import qls.gui.model.GuiModel;
import qls.gui.model.Paginator;
import qls.gui.view.InitialPanel;
import qls.gui.view.paginator.PaginatorView;
import qls.logic.builders.GuiBuilder;

import javax.swing.*;
import java.awt.*;

public class GuiController {

    private GuiModel guiModel;
    private WindowView windowView;

    public GuiController() {
        this.guiModel = new GuiModel();
        this.windowView = new WindowView();
        JPanel mainPanel = new InitialPanel(this);
        windowView.setMainPanel(mainPanel);
        windowView.formatAndShow();
    }

    public void setQlFilePath(String path) {
        this.guiModel.setQlFilePath(path);

        Form form = BuildFormController.buildForm(this.guiModel.getQlFilePath(), windowView);
        if (form != null)
            this.guiModel.setForm(form);

        this.proceed();
    }

    public void setQlsFilePath(String path) {
        this.guiModel.setQlsFilePath(path);

        Stylesheet stylesheet = BuildStylesheetController.buildStylesheet(this.guiModel.getQlsFilePath(), windowView);
        if (stylesheet != null)
            this.guiModel.setStylesheet(stylesheet);

        this.proceed();
    }

    private void proceed() {
        if (this.guiModel.getForm() != null && this.guiModel.getStylesheet() != null) {

            GuiBuilder guiBuilder = new GuiBuilder(
                    this.guiModel.getForm(),
                    this.guiModel.getStylesheet()
            );

            FormModel formModel = new FormModel(guiBuilder.getQuestionModels());
            FormController formController = new FormController(formModel);
            formModel.registerController(formController);

            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = GridBagConstraints.WEST;

            Paginator paginator = new Paginator(guiBuilder.getPages());

            JPanel pagePanel = new JPanel();
            pagePanel.add(paginator.getCurrentPage());

            JPanel paginatorView = new PaginatorView(paginator, pagePanel);

            gridBagConstraints.gridy = 0;
            mainPanel.add(paginatorView, gridBagConstraints);
            gridBagConstraints.gridy = 1;
            mainPanel.add(pagePanel, gridBagConstraints);

            this.windowView.setMainPanel(mainPanel);
            windowView.formatAndShow();

        }
    }

}
