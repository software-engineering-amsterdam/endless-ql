using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal class PageNode : AstNodeBase, IPageNode
    {
        public PageNode(
            Guid id, 
            string definition,
            string name,
            IEnumerable<Reference<ISectionNode>> sections) : base(id, definition)
        {
            Name = name;
            Sections = sections;
        }

        public string Name { get; }
        public IEnumerable<Reference<ISectionNode>> Sections { get; }
    }
}
