﻿using System.Collections.Generic;
using QlsTransformer.Domain.Ast.Nodes;

namespace QlsTransformer.Domain.Output.Tools
{
    internal class StyleStack
    {
        private readonly Stack<Style> m_styleStack = new Stack<Style>();

        public StyleStack(Style baseStyle)
        {
            m_styleStack.Push(baseStyle);
        }

        public void PushStyle(Style style)
        {
            m_styleStack.Push(style);
        }

        public void PushStyle(IStyleNode styleNode)
        {
            var previouStyle = PeekStyle();

            if (styleNode == null)
            {
                PushStyle(previouStyle);
            }
            else
            {
                var newStyle = new Style(
                    previouStyle,
                    styleNode.Widget,
                    styleNode.Width,
                    styleNode.Font,
                    styleNode.FontSize,
                    styleNode.Color);

                PushStyle(newStyle);
            }
        }

        public Style PeekStyle()
        {
            return m_styleStack.Peek();
        }

        public Style PopStyle()
        {
            if (m_styleStack.Count == 1) return m_styleStack.Peek();

            return m_styleStack.Pop();
        }
    }
}