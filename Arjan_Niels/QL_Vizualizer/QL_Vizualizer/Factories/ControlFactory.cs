using QL_Vizualizer.Controllers;
using QL_Vizualizer.Widgets;
using System;
using System.Windows.Forms;

namespace QL_Vizualizer.Factories
{
    public class ControlFactory : IElementFactory<Control>
    {
        /// <summary>
        /// Creates a windowsform control
        /// </summary>
        /// <param name="widget">Widget to create control from</param>
        /// <returns>Windows forms control</returns>
        public Control CreateElement(QLWidget widget)
        {
            // Create main body of control
            Control result = new Panel();
            result.Controls.Add(new Label { Text = widget.Text });

            Control input = null;
            switch (widget)
            {
                case QLWidgetInt intWidget:
                    input = CreateIntWidget(intWidget);
                    break;
            }

            // Check if input is actually created
            if (input == null)
                throw new InvalidOperationException(string.Format("Control Factory cannot create control for type {0}", widget.GetType()));

            // Correct location of inner input field
            input.Location = new System.Drawing.Point(0, result.Controls[0].Height);

            // Add control to main control
            result.Controls.Add(input);

            // Resize main control
            result.Height = result.Controls[0].Height + result.Controls[1].Height;

            return result;
        }

        /// <summary>
        /// Updates a windowsforms control
        /// </summary>
        /// <param name="widget">Widget associated to the control</param>
        /// <param name="control">Control to be updated</param>
        /// <returns></returns>
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

        #region Updaters

        private void UpdateIntWidget(QLWidgetInt widget, Control control)
        {
            foreach (Control b in control.Controls)
                if (b.GetType() == typeof(TextBox))
                    b.Text = widget.AnswerValue.ToString();
        }
        #endregion

        #region Creators
        private Control CreateIntWidget(QLWidgetInt widget)
        {
            // Create textbox for integerss
            TextBox result = new TextBox();
            result.TextChanged += delegate (object sender, EventArgs e) { ChangedIntWidget(widget, result); };
            if (!widget.Active)
                result.Enabled = false;
            return result;
        }

        #endregion

        #region Windows Change Events
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
        #endregion
    }
}
