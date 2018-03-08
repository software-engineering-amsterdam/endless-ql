using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using QLVizualizer.Expression;
using QLVizualizer.Expression.Types;
using QLVizualizer.Factories;
using QLVizualizer.Properties;
using QLVizualizer.Style;
using QLVizualizer.ElementManagers;
using QLVizualizer.ElementManagers.Types;

namespace QLVizualizer.Controllers.Display
{
    public class ElementManagerDisplayContollerWindows : WidgetDisplayController<Control, WindowsStyleProperties>
    {
        /// <summary>
        /// Control element to add all created controls to
        /// </summary>
        private Control _widgetContainer;

        /// <summary>
        /// QL input field
        /// </summary>
        private TextBox _qlInput;

        /// <summary>
        /// Parse button
        /// </summary>
        private Button _parseButton;

        /// <summary>
        /// Form shown to user
        /// </summary>
        private Form _mainForm;

        private Label _titleLabel;

        public ElementManagerDisplayContollerWindows(float topMargin) : base(topMargin, new WindowsStyleProperties { Width = 338 })
        {
            _elementFactory = new ControlFactory(this);
            ConstructMainWindow();
        }

        /// <summary>
        /// Shows specific widget
        /// </summary>
        /// <param name="widget">Widget to show</param>
        /// <param name="position">X position to show widget</param>
        /// <returns></returns>
        public override float ShowWidget(ElementManager widget, WindowsStyleProperties style)
        {
            // Create control
            Control control = null;
            if (ElementIndex.ContainsKey(widget.Identifyer))
            {
                control = ElementIndex[widget.Identifyer];
                control.Location = new Point(0, style.YPosition);
            }
            else
                control = CreateElement(widget, style);

            // Calculate bottom position
            int newBottom = control.Height + control.Location.Y;

            // Check if form has enough space, extend if needed
            if (_widgetContainer.Height < newBottom)
                _widgetContainer.Height = newBottom + (int)InitialPosition;

            // Add control to form
            _widgetContainer.Controls.Add(control);

            // Return bottom
            return newBottom;
        }

        /// <summary>
        /// Show main form
        /// </summary>
        public override void ShowView()
        {
            Application.Run(_mainForm);
        }

        /// <summary>
        /// Updates style of widget
        /// </summary>
        /// <param name="widget">Target widget</param>
        /// <param name="positionAbove">Bottom of previous</param>
        /// <param name="style">Style of target</param>
        /// <returns>Updated style</returns>
        public override WindowsStyleProperties UpdatePosition(ElementManager widget, float positionAbove, WindowsStyleProperties style)
        {
            style.YPosition += (int)positionAbove;
            return style;
        }

        protected override void RemoveFromView(Control element)
        {
            element.Dispose();
        }

        /*/// <summary>
        /// Temporary function to diplay dummy widgets
        /// </summary>
        public void DummyQL()
        {
            // Create widgets
            _widgetController.SetWidgets(new QLWidget[]
            {
                new QLWidgetInt("a", "wat is 10 + 1?"),
                new QLWidgetInt("b", "wat is 5 + 3?"),
                new QLWidgetInt("c", "som:", null, new ExpressionInt(new string[]{"a","b" },() => {
                    return (_widgetController.GetWidget("a") as QLWidgetInt).AnswerValue + (_widgetController.GetWidget("b") as QLWidgetInt).AnswerValue;
                })),
                new QLWidgetBool("d", "This statement is False")

            });

            // Display widgets
            _widgetController.ShowWidgets();
        }*/

        /// <summary>
        /// Shows message box with error(s) to user
        /// </summary>
        /// <param name="errors">One or more errors</param>
        public override void ShowError(params string[] errors)
        {
            // Show message box
            MessageBox.Show(string.Join("\n", errors), errors.Length > 1 ? "Errors occured" : "Error occured", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        /// <summary>
        /// Sets title of current form
        /// </summary>
        /// <param name="title">Title of form</param>
        protected override void SetTitle(string title)
        {
            _titleLabel.Text = title;
        }

        /// <summary>
        /// Resets all values that define its state
        /// </summary>
        public override void Reset()
        {
            base.Reset();
            _widgetContainer.Controls.Clear();
        }

        #region Main window constructors
        /// <summary>
        /// Construct view and assigns it to _mainForm
        /// </summary>
        private void ConstructMainWindow()
        {
            // Create form
            _mainForm = CreateForm();

            // Create form controls
            _widgetContainer = CreateWidgetPanel();
            _qlInput = CreateQLInputPanel();
            _parseButton = CreateParseButton();
            _titleLabel = CreateTitle();

            // Assign controls
            _mainForm.Controls.AddRange(new Control[]
            {
                _widgetContainer,
                _qlInput,
                _parseButton,
                _titleLabel
            });

            _mainForm.ResumeLayout(false);
            _mainForm.PerformLayout();
        }

        /// <summary>
        /// Creates form element
        /// </summary>
        /// <returns>Form object</returns>
        private Form CreateForm()
        {
            return new Form
            {
                AutoScaleDimensions = new SizeF(6f, 13f),
                AutoScaleMode = AutoScaleMode.Font,
                ClientSize = new Size(738, 673),
                Icon = (Icon)Resources.ResourceManager.GetObject("programIcon"),
                Name = "Visualizer",
                Text = "Sharp QL"
            };
        }


        /// <summary>
        /// Creates label element for title
        /// </summary>
        /// <returns>Title element</returns>
        private Label CreateTitle()
        {
            return new Label
            {
                Location = new Point(378, 12),
                Text = "Form: -"
            };
        }

        /// <summary>
        /// Creates widget panel
        /// </summary>
        /// <returns>Widget panel</returns>
        private Panel CreateWidgetPanel()
        {
            return new Panel
            {
                Location = new Point(378, 32),
                Name = "widgetDisplayPanel",
                Size = new Size(348, 575)
            };
        }

        /// <summary>
        /// Creates QL input textbox
        /// </summary>
        /// <returns>QL inputfield</returns>
        private TextBox CreateQLInputPanel()
        {
            return new TextBox
            {
                AcceptsReturn = true,
                AcceptsTab = true,
                Location = new Point(12, 9),
                Multiline = true,
                Name = "qlInputField",
                Size = new Size(350, 595)
            };
        }

        private Button CreateParseButton()
        {
            Button result = new Button
            {
                Location = new Point(12, 613),
                Name = "parseButton",
                Size = new Size(714, 48),
                Text = "Parse",
                UseVisualStyleBackColor = true
            };

            //result.Click += delegate (object sender, EventArgs eventArgs) { DummyQL(); };
            result.Click += delegate (object sender, EventArgs eventArgs) { HandleQL(_qlInput.Text); };
            return result;
        }
        #endregion
    }
}
