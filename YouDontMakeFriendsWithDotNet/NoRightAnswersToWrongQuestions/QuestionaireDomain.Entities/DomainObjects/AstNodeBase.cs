﻿using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase(
            Guid id,
            string definition)
        {
            Id = id;
            Definition = definition;
        }

        public Guid Id { get; }
        public string Definition { get; }
        public abstract void Accept(IAstVisitor visitor);
    }
}