namespace QLParser.AST.Nodes
{
    public enum NodeType
    {
        FORM,
        QUESTION,
        CONDITIONAL,
        COMPUTED,
        IDENTIFIER,
        LITERAL,

        // Expression
        LOGICAL_EXPRESSION,
        COMPARISON_EXPRESSION,
        ARTHIMETRIC_EXPRESSION
    }
}
