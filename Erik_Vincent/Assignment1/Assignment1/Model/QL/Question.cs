using Assignment1.Model.QL.QLExpression;

namespace Assignment1.Model.QL
{
    public abstract class Question
    {
        public int LineNumber { get; }
        public string Id { get; }
        public string Label { get; }
        public AnswerType Type { get; }
        //public Expression Value { get; }
        public dynamic Value
        {
            get => Computed ? Computation.Evaluate() : _value;
            set => _value = value;
        }

        private dynamic _value;
        public Expression Computation;
        public bool Computed => Computation != null;
        public Expression Condition;

        protected Question(string id, string label)
        {
            Id = id;
            Label = label;
        }

        protected Question(string id, string label, int lineNumber, AnswerType type)
        {
            Id = id;
            Label = label;
            LineNumber = lineNumber;
            Type = type;
        }

        protected Question(string id, string label, int lineNumber, AnswerType type, Expression computation)
        {
            Id = id;
            Label = label;
            LineNumber = lineNumber;
            Type = type;
            Computation = computation;
        }

        public abstract void Accept(IQuestionVisitor visitor);

        public void AddCondition(Expression condition) => Condition = Condition == null ? condition :  new ExpressionAnd(condition, Condition);
    }
}
