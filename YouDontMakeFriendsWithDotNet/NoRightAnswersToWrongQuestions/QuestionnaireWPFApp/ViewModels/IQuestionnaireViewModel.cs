using System;
using QuestionnaireUI;
using QuestionnaireUI.Models;

namespace QuestionnaireWPFApp.ViewModels
{
    public interface IQuestionnaireViewModel
    {
        void Load(Guid? id = null);
        QuestionnaireWrapper Questionnaire { get; }
    }
}