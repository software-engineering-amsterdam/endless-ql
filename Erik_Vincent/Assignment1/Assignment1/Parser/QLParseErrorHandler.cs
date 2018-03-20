using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Parser
{
    class QLParseErrorHandler
    {
        private readonly List<string> _errors = new List<string>();

        public bool FormHasErrors => _errors.Count > 0;

        public void AddError(int lineNumber, string message)
        {
            _errors.Add("Line " + lineNumber + ": " + message);
        }

        public void ThrowQLParseException()
        {
            throw new QLParseException("Invalid form", _errors);
        }
    }
}
