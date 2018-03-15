namespace QuestionnaireUI.Models
{
    public class QuestionnaireWrapper
    {
        public QuestionnaireWrapper(QuestionnaireModel model)
        {
            Model = model;
        }

        public QuestionnaireModel Model { get; set; }
    }
}