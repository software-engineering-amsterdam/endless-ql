package qls.gui.controller;

import ql.ast.model.Form;
import ql.gui.controller.BuildFormController;
import ql.gui.controller.FormController;
import ql.gui.model.FormModel;
import ql.gui.model.ValidatorsHandler;
import ql.gui.view.WindowView;
import ql.logic.validators.Validator;
import qls.ast.model.Stylesheet;
import qls.gui.model.GuiModel;
import qls.gui.model.Paginator;
import qls.gui.model.StylesheetBuilder;
import qls.gui.view.InitialPanel;
import qls.gui.view.PagePanel;
import qls.gui.view.paginator.PaginatorPanel;
import qls.logic.builders.GuiBuilder;
import qls.logic.validators.AllQuestionsCoveredValidator;
import qls.logic.validators.NoEmptyReferencesValidator;
import qls.logic.validators.NoMultipleQuestionDeclarationValidator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GuiController {

    private GuiModel guiModel;
    private WindowView windowView;

    public GuiController() {
        this.guiModel = new GuiModel();
        this.windowView = new WindowView("QLS Form GUI");
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

        Stylesheet stylesheet = StylesheetBuilder.buildStylesheet(this.guiModel.getQlsFilePath(), windowView);
        if (stylesheet != null)
            this.guiModel.setStylesheet(stylesheet);

        this.proceed();
    }

    private void proceed() {
        if (this.guiModel.getForm() != null && this.guiModel.getStylesheet() != null) {

            Form form = this.guiModel.getForm();
            Stylesheet stylesheet = this.guiModel.getStylesheet();

            Validator[] validators = new Validator[]{
                    new AllQuestionsCoveredValidator(form, stylesheet),
                    new NoEmptyReferencesValidator(form, stylesheet),
                    new NoMultipleQuestionDeclarationValidator(stylesheet)
            };

            if (!ValidatorsHandler.execute(windowView, validators))
                return;

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

            List<PagePanel> pagePanels = guiBuilder.getPagePanels();
            PagePanel lastPage = pagePanels.get(pagePanels.size() - 1);
            lastPage.enableSubmitButton(formModel);

            Paginator paginator = new Paginator(pagePanels);

            JPanel pagePanel = new JPanel();
            pagePanel.add(paginator.getCurrentPage());

            JPanel paginatorPanel = new PaginatorPanel(paginator, pagePanel);

            gridBagConstraints.gridy = 0;
            mainPanel.add(pagePanel, gridBagConstraints);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            mainPanel.add(paginatorPanel, gridBagConstraints);

            this.windowView.setMainPanel(mainPanel);
            windowView.formatAndShow();

        }
    }

}
