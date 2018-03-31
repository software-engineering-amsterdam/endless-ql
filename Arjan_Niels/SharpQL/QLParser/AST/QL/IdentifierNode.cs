using QLParser.Analysis;

namespace QLParser.AST.QL
{
    public class IdentifierNode : QLNode, IExpressionNode
    {
        public string ID { get; private set; }

        public IdentifierNode(Location location, string id) : base(location, NodeType.Identifier)
        {
            this.ID = id;
        }

        public QValueType GetQValueType()
        {
            return SymbolTable.Get(ID);
        }

        public override string ToString()
        {
            return string.Format("{0} {1}", base.ToString(), ID);
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit((dynamic)this);
        }
    }
}