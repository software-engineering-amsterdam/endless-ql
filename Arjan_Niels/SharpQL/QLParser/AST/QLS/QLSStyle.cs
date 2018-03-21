using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSStyle
    {
        public IList<QLSStyleValue> StylingValues { get; set; }
        public QLSWidgetSpecification WidgetSpecification { get; private set; }

        public QLSStyle()
        {
            this.StylingValues = new List<QLSStyleValue>();
        }

        public QLSStyle(QLSWidgetSpecification specification) : this()
        {
            this.WidgetSpecification = specification;
        }

        public void AddStyleValue(QLSStyleValue styleValue)
        {
            this.StylingValues.Add(styleValue);
        }

        public override string ToString()
        {
            return WidgetSpecification != null ? " - Node has a specification" : "";
        }
    }
}