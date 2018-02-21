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
            return (IQuestionnaireAst)qlVisitor.Visit(tree);
        }
    }
}