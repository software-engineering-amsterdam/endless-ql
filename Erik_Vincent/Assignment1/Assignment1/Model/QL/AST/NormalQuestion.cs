using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST
{
    public class NormalQuestion : Question
    {
        public IValue Answer { get; }

        public NormalQuestion(int lineNumber, string id, string label, Type type)
            : this(lineNumber, id, label, type, new Undefined()) { }

        public NormalQuestion(int lineNumber, string id, string label, Type type, IValue answer) : base(lineNumber, id, label, type)
        {
            Answer = answer;
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
