using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal class StyleSheetRootNode : StyleSheetCompartmentBase, IStyleSheetRootNode
    {
        public StyleSheetRootNode(
            Guid id, 
            string definition,
            string styleSheetName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<IPageNode>> pages) 
            : base(id, definition, styleSheetName, defaultStyles)
        {
            Pages = pages;
        }
        
        public IEnumerable<DomainId<IPageNode>> Pages { get; }
    }
}
    