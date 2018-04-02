using QuestionnaireOrchestration.Models;

namespace QlsTransformer.Orchestration.Models
{
    public class StyledQuestionModel : QuestionModel
    {
        public StyledQuestionModel(
            QuestionModel questionModel,
            StyleModel styleModel)
            : base(
                questionModel.QuestionOutputId,
                questionModel.QuestionVariableId,
                questionModel.QuestionText,
                questionModel.Visible,
                questionModel.ReadOnly,
                questionModel.QuestionType)
        {
            StyleModel = styleModel;
        }

        public StyleModel StyleModel { get; }
    }
}