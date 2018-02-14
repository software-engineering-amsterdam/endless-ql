using QL_Vizualizer.Controllers;
using QL_Vizualizer.Widgets;
using System;
using System.Windows.Forms;

namespace QL_Vizualizer.Factories
{
    public class ControlFactory : IElementFactory<Control>
    {
        public Control CreateElement(QLWidget widget)
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

            result.Height = result.Controls[0].Height + result.Controls[1].Height;

            if (result == null)
                throw new InvalidOperationException(string.Format("Control Factory cannot create control for type {0}", widget.GetType()));
            return result;
        }

        public Control UpdateElement(QLWidget widget, Control control)
        {
            switch (widget)
            {
                case QLWidgetInt intWidget:
                    UpdateIntWidget(intWidget, control);
                    break;
            }

            return control;
        }

        private void UpdateIntWidget(QLWidgetInt intWidget, Control control)
        {
            foreach (Control b in control.Controls)
                if (b.GetType() == typeof(TextBox))
                    b.Text = intWidget.AnswerValue.ToString();
        }

        private void ChangedIntWidget(QLWidgetInt intWidget, TextBox input)
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
