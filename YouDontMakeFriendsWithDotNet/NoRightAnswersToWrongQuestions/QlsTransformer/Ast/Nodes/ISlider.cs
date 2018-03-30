﻿namespace QlsTransformer.Ast.Nodes
{
    public interface ISlider : IWidget
    {
        int RangeStart { get; }
        int RangeEnd { get; }
        int Step { get; }
    }
}