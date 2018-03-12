using System;
using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire
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