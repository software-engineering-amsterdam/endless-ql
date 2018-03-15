using QLParser.AST.Nodes;

namespace QLParser.AST.QLS
{
    public struct QLSStyleValue
    {
        public string Value { get; private set; }
        public QValueType QValueType { get; private set; }

        public QLSStyleValue(string value, QValueType qValueType)
        {
            this.Value = value;
            this.QValueType = qValueType;
        }

        public override string ToString()
        {
            return string.Format("{0} - {1}", Value, QValueType);
        }
    }
}
