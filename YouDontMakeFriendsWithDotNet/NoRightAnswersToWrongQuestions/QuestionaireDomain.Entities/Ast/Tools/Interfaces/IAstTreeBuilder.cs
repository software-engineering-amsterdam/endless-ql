using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Tools.Interfaces
{
    //ToDo: does this Interface need to move to the AntlrInterpretorModule?
    public interface IAstTreeBuilder
    {
        Reference<IQuestionnaireRootNode> BuildForm(string definition);
    }
}
