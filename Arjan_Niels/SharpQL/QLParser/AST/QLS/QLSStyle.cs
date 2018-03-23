using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSStyle : IQLSElement
    {
        public IList<QLSValue> StylingValues { get; set; }
        public QLSWidgetSpecification WidgetSpecification { get; private set; }

        public QLSStyle()
        {
            this.StylingValues = new List<QLSValue>();
            this.WidgetSpecification = new QLSWidgetSpecification();
        }

        public QLSStyle(QLSWidgetSpecification specification) : this()
        {
            this.WidgetSpecification = specification;
        }

        public void AddStyleValue(QLSValue styleValue)
        {
            this.StylingValues.Add(styleValue);
        }

        public override string ToString()
        {
            return WidgetSpecification != null ? " - Node has a specification" : "";
        }

        #region IQLSElement
        public QLSWidgetSpecification GetQLSWidgetSpecification()
        {
            return this.WidgetSpecification;
        }

        public IList<QLSValue> GetStylingValues()
        {
            return this.StylingValues;
        }
        #endregion
    }
}