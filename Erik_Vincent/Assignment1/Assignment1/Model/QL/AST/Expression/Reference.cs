namespace Assignment1.Model.QL.AST.Expression
{
    public class Reference : QLNode, IExpression
    {
        public string QuestionId { get; }

        public Reference(int lineNumber, string questionId)
        {
            _lineNumber = lineNumber;
            QuestionId = questionId;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
