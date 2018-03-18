using System;

namespace QuestionnaireDomain.Entities.Domain
{
    public class QlValidationException : Exception
    {
        public QlValidationException(string message, Exception inner)
            : base(message, inner)
        {
        }
    }
}