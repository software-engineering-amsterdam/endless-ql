namespace QLVisualizer.Elements.Managers
{
    /// <summary>
    /// Response of a ParseInput of a Widget
    /// Indicates widgets value and validity of the input
    /// </summary>
    /// <typeparam name="T">Type of value</typeparam>
    public struct QuestionElementValue<T>
    {
        public T Value { get; private set; }

        public bool IsValid { get; private set; }

        public QuestionElementValue(T value, bool isValid)
        {
            Value = value;
            IsValid = isValid;
        }
    }
}
