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
            _result = new CheckBox(_label, value.Value, _readOnly, _valueChangedHandler);
        }

        public void Visit(QLInteger value)
        {
        }

        public void Visit(QLString value)
        {
        }

        public void Visit(QLDate value)
        {
        }

        public void Visit(QLDecimal value)
        {
        }

        public void Visit(QLMoney value)
        {
        }
    }
}
