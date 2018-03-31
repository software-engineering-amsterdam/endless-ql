using System;
using Antlr4.Runtime;
using QlsGrammar;
using QlsTransformer.Ast.Nodes;
using QlsTransformer.Ast.Tools;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsParser.AstBuilder
{
    internal class QlsAstBuilder : IQlsAstBuilder
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IQlsAstFactory m_qlsAstFactory;

        public QlsAstBuilder(
            IDomainItemLocator domainItemLocator,
            IQlsAstFactory qlsAstFactory)
        {
            m_domainItemLocator = domainItemLocator;
            m_qlsAstFactory = qlsAstFactory;
        }

        public DomainId<IStyleSheetRootNode> BuildStyleSheet(string definition)
        {
            return BuildAstTree<IStyleSheetRootNode>(definition);
        }

        private DomainId<T> BuildAstTree<T>(string definition) where T : IAstNode
        {
            var stream = new AntlrInputStream(definition);
            var lexer = new QlsLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(new QlsErrorListener());

            var tokens = new CommonTokenStream(lexer);

            var parser = new QlsGrammar.QlsParser(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(new QlsErrorListener());

            var tree = parser.styleSheet();

            var qlsVisitor = new BuildQlsAstVisitor(m_qlsAstFactory, m_domainItemLocator);
            return qlsVisitor.Visit(tree).To<T>(m_domainItemLocator);
        }
    }
}
