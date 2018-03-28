package qlviz.gui.viewModel;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelFactory;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelFactoryImpl;
import qlviz.gui.viewModel.linker.QuestionViewModelCollector;
import qlviz.gui.viewModel.linker.QuestionViewModelCollectorImpl;
import qlviz.gui.viewModel.linker.QuestionViewModelLinker;
import qlviz.gui.viewModel.linker.QuestionViewModelLinkerImpl;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactory;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactoryImpl;
import qlviz.gui.viewModel.question.QuestionViewModelFactory;
import qlviz.interpreter.CachingConditionCollector;
import qlviz.interpreter.ConditionCollector;
import qlviz.interpreter.ConditionCollectorFactory;

public class ViewModelFactoryModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(NumericExpressionViewModelFactory.class).to(NumericExpressionViewModelFactoryImpl.class);
        bind(BooleanExpressionViewModelFactory.class).to(BooleanExpressionViewModelFactoryImpl.class);
        install(new FactoryModuleBuilder()
                .implement(QuestionViewModelFactory.class, QuestionViewModelFactoryImpl.class)
                .build(QuestionViewModelFactoryCreator.class));
        install(new FactoryModuleBuilder()
                .implement(ConditionCollector.class, CachingConditionCollector.class)
                .build(ConditionCollectorFactory.class));
        install(new FactoryModuleBuilder()
                .implement(FormViewModel.class, FormViewModelImpl.class)
                .build(FormViewModelFactory.class));
    }
}

