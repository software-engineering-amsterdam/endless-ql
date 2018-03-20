using System;
using System.Runtime.Serialization;

namespace QLParser.Exceptions
{
    public class UnknownQValueTypeException : Exception
    {
        public UnknownQValueTypeException()
        {
        }

        public UnknownQValueTypeException(string message) : base(message)
        {
        }

        public UnknownQValueTypeException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected UnknownQValueTypeException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
