namespace QuestionnaireUI.Models
{
    public class QuestionWrapper
    {
        public QuestionWrapper(QuestionModel model)
        {
            Model = model;
        }

        public QuestionModel Model { get; set; }
    }
}
