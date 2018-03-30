using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    public interface IDefaultStyle 
    {
        Type Type { get; }
        DomainId<IStyleNode> Style { get; }
    }

    public class DefaultStyle : IDefaultStyle
    {
        public DefaultStyle(Type type, DomainId<IStyleNode> style)
        {
            Type = type;
            Style = style;
        }

        public Type Type { get; }
        public DomainId<IStyleNode> Style { get; }
    }

    public interface IStyleNode : IAstNode
    {
        IWidget Widget { get; }
        int? Width { get; }
        string Font { get; }
        decimal? FontSize { get; }
        string Color { get; }
    }

    internal class StyleNode : AstNodeBase, IStyleNode
    {
        public StyleNode(
            Guid id, 
            string definition,
            IWidget widget,
            int? width,
            string font,
            decimal? fontSize,
            string color) : base(id, definition)
        {
            Widget = widget;
            Width = width;
            Font = font;
            FontSize = fontSize;
            Color = color;
        }

        public IWidget Widget { get; }
        public int? Width { get; }
        public string Font { get; }
        public decimal? FontSize { get; }
        public string Color { get; }
    }
}