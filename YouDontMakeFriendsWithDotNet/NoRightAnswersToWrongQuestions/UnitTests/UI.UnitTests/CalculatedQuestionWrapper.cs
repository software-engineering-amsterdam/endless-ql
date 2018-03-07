using System;

namespace UnitTests.UI.UnitTests
{
    public class CalculatedQuestionWrapper
    {
        public CalculatedQuestionWrapper(Guid id, string text, string value)
        {
            if (string.IsNullOrEmpty(text))
            {
                throw new ArgumentNullException(nameof(text));
            }
            if (string.IsNullOrEmpty(value))
            {
                throw new ArgumentNullException(nameof(value));
            }

        }
    }
}