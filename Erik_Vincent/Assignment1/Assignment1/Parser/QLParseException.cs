using System;
using System.Collections.Generic;

namespace Assignment1.Parser
{
    public class QLParseException : Exception
    {
        public readonly List<string> Exceptions;

        public QLParseException(string message, List<string> exceptions) : base(message)
        {
            Exceptions = exceptions;
        }
    }
}
