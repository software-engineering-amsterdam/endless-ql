using System.ComponentModel.Design;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQlInterpretor
    {
        Reference<IRootNode> BuildForm(string definition);
        Reference<IBooleanLogicNode> BuildPredicate(string definition);
    }
}
