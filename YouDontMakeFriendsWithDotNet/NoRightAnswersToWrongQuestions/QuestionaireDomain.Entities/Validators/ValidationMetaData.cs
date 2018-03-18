using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Validators
{
    public class ValidationMetaData
    {
        public Reference<IAstNode> Source { get; set; }
        public string Message { get; set; }
        public Severity Severity { get; set; }
    }
}