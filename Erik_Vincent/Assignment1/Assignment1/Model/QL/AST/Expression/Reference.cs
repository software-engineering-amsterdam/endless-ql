namespace Assignment1.Model.QL.AST.Expression
{
    public class Reference : IExpression
    {
        public string QuestionId { get; }

        public Reference(string questionId)
        {
            QuestionId = questionId;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
