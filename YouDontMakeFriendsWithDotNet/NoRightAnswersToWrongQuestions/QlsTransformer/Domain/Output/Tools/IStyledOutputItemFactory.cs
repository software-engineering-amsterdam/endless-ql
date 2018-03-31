using System.Collections.Generic;
using QlsTransformer.Domain.Output.Nodes;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QlsTransformer.Domain.Output.Tools
{
    public interface IStyledOutputItemFactory
    {
        DomainId<IStyledQuestionnaireOutputItem> CreateRoot(
            string name, 
            List<DomainId<IPagesOutputItem>> pages);

        DomainId<IPagesOutputItem> CreatePage(
            string pageNodeName, 
            List<DomainId<ISectionOutputItem>> sections);

        DomainId<ISectionOutputItem> CreateSection(
            string sectionNodeName, 
            List<DomainId<IStyledQuestionOutputItem>> questions);

        DomainId<IStyledQuestionOutputItem> CreateQuestion(
            IQuestionOutputItem question, 
            Style style);
    }
}