using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class ConditionalNode : 
        AstNodeBase, 
        IConditionalStatementNode
    {
        public Reference<IBooleanLogicNode> Predicate { get; }
        public IEnumerable<Reference<IStatementNode>> Consequent { get; }
        public IEnumerable<Reference<IStatementNode>> Alternative { get; }

        public ConditionalNode(Guid id, 
            string definition,
            Reference<IBooleanLogicNode> predicate,
            IEnumerable<Reference<IStatementNode>> consequent,
            IEnumerable<Reference<IStatementNode>> alternative) : base(id, definition)
        {
            Predicate = predicate;
            Consequent = consequent ?? new List<Reference<IStatementNode>>();
            Alternative = alternative ?? new List<Reference<IStatementNode>>();
        }
    }
}