using System;
using System.Runtime.Serialization;

namespace QL_Parser.Exceptions
{
    public class UnknownNodeTypeException : Exception
    {
        public UnknownNodeTypeException()
        {
        }

        public UnknownNodeTypeException(string message) : base(message)
        {
        }

        public UnknownNodeTypeException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected UnknownNodeTypeException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
