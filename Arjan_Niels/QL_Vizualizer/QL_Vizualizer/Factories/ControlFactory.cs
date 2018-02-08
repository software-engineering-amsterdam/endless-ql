using QL_Vizualizer.Controllers;
using QL_Vizualizer.Widgets;
using System;
using System.Linq;
using System.Windows.Forms;

namespace QL_Vizualizer.Factories
{
    public static class ControlFactory
    {
        public static Control CreateControl(QLWidget widget)
        {
            Control result = new Panel();
            result.Controls.Add(new Label { Text = widget.Text });
            Control input = null;
            switch (widget)
            {
                case QLWidgetInt intWidget:
                    input = new TextBox();
                    input.TextChanged += delegate (object sender, EventArgs e) { ChangedIntWidget(intWidget, input as TextBox); };
                    break;
            }

            input.Location = new System.Drawing.Point(0, result.Controls[0].Height);
            result.Controls.Add(input);

            if (result == null)
                throw new InvalidOperationException(string.Format("Control Factory cannot create control for type {0}", widget.GetType()));
            return result;
        }

        public static void UpdateControl(QLWidget widget, Control control)
        {
            switch (widget)
            {
                case QLWidgetInt intWidget:
                    UpdateIntWidget(intWidget, control);
                    break;
            }

        }

        public static void UpdateIntWidget(QLWidgetInt intWidget, Control control)
        {
            foreach (Control b in control.Controls)
                if (b.GetType() == typeof(TextBox))
                {
                    b.Text = intWidget.AnswerValue.ToString();
                    
                }
        }

        public static void ChangedIntWidget(QLWidgetInt intWidget, TextBox input)
        {
            int value = 0;
            if (int.TryParse(input.Text, out value))
            {
                intWidget.SetAnswer(value);
                WidgetController.Instance.ValueUpdate(intWidget.Identifyer);
            }
            else
                input.Text = "";
        }
    }
}
