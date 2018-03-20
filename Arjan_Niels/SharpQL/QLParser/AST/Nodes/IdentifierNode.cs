using QLParser.AST;
using QLParser.Analysis;

namespace QLParser.AST.Nodes
{
    public class IdentifierNode : Node, IExpressionNode
    {
        public string ID { get; private set; }

        public IdentifierNode(Location location, string id) : base(location, NodeType.IDENTIFIER)
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