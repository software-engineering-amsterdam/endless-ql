using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public interface IStyleSheetCompartment
    {
        DomainId<IStyleNode> IntegerStyle { get; }
        DomainId<IStyleNode> DecimalStyle { get; }
        DomainId<IStyleNode> DateStyle { get; }
        DomainId<IStyleNode> StringStyle { get; }
        DomainId<IStyleNode> BooleanStyle { get; }
    }
}