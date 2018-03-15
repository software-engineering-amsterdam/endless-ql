using System;

namespace Assignment1.Model
{
    public abstract class Question
    {
        public string Id { get; }
        public string Label { get; }
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

        public abstract void Accept(IQuestionVisitor visitor);

        public void AddCondition(Expression condition) => Condition = Condition == null ? condition :  new ExpressionAnd(condition, Condition);
    }
}
