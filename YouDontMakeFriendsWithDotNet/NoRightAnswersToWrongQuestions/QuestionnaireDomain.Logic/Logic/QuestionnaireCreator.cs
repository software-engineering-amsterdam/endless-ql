using System;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IQuestionCreator
    {


    }

    internal class QuestionnaireCreator : IQuestionnaireCreator
    {
        private readonly IQlInterpretor m_qlInterpretor;

        public QuestionnaireCreator(IQlInterpretor qlInterpretor)
        {
            m_qlInterpretor = qlInterpretor;
        }

        public Guid Create(string definition)
        {
            var form =  m_qlInterpretor.BuildForm(definition);
            return form.Id;
        }
    }
}