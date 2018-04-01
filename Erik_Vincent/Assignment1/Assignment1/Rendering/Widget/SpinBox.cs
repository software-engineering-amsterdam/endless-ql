using System;
using System.Windows.Forms;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Rendering.Widget
{
    public class SpinBox : IWidget
    {
        private readonly Panel _panel = new FlowLayoutPanel
        {
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };
        private readonly NumericUpDown _numericUpDown;
        private readonly Action _valueChangedHandler;

        private SpinBox(string label, decimal value, decimal minimum, decimal maximum, int step, int decimalPlaces, bool readOnly)
        {
            AddLabel(label);
            _numericUpDown = new NumericUpDown
            {
                AutoSize = true,
                Minimum = minimum,
                Maximum = maximum,
                Increment = step,
                DecimalPlaces = decimalPlaces,
                Value = value,
                Enabled = !readOnly
            };
            if (!readOnly)
                _numericUpDown.ValueChanged += (s, a) => _valueChangedHandler();
            _panel.Controls.Add(_numericUpDown);
        }

        private void AddLabel(string label) => _panel.Controls.Add(new Label
        {
            AutoSize = true,
            Text = label
        });

        public SpinBox(string label, QLInteger value, bool readOnly, Action<IValue> valueChangedHandler)
            : this(label, value.Value, int.MinValue, int.MaxValue, 1, 0, readOnly)
        {
            _valueChangedHandler = () => valueChangedHandler(new QLInteger(Convert.ToInt32(_numericUpDown.Value)));
        }

        public SpinBox(string label, QLDecimal value, bool readOnly, Action<IValue> valueChangedHandler)
            : this(label, value.Value, decimal.MinValue, decimal.MaxValue, 1, 2, readOnly)
        {
            _valueChangedHandler = () => valueChangedHandler(new QLDecimal(_numericUpDown.Value));
        }

        public SpinBox(string label, QLMoney value, bool readOnly, Action<IValue> valueChangedHandler)
            : this(label, value.Value, decimal.MinValue, decimal.MaxValue, 1, 2, readOnly)
        {
            _valueChangedHandler = () => valueChangedHandler(new QLMoney(_numericUpDown.Value));
        }

        public Control Render() => _panel;
    }
}
