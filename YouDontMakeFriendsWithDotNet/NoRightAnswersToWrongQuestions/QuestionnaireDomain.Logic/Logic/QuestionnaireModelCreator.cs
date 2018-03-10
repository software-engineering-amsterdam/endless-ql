using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionnaireDomain.Logic.Logic
{
    public class QuestionnaireModelCreator : IQuestionnaireModelCreator
    {
        private readonly IOutputItemFactory m_outputItemFactory;

        public QuestionnaireModelCreator(IOutputItemFactory outputItemFactory)
        {
            m_outputItemFactory = outputItemFactory;
        }

        public bool Validate(IQuestionnaireRootNode questionnaireRootNode)
        {
            //ToDo Make this work
            return true;
        }

        public void Create(IQuestionnaireRootNode questionnaireRootNode)
        {
            m_outputItemFactory.CreateQuestionnaireOutputItem(
                "John", new List<IQuestionOutputItem>());
        }
    }
}
