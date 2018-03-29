using System;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Rendering.Widget
{
    public class QLWidgetFactory : IValueVisitor
    {
        private IWidget _result;
        private readonly string _label;
        private readonly bool _readOnly;
        private readonly Action<IValue> _valueChangedHandler;

        public static IWidget GetWidget(string label, IValue value, bool readOnly, Action<IValue> valueChangedHandler)
        {
            var renderer = new QLWidgetFactory(label, readOnly, valueChangedHandler);
            value.Accept(renderer);
            return renderer._result;
        }

        private QLWidgetFactory(string label, bool readOnly, Action<IValue> valueChangedHandler)
        {
            _label = label;
            _readOnly = readOnly;
            _valueChangedHandler = valueChangedHandler;
        }

        public void Visit(QLBoolean value)
        {
            _result = new CheckBox(_label, value, _readOnly, _valueChangedHandler);
        }

        public void Visit(QLInteger value)
        {
            _result = new SpinBox(_label, value, _readOnly, _valueChangedHandler);
        }

        public void Visit(QLString value)
        {
            _result = new TextBox(_label, value, _readOnly, _valueChangedHandler);
        }

        public void Visit(QLDate value)
        {
            _result = new DatePicker(_label, value, _readOnly, _valueChangedHandler);
        }

        public void Visit(QLDecimal value)
        {
            _result = new SpinBox(_label, value, _readOnly, _valueChangedHandler);
        }

        public void Visit(QLMoney value)
        {
            _result = new SpinBox(_label, value, _readOnly, _valueChangedHandler);
        }
    }
}
