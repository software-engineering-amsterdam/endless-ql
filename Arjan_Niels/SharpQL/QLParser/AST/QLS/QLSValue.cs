using QLParser.AST.Nodes;

namespace QLParser.AST.QLS
{
    public struct QLSValue
    {
        public string StyleProperty { get; private set; }
        public QValueType QValueType { get; private set; }

        public QLSValue(string value, QValueType qValueType)
        {
            this.StyleProperty = value;
            this.QValueType = qValueType;
        }

        public override string ToString()
        {
            return string.Format("{0} - {1}", StyleProperty, QValueType);
        }
    }
}
