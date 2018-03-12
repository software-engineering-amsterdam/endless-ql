using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire
{
    internal class CalculatedQuestionNode : 
        AstNodeBase, 
        ICalculatedQuestionNode
    {
        public string QuestionId { get; }
        public string QuestionText { get; }
        public Type QuestionType { get; }
        public Reference<ICalculationNode> CalculatedValue { get; }

        public CalculatedQuestionNode(
            Guid id, 
            string definition,
            string questionId, 
            string questionText, 
            Type questionType,
            Reference<ICalculationNode> calculation) 
            : base(id, definition)
        {
            QuestionId = questionId;
            QuestionText = questionText;
            QuestionType = questionType;
            CalculatedValue = calculation;
        }
    }
}