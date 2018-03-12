using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IBuildOutputVisitor
    {
        Reference<IQuestionnaireOutputItem> Build(Reference<IQuestionnaireRootNode> node);
    }
}