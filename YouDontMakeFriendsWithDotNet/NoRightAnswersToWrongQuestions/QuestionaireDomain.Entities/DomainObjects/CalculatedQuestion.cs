using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculatedQuestion : AstNodeBase, ICalculatedQuestionNode
    {
        public string QuestionId { get; }
        public string QuestionText { get; }
        public Type QuestionType { get; }
        public Reference<ICalculationNode> CalculatedValue { get; }

        public CalculatedQuestion(
            Guid id, 
            string definition,
            string questionId, 
            string questionText, 
            Type questionType,
            Reference<ICalculationNode> calculation) : base(id, definition)
        {
            QuestionId = questionId;
            QuestionText = questionText;
            QuestionType = questionType;
            CalculatedValue = calculation;
        }

        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: Do something here
        }
    }
}