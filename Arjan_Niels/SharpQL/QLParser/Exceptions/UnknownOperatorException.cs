using System;
using System.Runtime.Serialization;

namespace QLParser.Exceptions
{
    public class UnknownOperatorException : Exception
    {
        public UnknownOperatorException()
        {
        }

        public UnknownOperatorException(string message) : base(message)
        {
        }

        public UnknownOperatorException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected UnknownOperatorException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
