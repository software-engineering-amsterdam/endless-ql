namespace Assignment1.Model
{
    public abstract class Question : Content
    {
        public string Id { get; }
        public string Label { get; }
        public dynamic Value
        {
            get => Computed ? Expression.Evaluate() : _value;
            set => _value = value;
        }

        private dynamic _value;
        public Expression Expression;
        public bool Computed;

        protected Question(string id, string label)
        {
            Id = id;
            Label = label;
        }
    }

    internal class QuestionBool : Question
    {
        public QuestionBool(string id, string label) : base(id, label)
        {
            Value = false;
        }
    }

    internal class QuestionMoney : Question
    {
        public QuestionMoney(string id, string label) : base(id, label)
        {
            Value = 0.0;
        }
    }
}
