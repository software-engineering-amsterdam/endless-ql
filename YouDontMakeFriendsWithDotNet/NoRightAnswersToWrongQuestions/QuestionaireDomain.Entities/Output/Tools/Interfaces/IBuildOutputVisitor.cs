using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IBuildOutputVisitor
    {
        Reference<IQuestionnaireOutputItem> Build(Reference<IQuestionnaireRootNode> node);
    }
}