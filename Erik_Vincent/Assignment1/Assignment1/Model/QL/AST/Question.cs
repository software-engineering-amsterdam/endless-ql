namespace Assignment1.Model.QL.AST
{
    public abstract class Question : Statement
    {
        public string Id { get; }
        public string Label { get; }
        public Type Type { get; }

        protected Question(string id, string label, Type type)
        {
            Id = id;
            Label = label;
            Type = type;
        }
    }
}
