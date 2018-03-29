using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal class SectionNode : AstNodeBase, ISectionNode
    {

        public SectionNode(
            Guid id, 
            string definition,
            string name,
            IEnumerable<Reference<IQlsQuestionNode>> questions) 
            : base(id, definition)
        {
            Name = name;
            Questions = questions;
        }
        public string Name { get; }
        public IEnumerable<Reference<IQlsQuestionNode>> Questions { get; }
    }
}
