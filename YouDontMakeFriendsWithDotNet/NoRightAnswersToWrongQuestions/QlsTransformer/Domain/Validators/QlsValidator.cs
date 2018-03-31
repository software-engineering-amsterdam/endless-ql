using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators
{
    public class StyleSheetTypeChecker : IStyleSheetTypeChecker
    {
        private readonly List<IStyleSheetValidator> m_validators
            = new List<IStyleSheetValidator>();

        public IList<ValidationMetaData> Results { get; set; }
            = new List<ValidationMetaData>();

        public StyleSheetTypeChecker(IUnknownQuestionValidator unknownQuestionValidator)
        {
            m_validators.Add(unknownQuestionValidator);
        }

        public bool Validate(DomainId<IStyleSheetRootNode> styleSheet)
        {
            foreach (var validator in m_validators)
            {
                Results = Results
                    .Concat(validator.Validate(styleSheet))
                    .ToList();
            }

            return Results.All(x => x.Severity != Severity.Error);
        }
    }

    public interface IStyleSheetTypeChecker
    {
        IList<ValidationMetaData> Results { get; set; }
        bool Validate(
            DomainId<IStyleSheetRootNode> questionnaireRootNode);
    }
}
