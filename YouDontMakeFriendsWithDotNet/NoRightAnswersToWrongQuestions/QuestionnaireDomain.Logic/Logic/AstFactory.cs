using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class AstFactory : IAstFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;

        public AstFactory(IIdMaker ids, IDomainItemRegistry registry)
        {
            m_ids = ids;
            m_registry = registry;
        }

        public IQuestionnaireAst CreateQuestionnaire()
        {
            var questionnaire = new QuestionnaireAst(m_ids.Next);
            m_registry.Add(questionnaire);
            return questionnaire;
        }

        public ICalculationAst CreateCalculation(string calculationDefinition)
        {
            var calculation = new CalculationAst(m_ids.Next, calculationDefinition);
            m_registry.Add(calculation);
            return calculation;
        }

        public IConditionalAst CreateConditional(string questionDefinition)
        {
            var condition = new ConditionalAst(m_ids.Next, questionDefinition);
            m_registry.Add(condition);
            return condition;
        }

        public IQuestionAst CreateQuestion(
            string questionName, 
            string questionText, 
            Type questionType)
        {
            var question = new QuestionAst(m_ids.Next, questionName, questionText, questionType);
            m_registry.Add(question);
            return question;
        }
    }
}
