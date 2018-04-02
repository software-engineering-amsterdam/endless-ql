using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Tools
{
    internal class StyleSheetCreator : IStyleSheetCreator
    {
        private readonly IQlsAstBuilder m_astBuilder;

        public StyleSheetCreator(IQlsAstBuilder astBuilder)
        {
            m_astBuilder = astBuilder;
        }

        public DomainId<IStyleSheetRootNode> Create(string definition)
        {
            return m_astBuilder.BuildStyleSheet(definition);
        }
    }
}