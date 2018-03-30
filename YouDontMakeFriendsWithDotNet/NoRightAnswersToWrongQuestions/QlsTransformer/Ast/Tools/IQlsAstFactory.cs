using System.Collections.Generic;
using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IQlsAstFactory
    {
        DomainId<IStyleSheetRootNode> CreateStyleSheet(
            string definition, 
            string styleSheetName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<IPageNode>> pages);

        DomainId<IPageNode> CreatePage(
            string definition,
            string pageName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<ISectionNode>> sections);

        DomainId<ISectionNode> CreateSection(
            string definition,
            string sectionName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<IQlsQuestionNode>> questions);
        
        DomainId<IQlsQuestionNode> CreateQuestion(
            string definition,
            string questionName,
            DomainId<IStyleNode> questionStyle);

        DomainId<IAstNode> CreateStyle(
            string definition, 
            IWidget widget, 
            int? width, 
            decimal? fontSize, 
            string font, 
            string color);
    }
}