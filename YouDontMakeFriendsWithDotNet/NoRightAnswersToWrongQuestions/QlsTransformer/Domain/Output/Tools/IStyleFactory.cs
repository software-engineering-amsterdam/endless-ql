using QlsTransformer.Domain.Ast.Nodes;

namespace QlsTransformer.Domain.Output.Tools
{
    public interface IStyleFactory
    {
        Style CreateIntegerBaseStyle();
        Style CreateDecimalBaseStyle();
        Style CreateDateBaseStyle();
        Style CreateStringBaseStyle();
        Style CreateBoolBaseStyle();
        Style CreateMergedStyle(Style originalStyle, IStyleNode newStyle);
    }
}