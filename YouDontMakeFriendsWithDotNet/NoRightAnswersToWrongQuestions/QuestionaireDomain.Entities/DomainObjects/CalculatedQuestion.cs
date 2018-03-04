using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculatedQuestion : AstNodeBase, ICalculatedQuestionNode
    {
        public CalculatedQuestion(
            Guid id, 
            string questionId, 
            string questionText, 
            Type questionType,
            Reference<ICalculationNode> calculation) : base(id)
        {
            QuestionId = questionId;
            QuestionText = questionText;
            QuestionType = questionType;
            CalculatedValue = calculation;
        }

        public string QuestionId { get; }
        public string QuestionText { get; }
        public Type QuestionType { get; }

        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: Do something here
        }

        public Reference<ICalculationNode> CalculatedValue { get; }
    }
}