using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Validators.MetaData
{
    public abstract class ValidationMetaData
    {
        protected ValidationMetaData(Severity severity)
        {
            Severity = severity;
        }

        public DomainId<IAstNode> Source { get; set; }
        public string Message { get; set; }
        public Severity Severity { get; }
    }
}