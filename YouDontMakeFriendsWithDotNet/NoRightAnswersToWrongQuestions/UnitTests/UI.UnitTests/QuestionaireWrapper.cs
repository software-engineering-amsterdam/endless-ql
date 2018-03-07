using System;

namespace UnitTests.UI.UnitTests
{
    public class QuestionaireWrapper
    {
        public QuestionaireWrapper(QuestionnaireModel questionnaire)
        {
            Model = questionnaire;
        }

        public QuestionnaireModel Model { get; set; }
    }
}