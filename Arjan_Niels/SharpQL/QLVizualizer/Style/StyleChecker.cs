using System.Collections.Generic;
using System.Linq;
using QLParser.AST.Nodes;
using QLParser.AST.QLS;

namespace QLVisualizer.Style
{
    public abstract class StyleChecker : IStyleCheckable
    {
        protected Dictionary<string, QValueType> _acceptedStyleParameters { get; private set; }

        public StyleChecker(Dictionary<string, QValueType> acceptedStyleParameters)
        {
            _acceptedStyleParameters = acceptedStyleParameters;
        }

        public StyleChecker(params Dictionary<string,QValueType>[] acceptedStyleParameters)
        {
            _acceptedStyleParameters = acceptedStyleParameters.SelectMany(a => a).ToDictionary(a => a.Key, a => a.Value);
        }

        public ICollection<QLSValue> ParseStyle(ICollection<QLSValue> qlsValues/*, ICollection<QLSValue> parentQLSValues*/)
        {
            // return qlsValues.Where(qlsValue =>  _acceptedStyleParameters.ContainsKey(qlsValue.StyleProperty) && _acceptedStyleParameters[qlsValue.StyleProperty] == qlsValue.QValueType).Concat(parentQLSValues.Where(parentQLSValue => !qlsValues.Select(o => o.StyleProperty).Contains(parentQLSValue.StyleProperty))).ToList();
            Dictionary<string, QLSValue> result = new Dictionary<string, QLSValue>();
            foreach (QLSValue qlsValue in qlsValues)
                if (_acceptedStyleParameters.ContainsKey(qlsValue.StyleProperty) && _acceptedStyleParameters[qlsValue.StyleProperty] == qlsValue.QValueType)
                        result[qlsValue.StyleProperty] = qlsValue;
            return result.Values;
        }
    }
}
