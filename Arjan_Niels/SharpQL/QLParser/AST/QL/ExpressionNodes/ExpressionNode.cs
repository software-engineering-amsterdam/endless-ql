using QLParser.Analysis.QL.Evaluator;

namespace QLParser.AST.QL.ExpressionNodes
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