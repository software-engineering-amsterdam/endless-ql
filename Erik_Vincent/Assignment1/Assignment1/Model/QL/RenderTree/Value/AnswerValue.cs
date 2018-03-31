namespace Assignment1.Model.QL.RenderTree.Value
{
    public class AnswerValue<T> : IAnswerValuable
    {
        private T _value;
        private AnswerType _type;
        private bool _isUndefined;
        public T Value => _value;
        public AnswerType Type => _type;
        public bool IsUndefined => _isUndefined;

        public AnswerValue(T value, AnswerType type)
        {
            _value = value;
            _type = type;
            _isUndefined = false;
        }

        public AnswerValue(T value, AnswerType type, bool isUndefined)
        {
            _value = value;
            _type = type;
            _isUndefined = isUndefined;
        }
    }
}
