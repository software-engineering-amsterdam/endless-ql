using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class AstFactory : IAstFactory
    {
        private readonly IIdMaker m_ids;

        public AstFactory(IIdMaker ids)
        {
            m_ids = ids;
        }

        public IQuestionnaireAst CreateQuestionnaire()
        {
            return new QuestionnaireAst(m_ids.Next);
        }

        public ICalculationAst CreateCalculation(string calculationName)
        {
            return new CalculationAst(m_ids.Next, calculationName);
        }

        public IConditionalAst CreateConditional(string questionName)
        {
            return new ConditionalAst(m_ids.Next, questionName);
        }

        public IQuestionAst CreateQuestion(string questionName, string questionText, Type questionType)
        {
            return new QuestionAst(m_ids.Next, questionName, questionText, questionType);
        }
    }
}
