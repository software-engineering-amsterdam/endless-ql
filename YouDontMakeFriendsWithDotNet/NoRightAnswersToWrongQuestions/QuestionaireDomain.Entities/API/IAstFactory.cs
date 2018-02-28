using System;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstFactory
    {
        IQuestionnaireAst CreateQuestionnaire();
        ICalculationAst CreateCalculation(string calculationDefinition);
        IConditionalAst CreateConditional(string questionDefinition);
        IQuestionAst CreateQuestion(string questionName, string questionText, Type questionType);
    }
}
