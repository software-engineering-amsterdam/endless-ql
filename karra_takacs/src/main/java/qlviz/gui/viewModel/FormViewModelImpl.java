package qlviz.gui.viewModel;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import qlviz.gui.renderer.FormRenderer;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.gui.viewModel.question.QuestionViewModelFactory;
import qlviz.interpreter.ConditionCollector;
import qlviz.interpreter.ConditionCollectorFactory;
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

    @Inject
    public FormViewModelImpl(@Assisted Form model,
                             QuestionViewModelFactoryCreator questionViewModelFactoryCreator,
                             ConditionCollectorFactory conditionCollectorFactory) {
        this.model = model;
        var viewModelFactory = questionViewModelFactoryCreator.create(conditionCollectorFactory.create(model));
        this.questionViewModels = QuestionCollector.collect(model)
                                                    .stream()
                                                    .map(viewModelFactory::create)
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
