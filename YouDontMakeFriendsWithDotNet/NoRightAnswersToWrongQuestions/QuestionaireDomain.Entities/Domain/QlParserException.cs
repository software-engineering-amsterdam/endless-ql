using System;
using System.Runtime.Serialization;

namespace QuestionnaireDomain.Entities.Domain
{
    [Serializable]
    public class QlParserException : Exception
    {
        public string ParseErrorDetails { get; set; }

        protected QlParserException(
            SerializationInfo info,
            StreamingContext context) : base(info, context)
        {
        }

        public QlParserException(string message, Exception inner)
            : base(message, inner)
        {
        }
    }
}
