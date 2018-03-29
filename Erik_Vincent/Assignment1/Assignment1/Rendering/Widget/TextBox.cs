using System;
using System.Windows.Forms;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Rendering.Widget
{
    public class TextBox : IWidget
    {
        private readonly Panel _panel = new FlowLayoutPanel
        {
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };
        private readonly System.Windows.Forms.TextBox _textBox;
        private readonly Action<IValue> _valueChangedHandler;

        public TextBox(string label, QLString value, bool readOnly, Action<IValue> valueChangedHandler)
        {
            _valueChangedHandler = valueChangedHandler;
            _panel.Controls.Add(new Label
            {
                AutoSize = true,
                Text = label
            });
            _textBox = new System.Windows.Forms.TextBox
            {
                Text = value.Value,
                AutoSize = true,
                ReadOnly = readOnly
            };
            if (!readOnly)
                _textBox.Validated += _textBox_Validated;
            _panel.Controls.Add(_textBox);
        }

        private void _textBox_Validated(object sender, EventArgs e)
        {
            _valueChangedHandler(new QLString(_textBox.Text));
        }

        public Control Render() => _panel;
    }
}
