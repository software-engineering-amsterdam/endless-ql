using System;
using System.Windows.Forms;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Rendering.Widget
{
    public class CheckBox : IWidget
    {
        private readonly System.Windows.Forms.CheckBox _checkBox;
        private readonly Action<IValue> _valueChangedHandler;

        public CheckBox(string label, QLBoolean value, bool readOnly, Action<IValue> valueChangedHandler)
        {
            _valueChangedHandler = valueChangedHandler;
            _checkBox = new System.Windows.Forms.CheckBox
            {
                AutoSize = true,
                Text = label,
                Checked = value.Value,
                Enabled = !readOnly
            };
            _checkBox.CheckedChanged += _checkBox_CheckedChanged;
        }

        private void _checkBox_CheckedChanged(object sender, EventArgs e)
        {
            _valueChangedHandler(new QLBoolean(_checkBox.Checked));
        }

        public Control Render()
        {
            return _checkBox;
        }
    }
}
