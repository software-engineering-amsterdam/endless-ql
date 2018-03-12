using System;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class TextNode : AstNodeBase, ITextNode
    {
        public string Value { get; }

        public TextNode(
            Guid id,
            string text)
            : base(id, text)
        {
            Value = text.Replace("\"", "");
        }
    }
}