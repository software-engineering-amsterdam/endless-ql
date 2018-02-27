using System;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstFactory
    {
        IQuestionnaireAst CreateQuestionnaire();
        ICalculationAst CreateCalculation(string calculationName);
        IConditionalAst CreateConditional(string questionName);
        IQuestionAst CreateQuestion(string questionName, string questionText, Type questionType);
    }
}
