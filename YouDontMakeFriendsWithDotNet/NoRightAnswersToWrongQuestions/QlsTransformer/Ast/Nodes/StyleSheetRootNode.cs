using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal class StyleSheetRootNode : AstNodeBase, IStyleSheetRootNode
    {
        public StyleSheetRootNode(
            Guid id, 
            string definition,
            string styleSheetName,
            IEnumerable<Reference<IPageNode>> pages) 
            : base(id, definition)
        {
            Name = styleSheetName;
            Pages = pages;
        }

        public string Name { get; }
        public IEnumerable<Reference<IPageNode>> Pages { get; }
    }
}
    