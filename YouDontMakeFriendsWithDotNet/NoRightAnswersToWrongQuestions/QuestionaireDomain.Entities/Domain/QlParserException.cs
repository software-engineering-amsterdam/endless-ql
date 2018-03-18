using System;

namespace QuestionnaireDomain.Entities.Domain
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
