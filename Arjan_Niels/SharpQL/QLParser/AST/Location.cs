using Antlr4.Runtime;

namespace QLParser.AST
{
    public struct Location
    {
        public int LineIndex { get; set; }
        public int CharIndex { get; set; }

        public Location(int lineIndex, int charIndex)
        {
            this.LineIndex = lineIndex;
            this.CharIndex = charIndex;
        }

        public override string ToString()
        {
            return string.Format("[{0},{1}]", this.LineIndex, this.CharIndex);
        }

        public static Location FromContext(ParserRuleContext context)
        {
            return new Location(context.Start.Line, context.Start.Column);
        }
    }
}