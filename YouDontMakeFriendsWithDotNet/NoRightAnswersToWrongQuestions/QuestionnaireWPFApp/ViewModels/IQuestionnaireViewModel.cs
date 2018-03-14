using System;
using QuestionnaireUI;

namespace QuestionnaireWPFApp.ViewModels
{
    public interface IQuestionnaireViewModel
    {
        void Load(Guid? id = null);
        QuestionnaireWrapper Questionnaire { get; }
    }
}