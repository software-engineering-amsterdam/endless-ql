using System;
using System.Windows.Forms;

namespace Assignment1.Rendering.Widget
{
    public class Undefined : IWidget
    {
        private readonly Panel _panel = new FlowLayoutPanel
        {
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };

        private void AddLabel(string label) => _panel.Controls.Add(new Label
        {
            AutoSize = true,
            Text = label
        });

        public Undefined(string label, bool readOnly, Action clickHandler)
        {
            AddLabel(label);
            if (readOnly)
            {
                _panel.Controls.Add(new Label { Text = "Undefined" });
            }
            else
            {
                var button = new Button
                {
                    AutoSize = true,
                    Text = "Define"
                };
                button.Click += (s, a) =>
                {
                    clickHandler();
                };
                _panel.Controls.Add(button);
            }
        }

        public Control Render() => _panel;
    }
}
