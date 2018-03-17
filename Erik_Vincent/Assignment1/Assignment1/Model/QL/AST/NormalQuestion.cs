namespace Assignment1.Model.QL.AST
{
    public class NormalQuestion : Question
    {
        public Value Answer { get; }

        public NormalQuestion(string id, string label, Type type)
            : this(id, label, type, new Undefined(type)) { }

        public NormalQuestion(string id, string label, Type type, Value answer) : base(id, label, type)
        {
            Answer = answer;
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
