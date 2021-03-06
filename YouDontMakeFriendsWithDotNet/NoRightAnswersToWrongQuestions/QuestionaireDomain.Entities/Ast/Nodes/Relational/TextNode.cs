﻿using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
{
    internal class TextNode : AstNodeBase, ITextNode
    {
        public TextNode(
            Guid id,
            string text)
            : base(id, text)
        {
            Value = text.Replace("\"", "");
        }

        public string Value { get; }
    }
}