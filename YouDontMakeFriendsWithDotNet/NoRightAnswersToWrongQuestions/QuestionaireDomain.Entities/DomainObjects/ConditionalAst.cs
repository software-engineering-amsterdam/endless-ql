using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class ConditionalAst : AstNodeBase, IConditionalStatementNode
    {
        public ConditionalAst(Guid id, string conditionDefinition) : base(id)
        {
            ConditionDefinition = conditionDefinition;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public string ConditionDefinition { get; }
        public IBooleanLogicNode Predicate { get; }
        public IList<IQuestionnaireNode> Consequent { get; }
        public IList<IQuestionnaireNode> Alternative { get; }
    }
}
