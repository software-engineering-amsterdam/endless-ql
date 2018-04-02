namespace QLParser.AST.QL.ExpressionNodes
{
    public class TextConcatinationNode : ExpressionNode
    {
        public TextConcatinationNode(Location location, IExpressionNode left, IExpressionNode right, NodeType type) : base(location, type)
        {
            this.Left = left;
            this.Right = right;
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
