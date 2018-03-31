using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Validators
{
    public class StyleSheetTypeChecker : IStyleSheetTypeChecker
    {
        private readonly List<IStyleSheetValidator> m_validators
            = new List<IStyleSheetValidator>();

        public IList<ValidationMetaData> Results { get; set; }
            = new List<ValidationMetaData>();

        public StyleSheetTypeChecker()
        {
            //m_validators.Add(cyclicDependencyValidator);
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
        bool Validate(DomainId<IStyleSheetRootNode> questionnaireRootNode);
    }
}
