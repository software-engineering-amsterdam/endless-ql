﻿using QL_Vizualizer.Controllers;
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
                case QLWidgetMoney moneyWidget:
                    CreateMoneyWidget(moneyWidget, style, ref result);
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
        /// Construct textbox 
        /// </summary>
        /// <typeparam name="T">Expected input type</typeparam>
        /// <param name="widget">Widget to copple to</param>
        /// <param name="style">Textbox style</param>
        /// <param name="result">Parent control</param>
        /// <returns>Created textbox already present in result</returns>
        private TextBox ConstructTextbox<T>(QLQuestionWidget<T> widget, WindowsStyleProperties style, ref Control result)
        {
            TextBox textBox = new TextBox();
            textBox.Text = widget.AnswerValue.ToString();
            textBox.Location = new Point(0, AddLabel(widget.Text, 0, style, ref result));
            textBox.Enabled = widget.Editable;

            textBox = ApplyControlStyle(textBox, style) as TextBox;
            result.Controls.Add(textBox);

            return textBox;
        }

        /// <summary>
        /// Creates an integer widget
        /// </summary>
        /// <param name="widget">Widget settings</param>
        /// <param name="style">Style for the widget</param>
        /// <param name="result">Styled Widget</param>
        private void CreateIntWidget(QLWidgetInt widget, WindowsStyleProperties style, ref Control result)
        {
            // Create textbox for integers
            TextBox input = ConstructTextbox<int>(widget, style, ref result);

            // Add change listener
            input.TextChanged += delegate (object sender, EventArgs e) { ChangedIntWidget(widget, input); };
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
            // Create textbox
            TextBox input = ConstructTextbox<string>(widget, style, ref result);

            // Add listener
            input.TextChanged += delegate (object sender, EventArgs e) { ChangedStringWidget(widget, input); };
        }


        private void CreateMoneyWidget(QLWidgetMoney widget, WindowsStyleProperties style, ref Control result)
        {
            TextBox input = ConstructTextbox<double>(widget, style, ref result);

            // Add listener
            input.TextChanged += delegate (object sender, EventArgs e) { ChangedMoneyWidget(widget, input); };
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
            // Parse value
            ParsedWidgetValue<int> value = intWidget.ParseInput(input.Text);

            // Assign parsed answer to input
            if (value.IsValid)
            {
                input.Text = value.Value.ToString();
                intWidget.SetAnswer(value.Value);
            }
            else if (input.Text != "")
                input.Text = value.Value.ToString();

            // Launch update 
        }

        /// <summary>
        /// Boolean widget change event
        /// </summary>
        /// <param name="boolWidget">Sending widget</param>
        /// <param name="input">Input checkbox</param>
        private void ChangedBoolWidget(QLWidgetBool boolWidget, CheckBox input)
        {
            bool value = boolWidget.Validate(input.Checked);
            boolWidget.SetAnswer(value);
            input.Checked = value;
        }

        /// <summary>
        /// String widget change event
        /// </summary>
        /// <param name="stringWidget">Sending widget</param>
        /// <param name="input">Input textbox</param>
        private void ChangedStringWidget(QLWidgetString stringWidget, TextBox input)
        {
            ParsedWidgetValue<string> value = stringWidget.ParseInput(input.Text);
            if (value.IsValid)
            {
                input.Text = value.Value.ToString();
                stringWidget.SetAnswer(value.Value);
            }
            else if (input.Text != "")
                input.Text = value.Value.ToString();
        }


        private void ChangedMoneyWidget(QLWidgetMoney moneyWidget, TextBox input)
        {
            ParsedWidgetValue<double> value = moneyWidget.ParseInput(input.Text);

            if (value.IsValid)
            {
                input.Text = value.Value.ToString();
                moneyWidget.SetAnswer(value.Value);
            }
            else if (input.Text != "")
                input.Text = moneyWidget.AnswerValue.ToString();

        }
        #endregion
    }
}
