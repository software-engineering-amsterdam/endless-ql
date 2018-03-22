package qlviz.gui.viewModel;

import qlviz.gui.renderer.FormRenderer;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;
import qlviz.typecheker.QuestionCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FormViewModelImpl implements FormViewModel {
    private final Form model;
    private final List<QuestionViewModel> questionViewModels;

    public FormViewModelImpl(Form model,
                             Function<Question, QuestionViewModel> viewModelFactory) {
        this.model = model;
        this.questionViewModels = QuestionCollector.collect(model)
                                                    .stream()
                                                    .map(viewModelFactory)
                                                    .collect(Collectors.toList());
    }

    @Override
    public String getTitle() {
        return model.getTitle();
    }

    public List<QuestionViewModel> getQuestions() {
        return questionViewModels;
    }
}
