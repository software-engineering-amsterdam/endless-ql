using System;
using System.Runtime.Serialization;

namespace QLParser.Exceptions
{
    public class UnknownLogicalEntity : Exception
    {
        public UnknownLogicalEntity()
        {
        }

        public UnknownLogicalEntity(string message) : base(message)
        {
        }

        public UnknownLogicalEntity(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected UnknownLogicalEntity(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
