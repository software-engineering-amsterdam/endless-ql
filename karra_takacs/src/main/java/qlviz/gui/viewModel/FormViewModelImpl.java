package qlviz.gui.viewModel;

import qlviz.gui.renderer.FormRenderer;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FormViewModelImpl implements FormViewModel {
    private final Form model;
    private final FormRenderer renderer;
    private final List<QuestionBlockViewModel> questionBlockViewModels;

    public FormViewModelImpl(Form model, FormRenderer renderer, Function<QuestionBlock, QuestionBlockViewModel> viewModelFactory) {
        this.model = model;
        this.renderer = renderer;
        this.questionBlockViewModels = new ArrayList<>();

        for (QuestionBlock block : model.getQuestions()) {
            this.questionBlockViewModels.add(viewModelFactory.apply(block));
        }
    }

    @Override
    public void notifyValueChanged(QuestionViewModel source) {
        renderer.render(this);
    }

    @Override
    public String getTitle() {
        return model.getTitle();
    }

    @Override
    public List<QuestionBlockViewModel> getQuestions() {
        return this.questionBlockViewModels;
    }
}
