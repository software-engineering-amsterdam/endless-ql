namespace QlsTransformer.Domain.Ast.Nodes
{
    public class AstSlider : ISlider
    {
        public AstSlider(
            int rangeStart, 
            int rangeEnd, 
            int step)
        {
            RangeStart = rangeStart;
            RangeEnd = rangeEnd;
            Step = step;
        }

        public int RangeStart { get; }
        public int RangeEnd { get; }
        public int Step { get; }
    }
}