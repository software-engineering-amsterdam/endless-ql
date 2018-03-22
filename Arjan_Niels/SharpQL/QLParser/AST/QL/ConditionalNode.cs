namespace QLParser.AST.QL
{
    public class ConditionalNode : QLNode
    {
        public IExpressionNode Expression { get; private set; }

        public ConditionalNode(Location location, IExpressionNode statementNode) : base(location, NodeType.CONDITIONAL)
        {
            this.Expression = statementNode;
        }

        public override string ToString()
        {
            return string.Format("{0} {1}", base.ToString(), Expression);
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
