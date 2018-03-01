using QL_Parser.Analysis;

namespace QL_Parser.AST.Nodes
{
    public class IdentifierNode : Node, IExpressionNode
    {
        public string ID { get; private set; }

        public IdentifierNode(string id) : base(NodeType.IDENTIFIER)
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