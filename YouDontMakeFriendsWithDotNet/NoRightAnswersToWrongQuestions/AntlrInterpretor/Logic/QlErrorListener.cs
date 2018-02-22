using Antlr4.Runtime;
using QuestionaireDomain.Entities.API;

namespace AntlrInterpretor.Logic
{
    internal class QlErrorListener : BaseErrorListener, IAntlrErrorListener<int>
    {
        public override void SyntaxError(
            IRecognizer recognizer, 
            IToken offendingSymbol, 
            int line,
            int charPositionInLine, 
            string msg, 
            RecognitionException e)
        {
            const string message = @"Parse failed. See inner exception for details.";
            var detailsMessage = $@"'{offendingSymbol.Text}' was not recognized at line {line}, position {charPositionInLine}, giving the following error: {msg} ";
            throw new QlParserException(message, e)
            {
                ParseErrorDetails = detailsMessage
            };
        }
        
        public void SyntaxError(
            IRecognizer recognizer, 
            int offendingSymbol, 
            int line, 
            int charPositionInLine, 
            string msg,
            RecognitionException e)
        {
            const string message = @"Parse failed. See inner exception for details.";
            var detailsMessage = $@"Lexing of {recognizer.InputStream} failed at line {line}, position {charPositionInLine}, giving the following error: {msg} ";
            throw new QlParserException(message, e)
            {
                ParseErrorDetails = detailsMessage
            };
        }
    }
}