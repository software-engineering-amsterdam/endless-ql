using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    internal abstract class StyleSheetCompartmentBase : AstNodeBase
    {
        protected StyleSheetCompartmentBase(
            Guid id, 
            string definition, 
            string name,
            IEnumerable<IDefaultStyle> defaultStyle) : base(id, definition)
        {
            Name = name;
            IntegerStyle = defaultStyle.FirstOrDefault(x => x.Type == typeof(int))?.Style;
            DecimalStyle = defaultStyle.FirstOrDefault(x => x.Type == typeof(decimal))?.Style;
            DateStyle = defaultStyle.FirstOrDefault(x => x.Type == typeof(DateTime))?.Style;
            StringStyle = defaultStyle.FirstOrDefault(x => x.Type == typeof(string))?.Style;
            BooleanStyle = defaultStyle.FirstOrDefault(x => x.Type == typeof(bool))?.Style;
        }

        public string Name { get; }
        public DomainId<IStyleNode> IntegerStyle { get; } 
        public DomainId<IStyleNode> DecimalStyle { get; }
        public DomainId<IStyleNode> DateStyle { get; }
        public DomainId<IStyleNode> StringStyle { get; }
        public DomainId<IStyleNode> BooleanStyle { get; }
    }
}