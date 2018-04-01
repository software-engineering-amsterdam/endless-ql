package gui.widgets;

import QL.classes.values.BooleanValue;
import QL.classes.values.NumericValue;
import gui.listeners.BoolActionListener;
import gui.listeners.SliderChangeListener;

import javax.swing.*;

public class SliderWidget implements Widget {
    private JSlider slider;
    private JPanel sliderBox;
    private JLabel label;
    private NumericValue value;

    public SliderWidget(NumericValue value, int min, int max){
        this.value = value;

        label = new JLabel();
        slider = new JSlider(JSlider.HORIZONTAL, min, max, min);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);

        slider.addChangeListener(new SliderChangeListener(value, slider, label));

        sliderBox = new JPanel();
        sliderBox.add(slider);
        sliderBox.add(label);

        refresh();
    }

    @Override
    public JPanel getJComponent() {
        return sliderBox;
    }

    @Override
    public void refresh() {
        //auto-refresh
    }
}
