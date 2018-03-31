using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal class PageNode : StyleSheetCompartmentBase, IPageNode
    {
        public PageNode(
            Guid id, 
            string definition,
            string name,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<ISectionNode>> sections) 
            : base(id, definition, name, defaultStyles)
        {
            Sections = sections;
        }

        public IEnumerable<DomainId<ISectionNode>> Sections { get; }
    }
}
