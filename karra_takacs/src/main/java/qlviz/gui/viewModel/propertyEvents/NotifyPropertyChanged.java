package qlviz.gui.viewModel.propertyEvents;

public interface NotifyPropertyChanged<T> {
    void subscribeToPropertyChanged(PropertyChangedListener<T> observer);
}
