namespace Assignment1.Model.QLS
{
    public abstract class Widget
    {
    }

    public class CheckBoxWidget : Widget { }

    public class RadioWidget : Widget
    {
        public RadioWidget(string[] labels)
        {

        }
    }

    public class SliderWidget : Widget { }
    public class SpinBoxWidget : Widget { }
    public class TextBoxWidget : Widget { }
    public class DropDownWidget : Widget { }
}
