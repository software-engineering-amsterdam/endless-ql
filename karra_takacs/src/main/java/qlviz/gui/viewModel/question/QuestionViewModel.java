package qlviz.gui.viewModel.question;


public interface QuestionViewModel {
    void accept(QuestionViewModelVisitor visitor);
    String getText();
}


