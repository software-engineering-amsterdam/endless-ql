using System.Collections.Generic;
using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IQlsAstFactory
    {
        Reference<IStyleSheetRootNode> CreateStyleSheet(
            string definition, 
            string styleSheetName,
            IEnumerable<Reference<IPageNode>> pages);

        Reference<IPageNode> CreatePage(
            string definition,
            string pageName);
    }
}