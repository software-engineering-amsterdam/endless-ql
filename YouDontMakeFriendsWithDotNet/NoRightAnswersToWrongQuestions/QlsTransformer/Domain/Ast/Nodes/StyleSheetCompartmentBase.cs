using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    internal abstract class StyleSheetCompartmentBase : AstNodeBase, IStyleSheetCompartment
    {
        protected StyleSheetCompartmentBase(
            Guid id, 
            string definition, 
            string name,
            IEnumerable<IDefaultStyle> defaultStyle) : base(id, definition)
        {
            Name = name;
            IntegerStyle = defaultStyle.FirstOrDefault(x => x.Type.GetType() == typeof(IntegerQuestionType))?.Style;
            DecimalStyle = defaultStyle.FirstOrDefault(x => x.Type.GetType() == typeof(DecimalQuestionType))?.Style;
            DateStyle = defaultStyle.FirstOrDefault(x => x.Type.GetType() == typeof(DateQuestionType))?.Style;
            StringStyle = defaultStyle.FirstOrDefault(x => x.Type.GetType() == typeof(StringQuestionType))?.Style;
            BooleanStyle = defaultStyle.FirstOrDefault(x => x.Type.GetType() == typeof(BooleanQuestionType))?.Style;
        }

        public string Name { get; }
        public DomainId<IStyleNode> IntegerStyle { get; } 
        public DomainId<IStyleNode> DecimalStyle { get; }
        public DomainId<IStyleNode> DateStyle { get; }
        public DomainId<IStyleNode> StringStyle { get; }
        public DomainId<IStyleNode> BooleanStyle { get; }
    }
}