using QL_Vizualizer.Controllers;
using QL_Vizualizer.Style;
using QL_Vizualizer.Widgets;
using QL_Vizualizer.Widgets.Types;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace QL_Vizualizer.Factories
{
    public class ControlFactory : ElementFactory<Control, WindowsStyleProperties>
    {
        public ControlFactory(WidgetController widgetController) : base(widgetController) { }

        /// <summary>
        /// Creates a windowsform control
        /// </summary>
        /// <param name="widget">Widget to create control from</param>
        /// <returns>Windows forms control</returns>
        public override Control CreateElement(QLWidget widget, WindowsStyleProperties style)
        {
            // Create main control with style
            Control result = CreateStyledBase(style);

            switch (widget)
            {
                case QLWidgetInt intWidget:
                    CreateIntWidget(intWidget, style, ref result);
                    break;
                case QLWidgetBool boolWidget:
                    CreateBoolWidget(boolWidget, style, ref result);
                    break;
                case QLWidgetString stringWidget:
                    CreateStringWidget(stringWidget, style, ref result);
                    break;
            }

            // Resize main control
            result.Height = result.Controls[0].Height + result.Controls[0].Height;
            return result;
        }

        /// <summary>
        /// Updates a windowsforms control
        /// </summary>
        /// <param name="widget">Widget associated to the control</param>
        /// <param name="control">Control to be updated</param>
        /// <returns></returns>
        public override Control UpdateElement(QLWidget widget, Control control)
        {
            switch (widget)
            {
                case QLWidgetInt intWidget:
                    UpdateIntWidget(intWidget, control);
                    break;
                case QLWidgetBool boolWidget:
                    UpdateBoolWidget(boolWidget, control);
                    break;
                case QLWidgetString stringWidget:
                    UpdateStringWidget(stringWidget, control);
                    break;
            }

            return control;
        }

        #region Styling

        /// <summary>
        /// Create a panel using the provided style
        /// </summary>
        /// <param name="style">Style for the panel</param>
        /// <returns>Panel with applied style</returns>
        private Control CreateStyledBase(WindowsStyleProperties style)
        {
            // Create main body of control
            Control result = new Panel();

            // Assign style elements
            result.Width = style.Width;
            result.Location = new Point(0, style.YPosition);

            return result;
        }

        /// <summary>
        /// Apply the style to a control
        /// </summary>
        /// <param name="control">Control to style</param>
        /// <param name="style">Style to use</param>
        /// <returns>Control</returns>
        private Control ApplyControlStyle(Control control, WindowsStyleProperties style)
        {
            control.Width = style.Width;
            return control;
        }
        #endregion

        #region Updaters

        private void UpdateIntWidget(QLWidgetInt widget, Control control)
        {
            foreach (Control b in control.Controls)
                if (b.GetType() == typeof(TextBox))
                    b.Text = widget.AnswerValue.ToString();
        }

        private void UpdateBoolWidget(QLWidgetBool widget, Control control)
        {
            foreach (Control c in control.Controls)
                if (c.GetType() == typeof(CheckBox))
                    ((CheckBox)c).Checked = widget.AnswerValue;
        }

        private void UpdateStringWidget(QLWidgetString widget, Control control)
        {
            foreach (Control c in control.Controls)
                if (c.GetType() == typeof(TextBox))
                    ((TextBox)c).Text = widget.AnswerValue;
        }
        #endregion

        #region Creators

        /// <summary>
        /// Create a label
        /// </summary>
        /// <param name="labelText">Text for the label</param>
        /// <param name="yLocation">Y location</param>
        /// <param name="style">Label style</param>
        /// <param name="result">Styled label</param>
        /// <returns></returns>
        private int AddLabel(string labelText, int yLocation, WindowsStyleProperties style, ref Control result)
        {
            // Create label
            Control styledLabel = ApplyControlStyle(new Label { Text = labelText, Location = new Point(0, yLocation) }, style);

            // Add to result
            result.Controls.Add(styledLabel);

            // Return bottom
            return styledLabel.Height + styledLabel.Location.Y + style.LabelInputMargin;
        }

        /// <summary>
        /// Creates an integer widget
        /// </summary>
        /// <param name="widget">Widget settings</param>
        /// <param name="style">Style for the widget</param>
        /// <param name="result">Styled Widget</param>
        private void CreateIntWidget(QLWidgetInt widget, WindowsStyleProperties style, ref Control result)
        {
            // Create textbox for integerss
            TextBox input = new TextBox();
            input.TextChanged += delegate (object sender, EventArgs e) { ChangedIntWidget(widget, input); };
            input.Enabled = widget.Editable;
            input.Location = new Point(0, AddLabel(widget.Text, 0, style, ref result));
            result.Controls.Add(ApplyControlStyle(input, style));
        }

        /// <summary>
        /// Creates boolean Widget
        /// </summary>
        /// <param name="widget">Widget settings</param>
        /// <param name="style">Style for the widget</param>
        /// <param name="result">Styled Widget</param>
        private void CreateBoolWidget(QLWidgetBool widget, WindowsStyleProperties style, ref Control result)
        {
            // Create checkbox
            CheckBox checkbox = new CheckBox();
            checkbox.Text = widget.Text;
            checkbox.CheckedChanged += delegate (object sender, EventArgs e) { ChangedBoolWidget(widget, checkbox); };
            checkbox.Enabled = widget.Editable;
            checkbox.Checked = widget.AnswerValue;

            // Add to result
            result.Controls.Add(ApplyControlStyle(checkbox, style));
        }

        /// <summary>
        /// Creates string Widget
        /// </summary>
        /// <param name="widget">Widget settings</param>
        /// <param name="style">Style for the widget</param>
        /// <param name="result">Styled Widget</param>
        private void CreateStringWidget(QLWidgetString widget, WindowsStyleProperties style, ref Control result)
        {
            TextBox textBox = new TextBox();
            textBox.Text = widget.AnswerValue;
            textBox.Location = new Point(0, AddLabel(widget.Text, 0, style, ref result));
            textBox.TextChanged += delegate (object sender, EventArgs e) { ChangedStringWidget(widget, textBox); };

            result.Controls.Add(ApplyControlStyle(textBox, style));
        }

        #endregion

        #region Windows Change Events

        /// <summary>
        /// Int widget change event
        /// </summary>
        /// <param name="intWidget">Sending widget</param>
        /// <param name="input">Input textbox</param>
        private void ChangedIntWidget(QLWidgetInt intWidget, TextBox input)
        {
            // try to parse the value
            int value = 0;

            // If invalid, set textbox to previous value
            if (!int.TryParse(input.Text, out value))
            {
                input.Text = intWidget.AnswerValue.ToString();
                value = intWidget.AnswerValue;
            }

            // Launch update 
            intWidget.SetAnswer(value);
            _widgetController.ValueUpdate(intWidget.Identifyer);
        }

        /// <summary>
        /// Boolean widget change event
        /// </summary>
        /// <param name="boolWidget">Sending widget</param>
        /// <param name="input">Input checkbox</param>
        private void ChangedBoolWidget(QLWidgetBool boolWidget, CheckBox input)
        {
            boolWidget.SetAnswer(input.Checked);
            _widgetController.ValueUpdate(boolWidget.Identifyer);
        }

        /// <summary>
        /// String widget change event
        /// </summary>
        /// <param name="stringWidget">Sending widget</param>
        /// <param name="input">Input textbox</param>
        private void ChangedStringWidget(QLWidgetString stringWidget, TextBox input)
        {
            stringWidget.SetAnswer(input.Text);
            _widgetController.ValueUpdate(stringWidget.Identifyer);
        }
        #endregion
    }
}
