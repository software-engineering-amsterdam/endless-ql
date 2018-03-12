using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Common
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