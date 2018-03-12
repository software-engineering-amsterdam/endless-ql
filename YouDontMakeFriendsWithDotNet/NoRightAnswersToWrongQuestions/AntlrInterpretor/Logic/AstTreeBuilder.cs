using System.Linq;
using AntlGrammar;
using Antlr4.Runtime;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

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

        public Reference<IBooleanLogicNode> BuildPredicate(string definition)
        {
            definition = $"form FakeForm {{ if ({definition}) {{ fakeVar: \"fakeText\" date }} }}";
            BuildForm(definition);
            return m_domainItemLocator
                .GetAll<IConditionalStatementNode>()
                .FirstOrDefault()
                ?.Predicate;
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