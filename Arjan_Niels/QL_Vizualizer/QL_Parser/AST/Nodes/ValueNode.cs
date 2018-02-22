using QL_Parser.Analysis;

namespace QL_Parser.AST.Nodes
{
    public class ValueNode : Node, IExpressionNode
    {
        public string ID { get; private set; }

        public ValueNode(string id) : base(NodeType.VALUE)
        {
            this.ID = id;
        }

        public QValueType GetQValueType()
        {
            return SymbolTable.Get(ID);
        }

        public NodeType GetNodeType()
        {
            return Type;
        }

        public override string ToString()
        {
            return string.Format("{0} {1}", base.ToString(), ID);
        }
    }
}
