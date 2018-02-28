package qlviz.gui.viewModel;

import qlviz.gui.viewModel.propertyEvents.NotifyPropertyChanged;
import qlviz.gui.viewModel.propertyEvents.PropertyChangedListener;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConditionalBlockViewModelImpl implements ConditionalBlockViewModel,
        PropertyChangedListener<QuestionViewModel>
{

    private final BooleanExpression condition;
    private final List<QuestionBlockViewModel> questionBlocks;
    private final List<PropertyChangedListener<QuestionViewModel>> questionChangeListeners = new ArrayList<>();

    public ConditionalBlockViewModelImpl(
            ConditionalBlock conditionalBlock,
            Function<QuestionBlock, QuestionBlockViewModel> questionBlockQuestionBlockViewModelFactory) {
        this.condition = conditionalBlock.getCondition();
        this.questionBlocks =
                conditionalBlock.getQuestionBlocks()
                        .stream()
                        .map(questionBlockQuestionBlockViewModelFactory)
                        .collect(Collectors.toList());
        for (QuestionBlockViewModel block : this.questionBlocks) {
           block.subscribeToPropertyChanged(this);
        }
    }

    @Override
    public BooleanExpression getCondition() {
        return this.condition;
    }

    @Override
    public List<QuestionBlockViewModel> getQuestionBlocks() {
        return this.questionBlocks;
    }

    @Override
    public void notifyValueChanged(QuestionViewModel source) {
        this.questionChangeListeners.stream().forEach(listener -> listener.notifyValueChanged(source));
    }

    @Override
    public void subscribeToPropertyChanged(PropertyChangedListener<QuestionViewModel> observer) {
        this.questionChangeListeners.add(observer);
    }
}
