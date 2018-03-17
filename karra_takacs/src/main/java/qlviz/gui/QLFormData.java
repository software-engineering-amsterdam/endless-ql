package qlviz.gui;

import qlviz.gui.renderer.javafx.JavafxFormRenderer;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.model.Form;

public class QLFormData {
	private JavafxFormRenderer renderer;
	private Form model;
	private FormViewModel viewModel;
	private boolean containsDuplicates;

	public QLFormData(boolean containsDuplicates) {
		this.containsDuplicates = containsDuplicates;
	}

	public JavafxFormRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(JavafxFormRenderer renderer) {
		this.renderer = renderer;
	}

	public Form getModel() {
		return model;
	}

	public void setModel(Form model) {
		this.model = model;
	}

	public FormViewModel getViewModel() {
		return viewModel;
	}

	public void setViewModel(FormViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public boolean isContainsDuplicates() {
		return containsDuplicates;
	}

	public void setContainsDuplicates(boolean containsDuplicates) {
		this.containsDuplicates = containsDuplicates;
	}
}