using System.Collections.Generic;

namespace Assignment1.Model.QLS
{
    public abstract class Widget
    {
    }

    public class CheckBoxWidget : Widget { }

    public class RadioWidget : Widget
    {
        public readonly string Yes, No;
        public RadioWidget(string yes, string no)
        {
            Yes = yes;
            No = no;
        }
    }

    public class SliderWidget : Widget { }
    public class SpinBoxWidget : Widget { }
    public class TextBoxWidget : Widget { }

    public class DropDownWidget : Widget
    {
        public readonly string Yes, No;
        public DropDownWidget(string yes, string no)
        {
            Yes = yes;
            No = no;
        }
    }
}
