using System.ComponentModel.Design;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstTreeBuilder
    {
        Reference<IQuestionnaireRootNode> BuildForm(string definition);
        Reference<IBooleanLogicNode> BuildPredicate(string definition);
    }
}
