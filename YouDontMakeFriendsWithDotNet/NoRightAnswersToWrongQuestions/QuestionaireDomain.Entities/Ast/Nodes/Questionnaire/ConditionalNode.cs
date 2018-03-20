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