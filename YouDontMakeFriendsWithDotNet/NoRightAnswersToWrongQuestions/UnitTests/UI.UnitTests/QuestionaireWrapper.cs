using System;

namespace UnitTests.UI.UnitTests
{
    public class QuestionaireWrapper
    {
        public QuestionaireWrapper(QuestionnaireModel questionnaire)
        {
            if (questionnaire == null)
            {
                throw new ArgumentNullException(nameof(questionnaire));
            }

            Model = questionnaire;

            if (string.IsNullOrEmpty(questionnaire.Name))
            {
                throw new ArgumentException("questionnaire name cannot be null");
            }
        }

        public QuestionnaireModel Model { get; set; }
    }
}