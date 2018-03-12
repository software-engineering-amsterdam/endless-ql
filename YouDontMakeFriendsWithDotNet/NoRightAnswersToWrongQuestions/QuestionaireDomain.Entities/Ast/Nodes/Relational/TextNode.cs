using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational
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