using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using QLVisualizer.Expression;
using QLVisualizer.Factories;
using QLVisualizer.Properties;
using QLVisualizer.Style;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Widgets;

namespace QLVisualizer.Controllers.Display
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

        public ElementManagerDisplayContollerWindows(FormManager form, float topMargin) : base(form, topMargin, new WindowsStyleProperties { Width = 338 }, new WidgetCreatorWindows())
        {
            _elementFactory = new ControlFactory(this);
            ConstructMainWindow();
            BaseDisplay = _widgetContainer;
        }

        /// <summary>
        /// Show main form
        /// </summary>
        public override void ShowView()
        {
            Application.Run(_mainForm);
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

            // Assign controls
            _mainForm.Controls.AddRange(new Control[]
            {
                _widgetContainer,
                _qlInput,
                _parseButton,
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
                Icon = (Icon)Resources.ResourceManager.GetObject("MainIcon"),
                Name = "Visualizer",
                Text = "Sharp QL"
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
