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
            string pageName,
            IEnumerable<Reference<ISectionNode>> pages);

        Reference<ISectionNode> CreateSection(
            string definition,
            string sectionName,
            IEnumerable<Reference<IQlsQuestionNode>> questions);
        
        Reference<IQlsQuestionNode> CreateQuestion(
            string definition,
            string questionName);
    }
}