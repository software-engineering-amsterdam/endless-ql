using Antlr4.Runtime;
using System.IO;

namespace Assignment1.Parser
{
    internal class QLErrorListener : BaseErrorListener, IAntlrErrorListener<int>
    {
        private readonly ParseErrorHandler _errorHandler;

        public QLErrorListener(ParseErrorHandler errorHandler)
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
