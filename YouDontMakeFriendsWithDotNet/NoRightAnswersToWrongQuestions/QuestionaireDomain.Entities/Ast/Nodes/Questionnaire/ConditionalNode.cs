using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire
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