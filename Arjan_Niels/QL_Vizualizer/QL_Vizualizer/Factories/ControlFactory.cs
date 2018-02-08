using QL_Vizualizer.Widgets;
using System;
using System.Windows.Forms;

namespace QL_Vizualizer.Factories
{
    public static class ControlFactory
    {
        public static Control CreateControl(QLWidget widget)
        {
            Control result = null;
            switch(widget)
            {
                case QLWidgetInt w:

                    break;
            }

            if (result == null)
                throw new InvalidOperationException(string.Format("Control Factory cannot create control for type {0}", widget.GetType()));
            return result;
        }
    }
}
