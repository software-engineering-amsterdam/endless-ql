namespace QL_Parser.AST.Nodes
{
    public class LiteralNode : Node, IExpressionNode
    {
        public string Value { get; private set; }
        public QValueType QValueType { get; set; }

        public LiteralNode(string value, QValueType qValueType) : base(NodeType.LITERAL)
        {
            this.Value = value;
            this.QValueType = qValueType;
        }

        public NodeType GetNodeType()
        {
            return this.Type;
        }

        public QValueType GetQValueType()
        {
            return this.QValueType;
        }

        public override string ToString()
        {
            return string.Format("{0} {1}", base.ToString(), Value);
        }
    }
}
