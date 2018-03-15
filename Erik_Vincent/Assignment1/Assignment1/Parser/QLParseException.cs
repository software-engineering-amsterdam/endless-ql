using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Parser
{
    class QLParseException : Exception
    {
        public readonly List<string> Exceptions;

        public QLParseException(string message, List<string> exceptions) : base(message)
        {
            Exceptions = exceptions;
        }
    }
}
