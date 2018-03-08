namespace QLParser.AST.Nodes
{
    public class ConditionalNode : Node
    {
        public IExpressionNode Expression { get; private set; }

        public ConditionalNode(IExpressionNode statementNode) : base(NodeType.CONDITIONAL)
        {
            this.Expression = statementNode;
        }

        public override string ToString()
        {
            return string.Format("{0} {1}", base.ToString(), Expression);
        }
    }
}
