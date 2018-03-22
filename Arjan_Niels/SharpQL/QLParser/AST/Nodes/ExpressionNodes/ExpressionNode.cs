using QLParser.Analysis.Evaluator;

namespace QLParser.AST.Nodes.ExpressionNodes
{
    public abstract class ExpressionNode : QLNode, IExpressionNode
    {
        public IExpressionNode Left { get; protected set; }
        public IExpressionNode Right { get; protected set; }

        public ExpressionNode(Location location, NodeType type) : base(location, type)
        {
        }

        public QValueType GetQValueType()
        {
            return StatementTypeEvaluator.GetStatementResultType(Left, Right);
        }
    }
}