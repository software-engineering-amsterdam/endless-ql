﻿using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class ConditionalAst : AstNodeBase, IConditionalStatementNode
    {
        public ConditionalAst(Guid id, 
            string conditionDefinition,
            Reference<IBooleanLogicNode> predicate,
            IEnumerable<Reference<IStatementNode>> consequent,
            IEnumerable<Reference<IStatementNode>> alternative) : base(id)
        {
            Definition = conditionDefinition;
            Predicate = predicate;
            Consequent = consequent;
            Alternative = alternative;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public string Definition { get; }
        public Reference<IBooleanLogicNode> Predicate { get; }
        public IEnumerable<Reference<IStatementNode>> Consequent { get; }
        public IEnumerable<Reference<IStatementNode>> Alternative { get; }
    }
}
