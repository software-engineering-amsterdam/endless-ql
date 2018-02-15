using System;
using System.Diagnostics;
using AntlGrammar;
using Antlr4.Runtime;
using QuestionaireDomain.Entities.API;

namespace AntlrInterpretor.Logic
{
    internal class QlInterpretor : IQlInterpretor
    {
        public IQuestionnaireAst BuildForm(string definition)
        {
            var stream = new AntlrInputStream(definition);
            var lexer = new QLLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(new QlErrorListener());

            var tokens = new CommonTokenStream(lexer);

            var parser = new QLParser(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(new QlErrorListener());

            var tree = parser.questionnaire();

            var qlVisitor = new QlVisitor();
            return qlVisitor.Visit(tree);
        }
    }
    
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
            var message = @"Parse failed. See inner exception for details.";
            var detailsMessage = $@"'{offendingSymbol.Text}' was not recognized at line {line}, position {charPositionInLine}, giving the following error {msg} ";
            throw new QlParserException(message, e)
            {
                ParserName = "ANTLR 4.0",
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
            var message = @"Parse failed. See inner exception for details.";
            var detailsMessage = $@"'{offendingSymbol}' was not recognized at line {line}, position {charPositionInLine}, giving the following error {msg} ";
            throw new QlParserException(message, e)
            {
                ParserName = "ANTLR 4.0",
                ParseErrorDetails = detailsMessage
            };
        }
    }
}