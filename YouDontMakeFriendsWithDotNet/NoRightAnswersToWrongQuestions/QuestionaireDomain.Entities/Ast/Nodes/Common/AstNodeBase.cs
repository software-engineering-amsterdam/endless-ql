using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Common
{
    internal abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase(
            Guid id,
            string definition)
        {
            Id = id;
            Definition = definition;
            DisplayName = definition;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public string Definition { get; }
    }
}