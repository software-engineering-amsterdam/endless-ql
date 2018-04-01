using AntlGrammar;
using Antlr4.Runtime;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace AntlrInterpretor.AstBuilder
{
    internal class AstBuilder : IAstBuilder
    {
        private readonly IAstFactory m_astFactory;
        private readonly IDomainItemLocator m_domainItemLocator;

        public AstBuilder(
            IAstFactory astFactory, 
            IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
        }
        
        public DomainId<IQuestionnaireRootNode> BuildForm(string definition)
        {
            return BuildAstTree<IQuestionnaireRootNode>(definition);
        }
        
        private DomainId<T> BuildAstTree<T>(string definition) where T : IAstNode
        {
            var stream = new AntlrInputStream(definition);
            var lexer = new QlLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(new QlErrorListener());

            var tokens = new CommonTokenStream(lexer);

            var parser = new QlParser(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(new QlErrorListener());

            var tree = parser.questionnaire();

            var qlVisitor = new BuildAstVisitor(m_astFactory, m_domainItemLocator);
            return qlVisitor.Visit(tree).To<T>(m_domainItemLocator);
        }
    }
}