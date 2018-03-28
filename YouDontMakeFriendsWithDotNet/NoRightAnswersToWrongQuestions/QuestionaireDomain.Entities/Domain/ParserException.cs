using System;
using System.Runtime.Serialization;

namespace QuestionnaireDomain.Entities.Domain
{
    [Serializable]
    public class ParserException : Exception
    {
        public string ParseErrorDetails { get; set; }

        protected ParserException(
            SerializationInfo info,
            StreamingContext context) : base(info, context)
        {
        }

        public ParserException(string message, Exception inner)
            : base(message, inner)
        {
        }
    }
}
