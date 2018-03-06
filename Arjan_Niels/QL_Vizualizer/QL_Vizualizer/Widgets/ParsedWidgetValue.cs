namespace QL_Vizualizer.Widgets
{
    /// <summary>
    /// Response of a ParseInput of a Widget
    /// Indicates widgets value and validity of the input
    /// </summary>
    /// <typeparam name="T">Type of value</typeparam>
    public struct ParsedWidgetValue<T>
    {
        /// <summary>
        /// Value of the widget
        /// </summary>
        public T Value { get; private set; }

        /// <summary>
        /// Input was valid value
        /// </summary>
        public bool IsValid { get; private set; }

        public ParsedWidgetValue(T value, bool isValid)
        {
            Value = value;
            IsValid = isValid;
        }
    }
}
