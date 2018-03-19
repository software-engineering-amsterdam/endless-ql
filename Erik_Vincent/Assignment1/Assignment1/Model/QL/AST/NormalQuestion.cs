using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST
{
    public class NormalQuestion : Question
    {
        public IValue Answer { get; }

        public NormalQuestion(string id, string label, Type type)
            : this(id, label, type, new Undefined()) { }

        public NormalQuestion(string id, string label, Type type, IValue answer) : base(id, label, type)
        {
            Answer = answer;
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
