using AntlGrammar;
using Antlr4.Runtime;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    internal class QlInterpretor : IQlInterpretor
    {
        private readonly IAstFactory m_astFactory;
        private readonly IDomainItemLocator m_domainItemLocator;

        public QlInterpretor(IAstFactory astFactory, IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
        }

        public Reference<IRootNode> BuildForm(string definition)
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

            var qlVisitor = new BuildAstVisitor(m_astFactory, m_domainItemLocator);
            return qlVisitor.Visit(tree).To<IRootNode>(m_domainItemLocator);
        }
        
    }
}