using System;
using System.Drawing;
using System.Windows.Forms;
using QLVisualizer.Factories;
using QLVisualizer.Properties;
using QLVisualizer.Elements.Managers.CollectionTypes;

namespace QLVisualizer.Controllers.Display
{
    public class ElementManagerDisplayContollerWindows : WidgetDisplayController<Control>
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
        /// QLS input field
        /// </summary>
        private TextBox _qlsInput;

        /// <summary>
        /// Parse button
        /// </summary>
        private Button _parseButton;

        /// <summary>
        /// Form shown to user
        /// </summary>
        private Form _mainForm;

        public ElementManagerDisplayContollerWindows(FormManager form, float topMargin) : base(form, topMargin)
        {
            //_elementFactory = new ControlFactory(this);
            ConstructMainWindow();
        }

        /// <summary>
        /// Show main form
        /// </summary>
        public override void ShowView()
        {
            Application.Run(_mainForm);
        }

        protected override void UpdateBaseDisplay(Control newDisplay)
        {
            _widgetContainer.Controls.Clear();
            _widgetContainer.Controls.Add(newDisplay);
            BaseDisplay = newDisplay;
        }

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

        protected override Control CreateFormWidget()
        {
            return WidgetFactoryWindows.GetBuilder(Form, null).Create();
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
            _qlInput = CreateInputTextBox();
            _qlsInput = CreateInputTextBox();
            _parseButton = CreateParseButton();

            // Assign controls
            _mainForm.Controls.AddRange(new Control[]
            {
                _widgetContainer,
                CreateInputTextBoxHolder(_qlInput, "QL:", true),
                CreateInputTextBoxHolder(_qlsInput, "QLS:", false),
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
                ClientSize = new Size(738, 700),
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
                Size = new Size(348, 605),
                AutoScroll = true
            };
        }

        /// <summary>
        /// Creates QL input textbox
        /// </summary>
        /// <returns>QL inputfield</returns>
        private TextBox CreateInputTextBox()
        {
            return new TextBox
            {
                AcceptsReturn = true,
                AcceptsTab = true,
                Location = new Point(12, 26),
                Multiline = true,
                Name = "qlInputField",
                Size = new Size(350, 285),
                ScrollBars = ScrollBars.Both
            };
        }

        private Panel CreateInputTextBoxHolder(TextBox textbox, string title, bool isTop)
        {
            Panel holder = new Panel
            {
                Height = 311,
                Width = 362,
                Location = new Point(12, isTop ? 9 : 326)
            };
            holder.Controls.Add(new Label { Text = title, Location = new Point(12, 9), Height = 12 });
            holder.Controls.Add(textbox);
            return holder;
        }

        private Button CreateParseButton()
        {
            Button result = new Button
            {
                Location = new Point(23, 642),
                Name = "parseButton",
                Size = new Size(698, 48),
                Text = "Parse",
                UseVisualStyleBackColor = true
            };

            result.Click += delegate (object sender, EventArgs eventArgs) { HandleInput(_qlInput.Text, _qlsInput.Text); };
            return result;
        }
        #endregion
    }
}
