using System;
using System.Runtime.Serialization;

namespace QuestionnaireDomain.Entities.Domain
{
    [Serializable]
    public class QlValidationException : Exception
    {
        public QlValidationException(string message, Exception inner)
            : base(message, inner)
        {
        }

        protected QlValidationException(
            SerializationInfo info,
            StreamingContext context)
        {
        }
    }
}