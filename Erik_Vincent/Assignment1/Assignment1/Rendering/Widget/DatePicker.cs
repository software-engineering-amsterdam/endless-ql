using System;
using System.Windows.Forms;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Rendering.Widget
{
    public class DatePicker : IWidget
    {
        private readonly Panel _panel = new FlowLayoutPanel
        {
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };
        private readonly DateTimePicker _dateTimePicker;
        private readonly Action<IValue> _valueChangedHandler;

        public DatePicker(string label, QLDate value, bool readOnly, Action<IValue> valueChangedHandler)
        {
            _valueChangedHandler = valueChangedHandler;
            _panel.Controls.Add(new Label
            {
                AutoSize = true,
                Text = label
            });
            _dateTimePicker = new DateTimePicker
            {
                MinDate = DateTime.MinValue,
                MaxDate = DateTime.MaxValue,
                Value = value.Value,
                Enabled = !readOnly
            };
            if (!readOnly)
                _dateTimePicker.ValueChanged += DateTimePicker_ValueChanged;
            _panel.Controls.Add(_dateTimePicker);
        }

        private void DateTimePicker_ValueChanged(object sender, EventArgs e)
        {
            _valueChangedHandler(new QLDate(_dateTimePicker.Value));
        }

        public Control Render() => _panel;
    }
}
