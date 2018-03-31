using QLParser.AST.QL;

namespace QLParser.AST.QLS
{
    public struct QLSValue
    {
        public string StyleProperty { get; private set; }
        public string StyleValue { get; private set; }
        public QValueType QValueType { get; private set; }

        public QLSValue(string property, string value, QValueType qValueType)
        {
            this.StyleProperty = property;
            this.StyleValue = value;
            this.QValueType = qValueType;
        }

        public override string ToString()
        {
            return string.Format("{0} - {1} - {2}", StyleProperty, QValueType, StyleValue);
        }
    }
}
