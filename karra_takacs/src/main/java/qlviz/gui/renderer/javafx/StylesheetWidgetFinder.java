package qlviz.gui.renderer.javafx;

import qlviz.gui.renderer.layout.QuestionLocation;
import qlviz.gui.renderer.layout.QuestionLocator;
import qlviz.gui.renderer.layout.QuestionNotFoundException;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.Scope;
import qlviz.model.style.Widget;

import java.util.List;
import java.util.Stack;


public class StylesheetWidgetFinder implements WidgetFinder {

    private final QuestionLocator questionLocator;

    public StylesheetWidgetFinder(QuestionLocator questionLocator) {
        this.questionLocator = questionLocator;
    }

    @Override
    public Widget findWidgetForQuestion(QuestionViewModel questionViewModel) throws QuestionNotFoundException, WidgetNotFoundException {
        QuestionLocation questionLocation = this.questionLocator.getPathToQuestion(questionViewModel);
        if (questionLocation.getQuestion().getWidget() != null) {
            return questionLocation.getQuestion().getWidget();
        }
        else
        {
            Stack<Scope> scopes = questionLocation.getScopes();
            while (!scopes.empty()) {
                Scope scope = scopes.pop();
                for (DefaultWidgetDeclaration declaration : scope.getDefaultWidgetDeclarations()) {
                    if (declaration.getQuestionType() == questionViewModel.getQuestionType()) {
                        return declaration.getWidget();
                    }
                }
            }
        }
        throw new WidgetNotFoundException();
    }

}
