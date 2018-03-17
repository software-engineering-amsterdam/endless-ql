using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST
{
    public class ComputedQuestion : Question
    {
        public IExpression Computation { get; }

        public ComputedQuestion(string id, string label, Type type, IExpression computation) : base(id, label, type)
        {
            Computation = computation;
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
