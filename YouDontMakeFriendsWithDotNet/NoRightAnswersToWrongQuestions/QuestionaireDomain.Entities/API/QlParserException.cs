using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuestionaireDomain.Entities.API
{
    public class QlParserException : Exception
    {
        public string ParserName { get; set; }
        public string ParseErrorDetails { get; set; }

        public QlParserException()
        {
        }

        public QlParserException(string message)
            : base(message)
        {
        }

        public QlParserException(string message, Exception inner)
            : base(message, inner)
        {
        }
    }
}
