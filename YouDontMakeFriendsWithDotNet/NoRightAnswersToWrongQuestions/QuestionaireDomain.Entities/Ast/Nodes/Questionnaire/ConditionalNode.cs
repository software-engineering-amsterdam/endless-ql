using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire
{
    internal class ConditionalNode :
        AstNodeBase,
        IConditionalStatementNode
    {
        public ConditionalNode(Guid id,
            string definition,
            DomainId<IBooleanLogicNode> predicate,
            IEnumerable<DomainId<IStatementNode>> consequent,
            IEnumerable<DomainId<IStatementNode>> alternative) : base(id, definition)
        {
            Predicate = predicate;
            Consequent = consequent ?? new List<DomainId<IStatementNode>>();
            Alternative = alternative ?? new List<DomainId<IStatementNode>>();
        }

        public DomainId<IBooleanLogicNode> Predicate { get; }
        public IEnumerable<DomainId<IStatementNode>> Consequent { get; }
        public IEnumerable<DomainId<IStatementNode>> Alternative { get; }
    }
}