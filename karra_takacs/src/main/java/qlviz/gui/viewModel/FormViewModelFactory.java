package qlviz.gui.viewModel;

import qlviz.model.Form;

public interface FormViewModelFactory {
    FormViewModel create(Form form);
}
