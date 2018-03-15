using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IVariableUpdater
    {
        void Update<T>(Reference<IQuestionNode> node, T value);
    }
}
