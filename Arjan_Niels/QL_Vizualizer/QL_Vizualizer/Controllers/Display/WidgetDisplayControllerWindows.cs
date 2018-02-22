using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using QL_Vizualizer.Factories;
using QL_Vizualizer.Properties;
using QL_Vizualizer.Style;
using QL_Vizualizer.Widgets;
using QL_Vizualizer.Widgets.Types;

namespace QL_Vizualizer.Controllers.Display
{
    public class WidgetDisplayControllerWindows : WidgetDisplayController<Control, WindowsStyleProperties>
    {
        /// <summary>
        /// Control element to add all created controls to
        /// </summary>
        private Control _widgetContainer;

        private TextBox _qlInput;

        private Button _parseButton;

        private Form _mainForm;

        public WidgetDisplayControllerWindows(float topMargin, WidgetController widgetController) : base(topMargin, new ControlFactory(widgetController), widgetController, new WindowsStyleProperties { Width = 338 })
        {
            ConstructMainWindow();
            
        }

        /// <summary>
        /// Shows specific widget
        /// </summary>
        /// <param name="widget">Widget to show</param>
        /// <param name="position">X position to show widget</param>
        /// <returns></returns>
        public override float ShowWidget(QLWidget widget, WindowsStyleProperties style)
        {
            // Create control
            Control control = CreateElement(widget, style);

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

        public override void ShowDisplay()
        {
            Application.Run(_mainForm);
        }

        public override WindowsStyleProperties UpdatePosition(QLWidget widget, float positionAbove, WindowsStyleProperties style)
        {
            style.YPosition += (int)positionAbove;
            return style;
        }

        public override void ParseQL(string rawQL)
        {
            base.ParseQL(rawQL);

            // Create widgets
            _widgetController.SetWidgets(new List<QLWidget>()
            {
                new QLWidgetInt("a", "wat is 10 + 1?"),
                new QLWidgetInt("b", "wat is 5 + 3?"),
                new QLWidgetInt("c", "som:", null, new Expression<int>(() => {
                    return (_widgetController.GetWidget("a") as QLWidgetInt).AnswerValue + (_widgetController.GetWidget("b") as QLWidgetInt).AnswerValue;
                }, "a", "b")),
                new QLWidgetBool("d", "This statement is False")

            });

            // Display widgets
            _widgetController.ShowWidgets();
        }

        #region Main window constructors
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
                _parseButton
            });

            _mainForm.ResumeLayout(false);
            _mainForm.PerformLayout();
        }

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

        private Panel CreateWidgetPanel()
        {
            return new Panel
            {
                Location = new Point(378, 12),
                Name = "widgetDisplayPanel",
                Size = new Size(348, 595)
            };
        }

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

            result.Click += delegate (object sender, EventArgs eventArgs) { ParseQL(_qlInput.Text); };
            return result;
        }
        #endregion
    }
}
