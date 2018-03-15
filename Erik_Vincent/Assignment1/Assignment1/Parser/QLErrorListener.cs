using Antlr4.Runtime;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Parser
{
    class QLErrorListener : BaseErrorListener, IAntlrErrorListener<int>
    {
        private QLParseErrorHandler _errorHandler;

        public QLErrorListener(QLParseErrorHandler errorHandler)
        {
            _errorHandler = errorHandler;
        }

        public override void SyntaxError(TextWriter output, IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            _errorHandler.AddError(line, msg);
        }

        void IAntlrErrorListener<int>.SyntaxError(TextWriter output, IRecognizer recognizer, int offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            _errorHandler.AddError(line, msg);
        }
    }
}
