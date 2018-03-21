using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public interface IQLSElement
    {
        QLSWidgetSpecification GetQLSWidgetSpecification();
        IList<QLSValue> GetStylingValues();
    }
}
