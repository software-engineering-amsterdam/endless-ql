using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal class SectionNode : StyleSheetCompartmentBase, ISectionNode
    {
        public SectionNode(
            Guid id, 
            string definition,
            string name,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<IQlsQuestionNode>> questions) 
            : base(id, definition, name, defaultStyles)
        {
            Questions = questions;
        }

        public IEnumerable<DomainId<IQlsQuestionNode>> Questions { get; }
    }
}