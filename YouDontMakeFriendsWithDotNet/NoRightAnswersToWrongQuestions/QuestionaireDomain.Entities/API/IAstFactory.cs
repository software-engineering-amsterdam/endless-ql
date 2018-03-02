using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstFactory
    {
        Reference<IRootNode> CreateQuestionnaire(
            string questionaireName,
            IEnumerable<Reference<IStatementNode>> statements);

        Reference<ICalculationNode> CreateCalculation(string calculationDefinition);
        Reference<IConditionalStatementNode> CreateConditional(string questionDefinition);
        Reference<IQuestionNode> CreateQuestion(string questionName, string questionText, Type questionType);
        Reference<INumberNode> CreateNumber(string numberText);
        Reference<IVariableNode> CreateNumberVariableName(string variableName);
        //Reference<I xxx Node>
    }
}
