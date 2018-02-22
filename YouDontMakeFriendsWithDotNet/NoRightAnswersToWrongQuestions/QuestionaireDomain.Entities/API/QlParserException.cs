using System;

namespace QuestionaireDomain.Entities.API
{
    public class QlParserException : Exception
    {
        public string ParseErrorDetails { get; set; }
        
        public QlParserException(string message, Exception inner)
            : base(message, inner)
        {
        }
    }
}
