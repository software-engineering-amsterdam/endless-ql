using System.Linq;
using AntlGrammar;
using Antlr4.Runtime;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace AntlrInterpretor.Logic
{
    internal class AstTreeBuilder : IAstTreeBuilder
    {
        private readonly IAstFactory m_astFactory;
        private readonly IDomainItemLocator m_domainItemLocator;

        public AstTreeBuilder(
            IAstFactory astFactory, 
            IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
        }
        
        public Reference<IQuestionnaireRootNode> BuildForm(string definition)
        {
            return BuildAstTree<IQuestionnaireRootNode>(definition);
        }
        
        private Reference<T> BuildAstTree<T>(string definition) where T : IAstNode
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
            return qlVisitor.Visit(tree).To<T>(m_domainItemLocator);
        }
    }
}