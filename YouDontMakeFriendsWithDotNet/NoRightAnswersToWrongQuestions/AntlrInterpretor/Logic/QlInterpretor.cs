using System;
using AntlGrammar;
using Antlr4.Runtime;
using AntlrInterpretor.API;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    //ToDo: Make internal + register with DI
    public class QlInterpretor : IQlInterpretor
    {
        public IQuestionnaire BuildForm(string definition)
        {
            var lexer = new QLLexer(new AntlrInputStream(definition));
            var tokens = new CommonTokenStream(lexer);
            var parser = new QLParser(tokens);
            var tree = parser.questionnaire();

            var qlVisitor = new QLVisitor();
            return qlVisitor.Visit(tree);

            return new Questionnaire()
            {
                FormName = "MyForm"
            };
        }
    }
}