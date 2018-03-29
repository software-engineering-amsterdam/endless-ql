package qlviz.gui.viewModel;

import com.google.inject.AbstractModule;
import qlviz.gui.viewModel.linker.QuestionViewModelCollector;
import qlviz.gui.viewModel.linker.QuestionViewModelCollectorImpl;
import qlviz.gui.viewModel.linker.QuestionViewModelLinker;
import qlviz.gui.viewModel.linker.QuestionViewModelLinkerImpl;

public class ViewModelLinkerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(QuestionViewModelLinker.class).to(QuestionViewModelLinkerImpl.class);
        bind(QuestionViewModelCollector.class).to(QuestionViewModelCollectorImpl.class);
    }
}
